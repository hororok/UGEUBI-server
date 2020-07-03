package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.common.util.MailForm;
import duksung.backend.hororok.ugeubi.common.util.RandomNumber;
import duksung.backend.hororok.ugeubi.common.util.RandomString;
import duksung.backend.hororok.ugeubi.user.domain.entity.SignUpNumber;
import duksung.backend.hororok.ugeubi.user.domain.entity.FindPasswordCode;
import duksung.backend.hororok.ugeubi.user.domain.repository.SignUpNumberRepository;
import duksung.backend.hororok.ugeubi.user.domain.repository.FindPasswordCodeRepository;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailFindPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailSignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqVerifyFindPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqVerifySignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.exception.ExistUserEmailException;
import duksung.backend.hororok.ugeubi.user.exception.IsNotEqualToAuthCodeException;
import duksung.backend.hororok.ugeubi.user.exception.NoExistUserAccountException;
import duksung.backend.hororok.ugeubi.user.exception.UnAuthorizedAuthCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationEmailService {

    private final UserRepository userRepository;
    private final SignUpNumberRepository signUpNumberRepository;
    private final JavaMailSenderService javaMailSenderService;
    private final FindPasswordCodeRepository findPasswordCodeRepository;

    private static final String SIGN_UP_ID = "sign-up-id";
    private static final String FIND_PW_CODE_ID = "find-pw-code-id";

    public void sendEmailSignUpNumber(ReqEmailSignUpNumberDto reqEmailSignUpNumberDto){
        boolean isExistEmail = userRepository.existsByEmail(reqEmailSignUpNumberDto.getEmail());

        if(isExistEmail){
            throw new ExistUserEmailException();
        }

        String number = createAuthenticationNumber();
        javaMailSenderService.sendEmail(reqEmailSignUpNumberDto.getEmail(), MailForm.SIGN_UP.getSubject(),
                MailForm.SIGN_UP.getContent()+number);

        saveAuthenticationNumberAtRedis(reqEmailSignUpNumberDto, number);
    }

    @Transactional
    public void verifyEmailSignUpNumber(ReqVerifySignUpNumberDto reqVerifySignUpNumberDto) {
        String id = SIGN_UP_ID+reqVerifySignUpNumberDto.getEmail();
        SignUpNumber signUpNumber = signUpNumberRepository.findById(id)
                .orElseThrow(()->new UnAuthorizedAuthCodeException());

        if(!signUpNumber.getAuthenticateNumber().equals(reqVerifySignUpNumberDto.getAuthenticateNumber())){
            throw new IsNotEqualToAuthCodeException();
        }

        signUpNumberRepository.delete(signUpNumber);
    }

    public void sendEmailFindPasswordCode(ReqEmailFindPasswordDto reqEmailFindPasswordDto) {

        boolean result = userRepository.existsByEmailAndUserId(reqEmailFindPasswordDto.getEmail(), reqEmailFindPasswordDto.getUserId());

        if(!result){
            throw new NoExistUserAccountException();
        }

        String passwordCode = createFindPasswordCode();
        javaMailSenderService.sendEmail(reqEmailFindPasswordDto.getEmail(), MailForm.FIND_USER_PASSWORD.getSubject(),
                MailForm.FIND_USER_PASSWORD.getContent() + passwordCode);

        saveAuthenticationPasswordAtRedis(reqEmailFindPasswordDto, passwordCode);
    }

    @Transactional
    public void verifyEmailFindPasswordCode(ReqVerifyFindPasswordDto reqVerifyFindPasswordDto) {
        String id = FIND_PW_CODE_ID+reqVerifyFindPasswordDto.getEmail();
        FindPasswordCode passwordCode = findPasswordCodeRepository.findById(id)
                .orElseThrow(()->new UnAuthorizedAuthCodeException());

        if(!passwordCode.getFindPasswordCode().equals(reqVerifyFindPasswordDto.getTemporaryPassword())){
            throw new IsNotEqualToAuthCodeException();
        }

        findPasswordCodeRepository.delete(passwordCode);
    }
    private String createAuthenticationNumber(){
        return RandomNumber.generateRandomNumber().toString();
    }

    private String createFindPasswordCode(){
        return RandomString.generateRandomString();
    }

    @Transactional
    void saveAuthenticationNumberAtRedis(ReqEmailSignUpNumberDto reqEmailSignUpNumberDto, String number){
        String email = reqEmailSignUpNumberDto.getEmail();
        String id = SIGN_UP_ID+email;

        SignUpNumber signUpNumber = signUpNumberRepository.findById(id)
                .orElse(SignUpNumber.builder()
                        .id(id)
                        .email(email)
                        .build());

        signUpNumber.changeNumber(number); // 이메일 인증번호 재전송의 경우

        signUpNumberRepository.save(signUpNumber);
    }

    @Transactional
    void saveAuthenticationPasswordAtRedis(ReqEmailFindPasswordDto reqEmailFindPasswordDto, String passwordCode){
        String email = reqEmailFindPasswordDto.getEmail();
        String id = FIND_PW_CODE_ID+email;

        FindPasswordCode temporaryPassword = findPasswordCodeRepository.findById(id)
                .orElse(FindPasswordCode.builder()
                        .id(id)
                        .email(email)
                        .userId(reqEmailFindPasswordDto.getUserId())
                        .build());

        temporaryPassword.changePasswordCode(passwordCode); // 비밀번호 찾기 이메일 재전송의 경우

        findPasswordCodeRepository.save(temporaryPassword);
        //log.info("저장된 코드:"+findPasswordCodeRepository.findById(id).get().getFindPasswordCode());
    }
}

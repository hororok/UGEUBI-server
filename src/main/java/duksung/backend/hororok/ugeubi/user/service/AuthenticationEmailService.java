package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.common.util.MailForm;
import duksung.backend.hororok.ugeubi.common.util.RandomNumber;
import duksung.backend.hororok.ugeubi.common.util.RandomString;
import duksung.backend.hororok.ugeubi.user.domain.entity.SignUpNumber;
import duksung.backend.hororok.ugeubi.user.domain.repository.SignUpNumberRepository;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailFindPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailSignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqVerifySignUpNumberDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class AuthenticationEmailService {

    private final UserRepository userRepository;
    private final SignUpNumberRepository signUpNumberRepository;
    private final JavaMailSenderService javaMailSenderService;
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendEmailSignUpNumber(ReqEmailSignUpNumberDto reqEmailSignUpNumberDto){
        boolean isExistEmail = userRepository.existsByEmail(reqEmailSignUpNumberDto.getEmail());

        if(isExistEmail){
            throw new IllegalArgumentException("중복되는 이메일 입니다.");
        }

        String number = createAuthenticationNumber();
        javaMailSenderService.sendEmail(reqEmailSignUpNumberDto.getEmail(), MailForm.SIGN_UP.getSubject(),
                MailForm.SIGN_UP.getContent()+number);

        saveAuthenticationNumberAtRedis(reqEmailSignUpNumberDto, number);
    }

    public void verifyEmailSignUpNumber(ReqVerifySignUpNumberDto reqVerifySignUpNumberDto) {
        SignUpNumber signUpNumber = signUpNumberRepository.findById(reqVerifySignUpNumberDto.getEmail())
                .orElseThrow(()->new IllegalArgumentException("인증번호의 만료시간이 지났거나 인증번호를 보낸 이메일이 아닙니다."));

        if(!signUpNumber.getAuthenticateNumber().equals(reqVerifySignUpNumberDto.getAuthenticateNumber())){
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        signUpNumberRepository.delete(signUpNumber);
    }


    public void sendEmailTemporaryPassword(ReqEmailFindPasswordDto reqEmailFindPasswordDto) {

        boolean result = userRepository.existsByEmailAndUserId(reqEmailFindPasswordDto.getEmail(), reqEmailFindPasswordDto.getUserId());

        if(!result){
            throw new IllegalArgumentException("이메일과 아이디에 해당하는 사용자가 존재하지 않습니다.");
        }

        String temporaryPassword = createTemporaryPassword();
        javaMailSenderService.sendEmail(reqEmailFindPasswordDto.getEmail(), MailForm.FIND_USER_PASSWORD.getSubject(),
                MailForm.FIND_USER_PASSWORD.getContent() + temporaryPassword);

        saveAuthenticationPasswordAtRedis(reqEmailFindPasswordDto, temporaryPassword);
    }

    private String createAuthenticationNumber(){
        return RandomNumber.generateRandomNumber().toString();
    }

    private String createTemporaryPassword(){
        return RandomString.generateRandomString();
    }

    @Transactional
    void saveAuthenticationNumberAtRedis(ReqEmailSignUpNumberDto reqEmailSignUpNumberDto, String number){
        String email = reqEmailSignUpNumberDto.getEmail();
        SignUpNumber signUpNumber = signUpNumberRepository.findById(email)
                .orElse(SignUpNumber.builder()
                        .email(email)
                        .build());

        signUpNumber.changeNumber(number); // 이메일 인증번호 재전송의 경우

        signUpNumberRepository.save(signUpNumber);
    }

    @Transactional
    void saveAuthenticationPasswordAtRedis(ReqEmailFindPasswordDto reqEmailFindPasswordDto, String temporaryPassword){
        //TemporaryPassword temporaryPassword = TemporaryPassword
    }
}

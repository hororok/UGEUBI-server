package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.common.util.MailForm;
import duksung.backend.hororok.ugeubi.common.util.RandomNumber;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailSignUpNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationEmailService {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    public void sendEmailSignUpNumber(ReqEmailSignUpNumberDto reqEmailSignUpNumberDto){
        boolean isExistEmail = userRepository.existsByEmail(reqEmailSignUpNumberDto.getEmail());

        if(isExistEmail){
            throw new IllegalArgumentException("중복되는 이메일 입니다.");
        }

        String number = createAuthenticationEmailNumber();
        sendEmail(reqEmailSignUpNumberDto.getEmail(), MailForm.SIGN_UP.getSubject(),
                MailForm.SIGN_UP.getContent()+number);

        //TODO 레디스에 회원가입 인증번호 저장
        //saveAuthenticationNumberAtRedis();
    }

    private String createAuthenticationEmailNumber(){
        return RandomNumber.generateRandomNumber().toString();
    }

    private void sendEmail(String to, String subject, String contents){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);//메일 제목
        message.setText(contents); //메일 내용

        javaMailSender.send(message);
    }
}

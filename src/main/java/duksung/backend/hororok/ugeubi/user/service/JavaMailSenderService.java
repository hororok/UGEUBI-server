package duksung.backend.hororok.ugeubi.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JavaMailSenderService {
    private final JavaMailSender javaMailSender;

    @Async("emailSenderThreadPool")
    public void sendEmail(String to, String subject, String contents){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);//메일 제목
        message.setText(contents); //메일 내용

        javaMailSender.send(message);
    }
}

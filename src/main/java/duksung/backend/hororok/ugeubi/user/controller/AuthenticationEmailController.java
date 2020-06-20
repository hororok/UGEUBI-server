package duksung.backend.hororok.ugeubi.user.controller;

import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailFindPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailSignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqVerifySignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.service.AuthenticationEmailService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthenticationEmailController {

    private final AuthenticationEmailService authenticationEmailService;

    @PostMapping("/authentication-numbers/sign-up")
    public ResponseEntity<Void> sendEmailSignUpNumber(@RequestBody @Valid ReqEmailSignUpNumberDto reqEmailSignUpNumberDto,
                                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        authenticationEmailService.sendEmailSignUpNumber(reqEmailSignUpNumberDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/authentication/sign-up")
    public ResponseEntity<Void> verifyEmailSignUpNumber(@RequestBody @Valid ReqVerifySignUpNumberDto reqVerifySignUpNumberDto,
                                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        authenticationEmailService.verifyEmailSignUpNumber(reqVerifySignUpNumberDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/authentication-numbers/find-password")
    public ResponseEntity<Void> sendEmailTemporaryPassword(@RequestBody @Valid ReqEmailFindPasswordDto reqEmailFindPasswordDto,
                                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        authenticationEmailService.sendEmailTemporaryPassword(reqEmailFindPasswordDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

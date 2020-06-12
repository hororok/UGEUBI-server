package duksung.backend.hororok.ugeubi.user.controller;

import duksung.backend.hororok.ugeubi.user.dto.request.ReqEmailSignUpNumberDto;
import duksung.backend.hororok.ugeubi.user.service.AuthenticationEmailService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> generateEmailSignUpNumber(@RequestBody @Valid ReqEmailSignUpNumberDto reqEmailSignUpNumberDto,
                                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        authenticationEmailService.sendEmailSignUpNumber(reqEmailSignUpNumberDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

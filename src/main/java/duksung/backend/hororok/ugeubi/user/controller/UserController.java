package duksung.backend.hororok.ugeubi.user.controller;

import duksung.backend.hororok.ugeubi.user.dto.request.ReqFindIdDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqModifyPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignInDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignUpDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResCheckUserIdDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResFindIdDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResUserInfoDto;
import duksung.backend.hororok.ugeubi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users/sign-in")
    public ResponseEntity<ResUserInfoDto> signIn(@RequestBody @Valid ReqSignInDto reqSingInDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(reqSingInDto));
    }

    @PostMapping("/users/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        userService.signUp(reqSignUpDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/users/check-id")
    public ResponseEntity<ResCheckUserIdDto> checkUserIdDuplication(@RequestParam("userId") String userId){
        if(userId == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.checkUserIdDuplication(userId));
    }

    @GetMapping("/users/find-id")
    public ResponseEntity<ResFindIdDto> findForgottenUserId(@RequestBody @Valid ReqFindIdDto reqFindIdDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.findForgottenUserId(reqFindIdDto));
    }

    @PatchMapping("/users/password")
    public ResponseEntity<Void> modifyUserPassword(@RequestBody @Valid ReqModifyPasswordDto reqModifyPasswordDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        userService.modifyUserPassword(reqModifyPasswordDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotEqualToAuthCodeException extends BaseException {

    public IsNotEqualToAuthCodeException(){
        this(HttpStatus.UNAUTHORIZED);
    }
    private IsNotEqualToAuthCodeException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("인증번호/코드가 일치하지 않습니다.")
                .build());
    }
}

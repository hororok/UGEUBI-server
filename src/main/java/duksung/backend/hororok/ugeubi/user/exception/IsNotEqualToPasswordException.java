package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotEqualToPasswordException extends BaseException {

    public IsNotEqualToPasswordException(){
        this(HttpStatus.UNAUTHORIZED);
    }

    private IsNotEqualToPasswordException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("비밀번호가 일치하지 않습니다.")
                .build());
    }
}

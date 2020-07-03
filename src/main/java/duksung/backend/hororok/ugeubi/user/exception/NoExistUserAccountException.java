package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NoExistUserAccountException extends BaseException {
    public NoExistUserAccountException(){
        this(HttpStatus.NOT_FOUND);
    }
    private NoExistUserAccountException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("일치하는 계정이 존재하지 않습니다.")
                .build());
    }
}

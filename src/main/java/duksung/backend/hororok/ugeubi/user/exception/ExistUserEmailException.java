package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistUserEmailException extends BaseException {

    public ExistUserEmailException(){
        this(HttpStatus.BAD_REQUEST);
    }
    private ExistUserEmailException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("이미 존재하는 이메일입니다.")
                .build());
    }
}

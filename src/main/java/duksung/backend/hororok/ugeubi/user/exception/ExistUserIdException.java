package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistUserIdException extends BaseException {

    public ExistUserIdException(){
        this(HttpStatus.BAD_REQUEST);
    }
    private ExistUserIdException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("이미 존재하는 아이디 입니다.")
                .build());
    }
}

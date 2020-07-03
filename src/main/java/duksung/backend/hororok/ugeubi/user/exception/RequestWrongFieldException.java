package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class RequestWrongFieldException extends BaseException {

    public RequestWrongFieldException(){
        this(HttpStatus.BAD_REQUEST);
    }
    private RequestWrongFieldException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("요청 필드의 형식이 올바르지 않습니다.")
                .build());
    }
}

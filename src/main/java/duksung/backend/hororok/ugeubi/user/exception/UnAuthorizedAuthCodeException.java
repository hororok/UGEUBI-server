package duksung.backend.hororok.ugeubi.user.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class UnAuthorizedAuthCodeException extends BaseException {

    public UnAuthorizedAuthCodeException(){
        this(HttpStatus.UNAUTHORIZED);
    }
    private UnAuthorizedAuthCodeException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("인증번호의 만료시간이 지났거나 인증번호를 보낸 이메일이 아닙니다.")
                .build());
    }
}

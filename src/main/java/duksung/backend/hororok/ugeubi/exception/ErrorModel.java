package duksung.backend.hororok.ugeubi.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorModel {
    private HttpStatus httpStatus;
    private String message;

    @Builder
    public ErrorModel(HttpStatus httpStatus, String message){
        this.httpStatus=httpStatus;
        this.message=message;
    }
}

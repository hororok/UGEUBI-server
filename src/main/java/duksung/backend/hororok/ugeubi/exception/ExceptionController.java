package duksung.backend.hororok.ugeubi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResExceptionDto> handleBaseException(BaseException e){
        ErrorModel errorModel = e.getErrorModel();

        return ResponseEntity.status(errorModel.getHttpStatus())
                .body(ResExceptionDto.builder()
                .errorModel(errorModel)
                .build());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Exception> handleRuntimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e);
    }
}

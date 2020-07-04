package duksung.backend.hororok.ugeubi.medicine.exception;

import duksung.backend.hororok.ugeubi.exception.BaseException;
import duksung.backend.hororok.ugeubi.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NoExistMedicineException extends BaseException {

    public NoExistMedicineException() {
        this(HttpStatus.NOT_FOUND);
    }

    private NoExistMedicineException(HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .httpStatus(httpStatus)
                .message("id에 일치하는 약이 존재하지 않습니다.")
                .build());
    }
}

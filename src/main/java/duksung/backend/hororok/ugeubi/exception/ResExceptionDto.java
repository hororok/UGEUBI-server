package duksung.backend.hororok.ugeubi.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResExceptionDto {
    private ErrorModel errorModel;

    @Builder
    public ResExceptionDto(ErrorModel errorModel){
        this.errorModel=errorModel;
    }
}

package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResFindIdDto {
    private String userId;

    @Builder
    public ResFindIdDto(String userId){
        this.userId = userId;
    }
}

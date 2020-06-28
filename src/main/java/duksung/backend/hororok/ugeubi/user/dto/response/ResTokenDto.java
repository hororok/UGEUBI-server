package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTokenDto {
    private String tokenType;
    private String accessToken;

    @Builder
    public ResTokenDto(String tokenType, String accessToken){
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }
}

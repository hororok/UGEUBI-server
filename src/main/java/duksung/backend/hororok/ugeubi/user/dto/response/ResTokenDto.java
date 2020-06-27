package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTokenDto {
    private String tokenType;
    private String accessToken;
    //private String refreshToken;

    @Builder
    public ResTokenDto(String tokenType, String accessToken){
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        //this.refreshToken = refreshToken;
    }
}

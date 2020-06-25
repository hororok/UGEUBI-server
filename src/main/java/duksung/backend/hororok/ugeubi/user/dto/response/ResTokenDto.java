package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTokenDto {
    private String accessToken;
    //private String refreshToken;

    @Builder
    public ResTokenDto(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        //this.refreshToken = refreshToken;
    }
}

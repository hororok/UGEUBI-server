package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserInfoDto {

    private ResTokenDto tokens;
    private ResUserDto user;

    @Builder
    public ResUserInfoDto(ResTokenDto tokens, ResUserDto user){
        this.tokens = tokens;
        this.user = user;
    }
}

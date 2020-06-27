package duksung.backend.hororok.ugeubi.user.dto.response;

import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserInfoDto {

    private ResTokenDto tokens;
    private UserInfo user;

    @Builder
    public ResUserInfoDto(ResTokenDto tokens, UserInfo user){
        this.tokens = tokens;
        this.user = user;
    }
}

package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserInfoDto {

    private String userId;
    private String userName;
    private String email;

    @Builder
    public ResUserInfoDto(String userId, String userName, String email){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}

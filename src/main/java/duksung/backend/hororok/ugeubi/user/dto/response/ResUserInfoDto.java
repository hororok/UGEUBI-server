package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserInfoDto {

    private String userName;
    private String email;
    private String phoneNumber;

    @Builder
    public ResUserInfoDto(String userName, String email, String phoneNumber){
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

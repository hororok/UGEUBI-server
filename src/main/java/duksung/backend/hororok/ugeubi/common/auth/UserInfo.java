package duksung.backend.hororok.ugeubi.common.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {

    private Long id;
    private String userId;
    private String userName;
    private String email;

    @Builder
    public UserInfo(Long id, String userId, String userName, String email){
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

}

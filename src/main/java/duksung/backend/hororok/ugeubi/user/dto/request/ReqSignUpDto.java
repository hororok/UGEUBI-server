package duksung.backend.hororok.ugeubi.user.dto.request;

import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqSignUpDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String userName;

    public User toEntity(){
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .userName(this.userName)
                .email(this.email)
                .build();
    }
}

package duksung.backend.hororok.ugeubi.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqVerifyFindPasswordDto {
    @NotNull
    private String userId;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String temporaryPassword;
}

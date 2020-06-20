package duksung.backend.hororok.ugeubi.user.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class ReqEmailFindPasswordDto {
    @NotNull
    private String userId;

    @NotNull
    @Email
    private String email;
}

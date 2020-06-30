package duksung.backend.hororok.ugeubi.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqFindIdDto {

    @NotNull
    private String userName;

    @NotNull
    @Email
    private String email;
}

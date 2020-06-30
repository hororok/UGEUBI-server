package duksung.backend.hororok.ugeubi.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqModifyPasswordDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;
}

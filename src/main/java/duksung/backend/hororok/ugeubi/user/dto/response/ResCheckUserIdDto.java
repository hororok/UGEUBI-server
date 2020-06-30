package duksung.backend.hororok.ugeubi.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResCheckUserIdDto {
    private String userId;
    private boolean available;

    @Builder
    public ResCheckUserIdDto(String userId, boolean available){
        this.userId = userId;
        this.available = available;
    }
}

package duksung.backend.hororok.ugeubi.fcmserver.dto;

import duksung.backend.hororok.ugeubi.fcmserver.domain.entity.DeviceToken;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeviceTokenDto {

    private Long userId;
    private String deviceToken;

    @Builder
    public DeviceTokenDto(Long userId, String deviceToken){
        this.userId=userId;
        this.deviceToken=deviceToken;
    }

    public DeviceToken toEntity(){
        return DeviceToken.builder()
                .userId(userId)
                .deviceToken(deviceToken)
                .build();

    }
}

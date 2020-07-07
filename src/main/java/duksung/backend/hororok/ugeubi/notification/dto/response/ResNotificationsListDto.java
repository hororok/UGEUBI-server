package duksung.backend.hororok.ugeubi.notification.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResNotificationsListDto {
    private List<ResListItemNotificationDto> notificationsList;

    @Builder
    public ResNotificationsListDto(List<ResListItemNotificationDto> notificationsList){
        this.notificationsList=notificationsList;
    }
}

package duksung.backend.hororok.ugeubi.notification.dto.response;

import duksung.backend.hororok.ugeubi.notification.domain.entity.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResListItemNotificationDto {

    private Long notificationId;

    private Long medicineId;

    private String medicineName;

    private String notificationDate;

    private String notificationTime;

    private NotificationType notificationType;

    @Builder
    private ResListItemNotificationDto(Long notificationId, Long medicineId, String medicineName, String notificationDate,
                                      String notificationTime, NotificationType notificationType){
        this.notificationId=notificationId;
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.notificationDate=notificationDate;
        this.notificationTime=notificationTime;
        this.notificationType=notificationType;
    }
}

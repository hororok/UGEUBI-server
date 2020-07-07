package duksung.backend.hororok.ugeubi.notification.dto;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
public class NotificationSaveRequestDTO {

    private Long userId;
    private Long medicineId;
    private String medicineName;
    private String notificationDate; //알람 날짜
    private String notificationTime; //-> null값 허용(유효기간인 경우)
    //private NotificationType notificationType;

    @Builder
    private NotificationSaveRequestDTO(Long userId, Long medicineId,
                                       String medicineName, String notificationDate,
                                       String notificationTime//, NotificationType notificationType
    ) {
        this.userId=userId;
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.notificationDate=notificationDate;
        this.notificationTime=notificationTime;
        this.notificationDate=notificationDate;
        //this.notificationType=notificationType;
    }

    public Notification toEntity() {
        return Notification.builder()
                .medicineId(medicineId)
                .userId(userId)
                .medicineName(medicineName)
                .notificationDate(notificationDate)
                .notificationTime(notificationTime)
                //.notificationType(notificationType)
                .build();
    }
}

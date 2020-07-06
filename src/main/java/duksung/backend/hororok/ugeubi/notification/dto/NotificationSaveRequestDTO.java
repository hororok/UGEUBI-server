package duksung.backend.hororok.ugeubi.notification.dto;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.notification.domain.entity.NotificationType;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
public class NotificationSaveRequestDTO {

    private Long notification_id;
    private Long userId;
    private Long medicineId;
    private String medicineName;
    private String notificationDate; //알람 날짜
    private String notificationTime; //-> null값 허용(유효기간인 경우)
    private NotificationType notificationType;

    @Builder
    private NotificationSaveRequestDTO(Long medicine_id, Long user_id, String medicine_name, String notification_date, String notification_time, NotificationType notification_type) {
        this.medicineId=medicine_id;
        this.userId=user_id;
        this.medicineName=medicine_name;
        this.notificationDate=notification_date;
        this.notificationTime=notification_time;
        this.notificationType=notification_type;
    }


    public Notification toEntity() {
        return Notification.builder()
                .medicineId(medicineId)
                .userId(userId)
                .medicineName(medicineName)
                .notificationDate(notificationDate)
                .notificationTime(notificationTime)
                .notificationType(notificationType)
                .build();
    }
}

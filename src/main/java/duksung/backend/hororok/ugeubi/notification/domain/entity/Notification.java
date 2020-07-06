package duksung.backend.hororok.ugeubi.notification.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.notification.dto.response.ResListItemNotificationDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
@Entity
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicineId;

    private String medicineName;

    private Long userId;

    private String notificationDate; //알람 날짜

    private String notificationTime; //-> null값 허용(유효기간인 경우)

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;


    @Builder
    private Notification(Long medicineId, String medicineName, Long userId, String notificationDate, String notificationTime, NotificationType notificationType){
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.userId=userId;
        this.notificationDate=notificationDate;
        this.notificationTime=notificationTime;
        this.notificationType=notificationType;
    }

    public ResListItemNotificationDto toSingleNotificationDto() {
        return ResListItemNotificationDto.builder()
                .notificationId(id)
                .medicineId(medicineId)
                .medicineName(medicineName)
                .notificationDate(notificationDate)
                .notificationTime(notificationTime)
                .notificationType(notificationType)
                .build();
    }
}



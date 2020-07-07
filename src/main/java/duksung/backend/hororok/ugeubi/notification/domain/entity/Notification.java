package duksung.backend.hororok.ugeubi.notification.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long medicineId;

    private String medicineName;

    private Long userId;

    private String notificationDate; //알람 날짜

    private String notificationTime; //-> null값 허용(유효기간인 경우)

    @Enumerated(EnumType.STRING)
    private Notification_type notificationType;

    public enum Notification_type{
        taking_time, valid_term //복용시간, 유효기간
    };

    @Builder
    private Notification(Long medicineId, String medicineName, Long userId, String notificationDate, String notificationTime, Notification_type notificationType){
        this.medicineId=medicineId;
        this.userId=userId;
        this.medicineName=medicineName;
        this.notificationDate=notificationDate;
        this.notificationTime=notificationTime;
        this.notificationType=notificationType;
    }

}



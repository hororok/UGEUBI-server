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

    private Long notification_id;
    private Long user_id;
    private Long medicine_id;
    private Date notification_date; //알람 날짜
    private Date notification_time; //-> null값 허용(유효기간인 경우)
    private Notification.Notification_type notification_type;

    @Builder
    private NotificationSaveRequestDTO(Long medicine_id, Long user_id, Date notification_date, Date notification_time, Notification.Notification_type notification_type) {
        this.medicine_id = medicine_id;
        this.user_id = user_id;
        this.notification_date = notification_date;
        this.notification_time = notification_time;
        this.notification_type = notification_type;
    }


    public Notification toEntity() {
        return Notification.builder()
                .medicine_id(medicine_id)
                .user_id(user_id)
                .notification_date(notification_date)
                .notification_time(notification_time)
                .notification_type(notification_type)
                .build();

    }
}

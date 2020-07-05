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
    private Long notification_id;

    private Long medicine_id;

    private String medicine_name;

    private Long user_id;

    private String notification_date; //알람 날짜

    private String notification_time; //-> null값 허용(유효기간인 경우)

    @Enumerated(EnumType.STRING)
    private Notification_type notification_type;

    public enum Notification_type{
        taking_time, valid_term //복용시간, 유효기간
    };

    @Builder
    private Notification(Long medicine_id, String medicine_name, Long user_id, String notification_date, String notification_time, Notification_type notification_type){
        this.medicine_id=medicine_id;
        this.user_id=user_id;
        this.medicine_name=medicine_name;
        this.notification_date=notification_date;
        this.notification_time=notification_time;
        this.notification_type=notification_type;
    }

}



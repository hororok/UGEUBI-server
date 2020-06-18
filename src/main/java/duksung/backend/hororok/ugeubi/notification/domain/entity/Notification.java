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

//    @ManyToOne
//    @JoinColumn(name ="medicine_id")
//    private Medicine medicine;

    private Long medicine_id;

    private Long user_id;

    @Temporal(TemporalType.DATE)
    private Date notification_date; //알람 날짜

    @Temporal(TemporalType.TIME)
    private Date notification_time; //-> null값 허용(유효기간인 경우)

    @Enumerated(EnumType.STRING)
    private Notification_type notification_type;

    public enum Notification_type{
        taking_time, valid_term //복용시간, 유효기간
    };

    @Builder
    private Notification(Long medicine_id, Long user_id, Date notification_date, Date notification_time, Notification_type notification_type){
        this.medicine_id=medicine_id;
        this.user_id=user_id;
        this.notification_date = notification_date;
        this.notification_time = notification_time;
        this.notification_type=notification_type;
    }

}



package duksung.backend.hororok.ugeubi.taking.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class TakingInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taking_info_id;

    private Long user_id;

    private Long medicine_id;

    private String medicine_name;

    @Temporal(TemporalType.TIME)
    private Date taking_time; //복용시간

    private String taking_day; //복용 요일

    @Temporal(TemporalType.DATE)
    private Date taking_period; //복용 기간

    private int taking_number; //복용 개수

    private boolean is_taken;

    @Builder
    private TakingInfo(Long medicine_id, String medicine_name, Date taking_time,String taking_day,
                       Date taking_period, int taking_number, boolean is_taken){
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.taking_time = taking_time;
        this.taking_day = taking_day;
        this.taking_period = taking_period;
        this.taking_number = taking_number;
        this.is_taken = is_taken;
    }
}

package duksung.backend.hororok.ugeubi.taking.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class TakingInfoSaveRequestDTO {
    /*
    private Long taking_info_id;
    private Long user_id;
    private Long medicine_id;
    private String medicine_name;
    private Date taking_time; //복용시간
    private String taking_day; //복용 요일
    private Date taking_period; //복용 기간
    private int taking_number; //복용 개수
    private boolean is_taken;

    @Builder
    private TakingInfoSaveRequestDTO(Long user_id, Long medicine_id, String medicine_name, Date taking_time,String taking_day,
                       Date taking_period, int taking_number){
        this.user_id = user_id;
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.taking_time = taking_time;
        this.taking_day = taking_day;
        this.taking_period = taking_period;
        this.taking_number = taking_number;
        this.is_taken = false;
    }

    public TakingInfoDay toEntity(){
        return TakingInfoDay.builder()
                .user_id(user_id)
                .medicine_id(medicine_id)
                .medicine_name(medicine_name)
                .taking_time(taking_time)
                .taking_day(taking_day)
                .taking_period(taking_period)
                .taking_number(taking_number)
                .build();
    }*/
}

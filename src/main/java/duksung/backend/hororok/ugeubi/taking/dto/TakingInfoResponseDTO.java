package duksung.backend.hororok.ugeubi.taking.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
public class TakingInfoResponseDTO {


    private Long taking_info_id;
    private Long user_id;
    private Long medicine_id;
    private String medicine_name;
    private Date taking_time; //복용시간
    private String taking_day; //복용 요일
    private Date taking_period; //복용 기간
    private int taking_number; //복용 개수
    private boolean is_taken;

    public TakingInfoResponseDTO(TakingInfo entity){
        this.medicine_id = entity.getMedicine_id();
        this.medicine_name = entity.getMedicine_name();
        this.taking_time = entity.getTaking_time();
        this.taking_day = entity.getTaking_day();
        this.taking_period = entity.getTaking_period();
        this.taking_number = entity.getTaking_number();
        this.is_taken = entity.is_taken();
    }

}

package duksung.backend.hororok.ugeubi.taking.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class TakingHistorySaveRequestDTO {


    private Long taking_history_id;
    private Long user_id;
    private Long taking_info_id;
    private Date taking_history_date;
    private boolean taking_history_is_taken;

    @Builder
    private TakingHistorySaveRequestDTO(Long user_id, Long taking_info_id, Date taking_history_date, boolean taking_history_is_taken ){
        this.user_id = user_id;
        this.taking_info_id = taking_info_id;
        this.taking_history_date = taking_history_date;
        this.taking_history_is_taken = taking_history_is_taken;
    }


    public TakingHistory toEntity(){
        return TakingHistory.builder()
                .user_id(user_id)
                .taking_info_id(taking_info_id)
                .taking_history_date(taking_history_date)
                .taking_history_is_taken(taking_history_is_taken)
                .build();

    }
}

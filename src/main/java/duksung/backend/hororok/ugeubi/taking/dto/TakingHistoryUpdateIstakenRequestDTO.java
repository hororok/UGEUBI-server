package duksung.backend.hororok.ugeubi.taking.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TakingHistoryUpdateIstakenRequestDTO {


    private Long taking_history_id;
    private boolean taking_history_is_taken;

    @Builder
    public TakingHistoryUpdateIstakenRequestDTO(Long taking_history_id, boolean taking_history_is_taken){
        this.taking_history_id = taking_history_id;
        this.taking_history_is_taken = taking_history_is_taken;
    }

}

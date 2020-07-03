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
public class TakingHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taking_history_id;

    private Long user_id;

    private Long taking_info_id;

    //@Temporal(TemporalType.DATE)
    private String taking_history_date;

    private boolean taking_history_is_taken;

    @Builder
    private TakingHistory(Long user_id, Long taking_info_id, String taking_history_date, boolean taking_history_is_taken ){
        this.user_id = user_id;
        this.taking_info_id = taking_info_id;
        this.taking_history_date = taking_history_date;
        this.taking_history_is_taken = taking_history_is_taken;
    }


}

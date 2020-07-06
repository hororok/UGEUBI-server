package duksung.backend.hororok.ugeubi.taking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class TakingInfoHistoryResponseDTO {


    private Long id;
    private Long userId;
    private Long medicineId;
    private Long taking_history_id;
    private String taking_history_date;
    private String medicineName; // 약 이름
    private String takingTime; // 복용 시간 hh:mm:ss
    private Integer takingNumber; // 복용 개수
    private boolean taking_history_is_taken; //약 먹었는지 체크


    public TakingInfoHistoryResponseDTO(Long id, Long userId, Long medicineId, Long taking_history_id, String taking_history_date,
                                        String medicineName, String takingTime, Integer takingNumber, boolean taking_history_is_taken ) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.taking_history_id = taking_history_id;
        this.taking_history_date = taking_history_date;
        this.medicineName = medicineName;
        this.takingTime = takingTime;
        this.takingNumber = takingNumber;
        this.taking_history_is_taken = taking_history_is_taken;
    }

}

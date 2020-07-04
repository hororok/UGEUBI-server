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
    private String medicineName; // 약 이름
    private String takingTime; // 복용 시간 hh:mm:ss
    private Integer takingNumber; // 복용 개수
    private boolean taking_history_is_taken; //약 먹었는지 체크

//
//    public TakingInfoHistoryResponseDTO(TakingInfoDay ti, TakingHistory th){
//        this.id = ti.getId();
//        this.userId = ti.getUserId();
//        this.medicineId = ti.getMedicineId();
//        this.taking_history_id = th.getTaking_history_id();
//        this.medicineName = ti.getMedicineName();
//        this.takingTime = ti.getTakingTime();
//        this.takingNumber = ti.getTakingNumber();
//        this.taking_history_is_taken = th.isTaking_history_is_taken();
//    }

    public TakingInfoHistoryResponseDTO(Long id, Long userId, Long medicineId, Long taking_history_id,
                                        String medicineName, String takingTime, Integer takingNumber, boolean taking_history_is_taken ) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.taking_history_id = taking_history_id;
        this.medicineName = medicineName;
        this.takingTime = takingTime;
        this.takingNumber = takingNumber;
        this.taking_history_is_taken = taking_history_is_taken;
    }

}

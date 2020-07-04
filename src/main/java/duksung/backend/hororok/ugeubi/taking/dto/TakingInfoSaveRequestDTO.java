package duksung.backend.hororok.ugeubi.taking.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class TakingInfoSaveRequestDTO {

    private Long id;
    private Long userId;
    private Long medicineId;
    private String medicineName; // 약 이름
    private String takingTime; // 복용 시간 hh:mm:ss
    private String takingDayOfWeek; // 복용 요일 월,화,수,목,금,토,일
    private Integer takingNumber; // 복용 개수

    @Builder
    private TakingInfoSaveRequestDTO(Long userId, Long medicineId, String medicineName, String takingTime,String takingDayOfWeek, int takingNumber){
        this.userId = userId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.takingTime = takingTime;
        this.takingDayOfWeek = takingDayOfWeek;
        this.takingNumber = takingNumber;
    }

    public TakingInfoDay toEntity(){
        return TakingInfoDay.builder()
                .userId(userId)
                .medicineId(medicineId)
                .medicineName(medicineName)
                .takingTime(takingTime)
                .takingDayOfWeek(takingDayOfWeek)
                .takingNumber(takingNumber)
                .build();
    }
}

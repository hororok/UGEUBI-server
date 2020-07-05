package duksung.backend.hororok.ugeubi.medicine.dto;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TakingInfoDayDto {
    private List<String> takingTime; // 복용 시간 hh:mm:ss

    private List<String> takingDayOfWeek; // 복용 요일 월,화,수,목,금,토,일

    private Integer takingNumber; // 복용 개수

    public List<TakingInfoDay> toEntities(Long userId, Medicine medicine) {
        List<TakingInfoDay> list = new ArrayList<>();

        for(String time : takingTime){
            for(String day : takingDayOfWeek){
                TakingInfoDay takingInfoDay =
                        TakingInfoDay.builder()
                        .userId(userId)
                        .medicineId(medicine.getId())
                        .medicineName(medicine.getMedicineName())
                        .takingTime(time)
                        .takingDayOfWeek(day)
                        .takingNumber(takingNumber)
                        .build();
                list.add(takingInfoDay);
            }
        }

        return list;
    }

    @Builder
    public TakingInfoDayDto(List<String> takingTime, List<String> takingDayOfWeek, Integer takingNumber){
        this.takingTime=takingTime;
        this.takingDayOfWeek=takingDayOfWeek;
        this.takingNumber=takingNumber;
    }
}

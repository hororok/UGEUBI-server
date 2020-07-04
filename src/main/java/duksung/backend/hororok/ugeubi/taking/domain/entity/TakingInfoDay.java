package duksung.backend.hororok.ugeubi.taking.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**약 복용을 요일로 설정한 경우**/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "taking_info_days")
public class TakingInfoDay extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long medicineId;

    private String medicineName;

    private String takingTime; // 복용 시간 hh:mm:ss

    private String takingDayOfWeek; // 복용 요일 월,화,수,목,금,토,일

    private Integer takingNumber; // 복용 개수


    @Builder
    private TakingInfoDay(Long userId, Long medicineId, String medicineName, String takingTime, String takingDayOfWeek, Integer takingNumber){
        this.userId=userId;
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.takingTime=takingTime;
        this.takingDayOfWeek=takingDayOfWeek;
        this.takingNumber=takingNumber;
    }

    public void modify(Long medicineId, String takingTime, String takingDayOfWeek, Integer takingNumber){
        this.medicineId=medicineId;
        this.takingTime=takingTime;
        this.takingDayOfWeek=takingDayOfWeek;
        this.takingNumber=takingNumber;
    }

}

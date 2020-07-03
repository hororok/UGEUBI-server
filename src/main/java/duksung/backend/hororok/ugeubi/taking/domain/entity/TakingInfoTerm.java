package duksung.backend.hororok.ugeubi.taking.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**약 복용을 기간으로 설정한 경우**/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "taking_info_terms")
public class TakingInfoTerm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long medicineId;

    private Integer takingTerm; // 복용 간격 며칠 1~7일

    private String takingTime;  // 복용 시간 hh:mm:ss

    private Integer takingNumber; // 복용 개수

    @Builder
    private TakingInfoTerm(Long userId, Long medicineId, Integer takingTerm, String takingTime, Integer takingNumber){
        this.userId=userId;
        this.medicineId=medicineId;
        this.takingTerm=takingTerm;
        this.takingTime=takingTime;
        this.takingNumber=takingNumber;
    }

    public void modify(Long medicineId, Integer takingTerm, String takingTime, Integer takingNumber){
        this.medicineId=medicineId;
        this.takingTerm=takingTerm;
        this.takingTime=takingTime;
        this.takingNumber=takingNumber;
    }

}

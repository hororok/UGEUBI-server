package duksung.backend.hororok.ugeubi.medicine.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "medicines")
@Entity
public class Medicine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String medicineName;

    @Enumerated(EnumType.STRING)
    private MedicineType medicineType; // 약 종류

    @Temporal(TemporalType.DATE)
    private Date medicineValidTerm; // 유효 기간, 복용 기간(처방약) yyyy-mm-dd

    private Boolean isTaken; // 복용 유무

    @Builder
    private Medicine(Long userId, String medicineName, MedicineType medicineType, Date medicineValidTerm, Boolean isTaken){
        this.userId=userId;
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isTaken=isTaken;
    }

    public void modify(String medicineName, MedicineType medicineType, Date medicineValidTerm, Boolean isTaken){
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isTaken=isTaken;
    }
}

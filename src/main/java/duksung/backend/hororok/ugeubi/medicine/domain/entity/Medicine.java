package duksung.backend.hororok.ugeubi.medicine.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.common.util.ParseString;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResListItemMedicineDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "medicines")
@Entity
public class Medicine extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String medicineName;

    @Enumerated(EnumType.STRING)
    private MedicineType medicineType; // 약 종류

    //@Temporal(TemporalType.DATE)
    private String medicineValidTerm; // 유효 기간, 복용 기간(처방약) yyyy-mm-dd

    private Boolean isTaken; // 복용 유무

    private String memo;

    @Builder
    private Medicine(Long userId, String medicineName, MedicineType medicineType, Date medicineValidTerm, Boolean isTaken, String memo){
        this.userId=userId;
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm.toString();
        this.isTaken=isTaken;
        this.memo=memo;
    }

    public void modify(String medicineName, MedicineType medicineType, Date medicineValidTerm, Boolean isTaken, String memo){
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm.toString();
        this.isTaken=isTaken;
        this.memo=memo;
    }

    public ResListItemMedicineDto toSingleMedicineDto(){
        return ResListItemMedicineDto.builder()
                .medicineId(id)
                .medicineName(medicineName)
                .medicineType(medicineType)
                .medicineValidTerm(ParseString.toDate(medicineValidTerm))
                .isTaken(isTaken)
                .memo(memo)
                .build();
    }
}

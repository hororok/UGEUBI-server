package duksung.backend.hororok.ugeubi.medicine.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import duksung.backend.hororok.ugeubi.medicine.domain.entity.MedicineType;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResListItemMedicineDto {

    private Long medicineId;

    private String medicineName;

    private MedicineType medicineType;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date medicineValidTerm;

    private Boolean isImminent; //유통기한 임박

    private Boolean isTaken;

    private String memo;

    @Builder
    public ResListItemMedicineDto(Long medicineId, String medicineName, MedicineType medicineType,
                                  Date medicineValidTerm, Boolean isTaken, String memo){
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isImminent=calculateImminent(medicineValidTerm);
        this.isTaken=isTaken;
        this.memo=memo;
    }

    private Boolean calculateImminent(Date medicineValidTerm) {
        Date today= new Date();
        if(today.getTime() <= medicineValidTerm.getTime()){
            long remainedMillis = medicineValidTerm.getTime()-today.getTime();
            long remainDays = remainedMillis / (24*60*60*1000);
            return remainDays<=5 ? true : false; //유통기한 5일 이하로 남은 경우 true
        }
        return false;
    }
}

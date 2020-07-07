package duksung.backend.hororok.ugeubi.medicine.dto.response;

import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResSingleMedicineDto {

    private String medicineName;

    private String medicineType;

    //@JsonFormat(pattern="yyyy-MM-dd")
    private String medicineValidTerm;

    private Boolean isTaken; // 복용 유무

    private String memo;

    private TakingInfoDayDto takingInfo;

    @Builder
    public ResSingleMedicineDto(String medicineName, String medicineType, String medicineValidTerm,
                                Boolean isTaken, String memo, TakingInfoDayDto takingInfoDayDto){
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isTaken=isTaken;
        this.memo=memo;
        this.takingInfo=takingInfoDayDto;
    }
}

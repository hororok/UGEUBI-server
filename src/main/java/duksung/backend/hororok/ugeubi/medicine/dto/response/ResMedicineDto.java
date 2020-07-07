package duksung.backend.hororok.ugeubi.medicine.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResMedicineDto {

    private Long medicineId;

    private String medicineName;

    private String medicineType;

    //@JsonFormat(pattern="yyyy-MM-dd")
    private String medicineValidTerm;

    private Boolean isTaken;

    private String memo;

    private TakingInfoDayDto takingInfo;

    @Builder
    public ResMedicineDto(Long medicineId, String medicineName, String medicineType, String medicineValidTerm,
                          Boolean isTaken, String memo, TakingInfoDayDto takingInfoDayDto){
        this.medicineId=medicineId;
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isTaken=isTaken;
        this.memo=memo;
        this.takingInfo= takingInfoDayDto;
    }
}

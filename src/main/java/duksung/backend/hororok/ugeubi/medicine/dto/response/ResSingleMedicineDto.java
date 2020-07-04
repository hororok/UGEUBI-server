package duksung.backend.hororok.ugeubi.medicine.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ResSingleMedicineDto {

    private String medicineName;

    private String medicineType;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date medicineValidTerm;

    private Boolean isTaken; // 복용 유무

    private String memo;

    private TakingInfoDayDto takingInfo;

    @Builder
    public ResSingleMedicineDto(String medicineName, String medicineType, Date medicineValidTerm,
                                Boolean isTaken, String memo, TakingInfoDayDto takingInfoDayDto){
        this.medicineName=medicineName;
        this.medicineType=medicineType;
        this.medicineValidTerm=medicineValidTerm;
        this.isTaken=isTaken;
        this.memo=memo;
        this.takingInfo=takingInfoDayDto;
    }
}

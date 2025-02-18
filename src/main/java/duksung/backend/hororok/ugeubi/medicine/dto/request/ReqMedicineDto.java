package duksung.backend.hororok.ugeubi.medicine.dto.request;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.MedicineType;
import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqMedicineDto {
    @NotNull
    private String medicineName;

    @NotNull
    private MedicineType medicineType;

    @NotNull
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private String medicineValidTerm;

    @NotNull
    private Boolean isTaken;

    private String memo;

    private TakingInfoDayDto takingInfoDayDto;
}

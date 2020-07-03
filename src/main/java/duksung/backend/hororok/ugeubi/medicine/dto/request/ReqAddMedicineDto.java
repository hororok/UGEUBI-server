package duksung.backend.hororok.ugeubi.medicine.dto.request;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqAddMedicineDto {
    @NotNull
    private String medicineName;

    @NotNull
    private MedicineType medicineType;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date medicineValidTerm;

    @NotNull
    private Boolean isTaken;


}

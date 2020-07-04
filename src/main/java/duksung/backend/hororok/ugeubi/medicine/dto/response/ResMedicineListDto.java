package duksung.backend.hororok.ugeubi.medicine.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResMedicineListDto {
    private List<ResListItemMedicineDto> medicineList;

    @Builder
    public ResMedicineListDto(List<ResListItemMedicineDto> medicineList){
        this.medicineList=medicineList;
    }
}

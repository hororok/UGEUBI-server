package duksung.backend.hororok.ugeubi.medicine.dto;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class MedicineDURRequestDTO {

    private String itemName;
    private Long pageNo;

    @Builder
    private MedicineDURRequestDTO(String itemName, Long pageNo){
        this.itemName = itemName;
        this.pageNo = pageNo;
    }

}

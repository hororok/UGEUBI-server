package duksung.backend.hororok.ugeubi.medicine.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

package duksung.backend.hororok.ugeubi.medicine.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Medicine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicine_id;

    private Long user_id;

    private String medicine_name; //이름 ITEM_NAME

    @Temporal(TemporalType.DATE)
    private Date medicine_valid_term; //유효기간

    private String medicine_class_no; //분류

    private String material_name; //원료성분

    private String EE_DOC_ID; //제조방법

    private String UD_DOC_ID; //용법용량

    private String NB_DOC_ID; //주의사항

    private String STORAGE_METHOD; //저장방법

    private String medicine_memo; //메모

    @Builder
    private Medicine(Long user_id, String medicine_name, Date medicine_valid_term, String medicine_class_no,
                     String material_name, String EE_DOC_ID, String UD_DOC_ID, String NB_DOC_ID, String STORAGE_METHOD, String medicine_memo){
        this.user_id = user_id;
        this.medicine_name = medicine_name;
        this.medicine_valid_term = medicine_valid_term;
        this.medicine_class_no = medicine_class_no;
        this.material_name = material_name;
        this.EE_DOC_ID = EE_DOC_ID;
        this.UD_DOC_ID = UD_DOC_ID;
        this.NB_DOC_ID = NB_DOC_ID;
        this.STORAGE_METHOD = STORAGE_METHOD;
        this.medicine_memo = medicine_memo;
    }


}

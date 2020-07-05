package duksung.backend.hororok.ugeubi.fcmserver.domain.entity;

import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResListItemMedicineDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DeviceToken")
@Entity
public class DeviceToken extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String deviceToken;

    @Builder
    private DeviceToken(Long userId, String deviceToken){
        this.userId=userId;
        this.deviceToken=deviceToken;
    }

}

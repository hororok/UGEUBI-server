package duksung.backend.hororok.ugeubi.fcmserver.domain.repository;

import duksung.backend.hororok.ugeubi.fcmserver.domain.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken,Long> {

    //SELECT DEVICE_TOKEN FROM DEVICE_TOKEN WHERE USER_ID IN (1,2)
    @Query("select distinct dt.deviceToken from DeviceToken dt where dt.userId in :userIdList")
    List<String> findTokenByUserId(@Param("userIdList")List<Long> todayTakingUserList);
}

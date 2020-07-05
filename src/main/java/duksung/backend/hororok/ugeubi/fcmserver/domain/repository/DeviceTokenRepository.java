package duksung.backend.hororok.ugeubi.fcmserver.domain.repository;

import duksung.backend.hororok.ugeubi.fcmserver.domain.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken,Long> {
}

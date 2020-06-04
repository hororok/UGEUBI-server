package duksung.backend.hororok.ugeubi.notification.domain.repository;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}

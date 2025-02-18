package duksung.backend.hororok.ugeubi.notification.domain.repository;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUserId(Long userId);
}

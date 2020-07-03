package duksung.backend.hororok.ugeubi.notification.domain.repository;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("select n from Notification n where n.user_id = ?1")
    List<Notification> findAllById(Long user_id);
}

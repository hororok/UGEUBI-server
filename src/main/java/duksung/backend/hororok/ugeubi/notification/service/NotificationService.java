package duksung.backend.hororok.ugeubi.notification.service;

import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.notification.domain.repository.NotificationRepository;
import duksung.backend.hororok.ugeubi.notification.dto.NotificationSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingHistoryRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    //알람 등록
    @Transactional
    public Long save(NotificationSaveRequestDTO requestDTO){
        return notificationRepository.save(requestDTO.toEntity()).getNotification_id();
    }

    //사용자의 알람 기록 가져오기
    public List<Notification> findAllById(Long id) {
        List<Notification> entity = notificationRepository.findAllById(id);
        return entity;
    }
}

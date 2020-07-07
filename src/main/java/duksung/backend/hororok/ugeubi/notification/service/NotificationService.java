package duksung.backend.hororok.ugeubi.notification.service;

import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.notification.domain.repository.NotificationRepository;
import duksung.backend.hororok.ugeubi.notification.dto.response.ResListItemNotificationDto;
import duksung.backend.hororok.ugeubi.notification.dto.response.ResNotificationsListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.util.Date;
=======
import java.util.Comparator;
>>>>>>> b04757d69a0e7b0a00c66aa6a4c5c90c65f787a6
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

<<<<<<< HEAD
=======
    //알람 등록
    /*@Transactional
    public Long save(NotificationSaveRequestDTO requestDTO){
        return notificationRepository.save(requestDTO.toEntity()).getNotification_id();
    }*/

>>>>>>> b04757d69a0e7b0a00c66aa6a4c5c90c65f787a6
    //사용자의 알람 기록 가져오기
    public ResNotificationsListDto getUsersNotificationsList(UserInfo userInfo) {
        List<Notification> notificationList = notificationRepository.findAllByUserId(userInfo.getId());

        List<ResListItemNotificationDto> resNotificationList =
                notificationList.stream()
                        .sorted(Comparator.comparing(BaseTimeEntity::getCreatedTime).reversed())
                        .map(notification -> notification.toSingleNotificationDto())
                        .collect(Collectors.toList());

        return ResNotificationsListDto.builder()
                .notificationsList(resNotificationList)
                .build();
    }

    //알람 등록
    @Transactional
    public void registerNotifications(NotificationSaveRequestDTO requestDTO){
        //return notificationRepository.save(requestDTO.toEntity()).getNotification_id();
    }
}

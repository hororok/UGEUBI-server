package duksung.backend.hororok.ugeubi.notification.controller;

import duksung.backend.hororok.ugeubi.common.auth.LoginUserInfo;
import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.notification.dto.NotificationSaveRequestDTO;
import duksung.backend.hororok.ugeubi.notification.service.NotificationService;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.service.TakingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

<<<<<<< Updated upstream
    //알람 등록
    @PostMapping("/registerNotifications")
    public Long save(@RequestBody NotificationSaveRequestDTO requestDTO) {
        return notificationService.save(requestDTO);
    }

=======
>>>>>>> Stashed changes
    //사용자의 알람 기록 가져오기
    @GetMapping("/getNotifications")
    public List<Notification> findById(@LoginUserInfo UserInfo userInfo) {
        return notificationService.findAllById(userInfo.getId());
    }
}

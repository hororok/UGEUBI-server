package duksung.backend.hororok.ugeubi.notification.controller;

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

    //알람 등록
    @PostMapping("/registerNotifications")
    public Long save(@RequestBody NotificationSaveRequestDTO requestDTO) {
        return notificationService.save(requestDTO);
    }

    //사용자의 알람 기록 가져오기
    @GetMapping("/getNotifications")
    public List<Notification> findById(@RequestParam(value = "id") Long id) {
        return notificationService.findAllById(id);
    }
}

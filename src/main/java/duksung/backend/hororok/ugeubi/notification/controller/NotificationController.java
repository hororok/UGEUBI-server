package duksung.backend.hororok.ugeubi.notification.controller;

import duksung.backend.hororok.ugeubi.common.auth.LoginUserInfo;
import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.notification.dto.response.ResNotificationsListDto;
import duksung.backend.hororok.ugeubi.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    //알람 등록
    /*@PostMapping("/notifications")
    public Long save(@RequestBody NotificationSaveRequestDTO requestDTO) {
        return notificationService.save(requestDTO);
    }*/

    //사용자의 알람 기록 가져오기
    @GetMapping("/notifications")
    public ResponseEntity<ResNotificationsListDto> getUsersNotificationsList(@LoginUserInfo UserInfo userInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getUsersNotificationsList(userInfo));
    }
}

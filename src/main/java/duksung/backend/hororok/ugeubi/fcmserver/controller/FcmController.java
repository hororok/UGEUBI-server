package duksung.backend.hororok.ugeubi.fcmserver.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import duksung.backend.hororok.ugeubi.common.auth.LoginUserInfo;
import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.fcmserver.dto.DeviceTokenDto;
import duksung.backend.hororok.ugeubi.fcmserver.service.AndroidPushNotificationsService;
import duksung.backend.hororok.ugeubi.fcmserver.service.AndroidPushPeriodicNotifications;
import duksung.backend.hororok.ugeubi.fcmserver.service.DeviceTokenService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;



@RestController
public class FcmController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    DeviceTokenService deviceTokenService;

    //디바이스 토큰 저장
    @PostMapping("/registerDeviceToken")
    public Long registerDeviceToken(@RequestBody String deviceToken, @LoginUserInfo UserInfo userInfo) {
        DeviceTokenDto deviceTokenDto = new DeviceTokenDto(userInfo.getId(), deviceToken);
        return deviceTokenService.save(deviceTokenDto);
    }

    //복용약 정보 체크해서 알림
    @Scheduled(cron="0 * * * * *") //매 1분마다 수행 (하루에 1440회)
    @GetMapping(value = "/takingInfoSend")
    public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException  {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    //유효기간 알림 (당일)
}
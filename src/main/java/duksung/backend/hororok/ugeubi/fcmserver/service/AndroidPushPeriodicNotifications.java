package duksung.backend.hororok.ugeubi.fcmserver.service;

import duksung.backend.hororok.ugeubi.notification.service.NotificationService;
import duksung.backend.hororok.ugeubi.taking.controller.TakingController;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoDayRepository;
import duksung.backend.hororok.ugeubi.taking.service.TakingInfoService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AndroidPushPeriodicNotifications {

    private final TakingInfoDayRepository takingInfoDayRepository;
    private final DeviceTokenService deviceTokenService;
    private final NotificationService notificationService;

    public String PeriodicNotificationJson() throws JSONException {

        LocalDate localDate = LocalDate.now();

        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기

        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        String today = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
        System.out.println("현재 요일: " + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1]);

        SimpleDateFormat df = new SimpleDateFormat ( "HH:mm");
        Calendar time = Calendar.getInstance();
        String current_time = df.format(time.getTime());

        //현재 요일과 시간을 고려해서 알람 받아야 할 유저 정보를 가져옴
        List<Long> todayTakingUserList = takingInfoDayRepository.findUserIdByTaking_day(today, current_time);

        //알람을 받을 토큰 리스트
        List<String> tokenList = deviceTokenService.findTokenByUserId(todayTakingUserList);

        JSONObject body = new JSONObject();

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenList.size(); i++) {
            array.put(tokenList.get(i));
        }

        body.put("registration_ids", array);

        //알람 보낼 내용
        JSONObject notification = new JSONObject();
        notification.put("title","복용약 알림");
        notification.put("body","약 먹을 시간입니다.");

        body.put("notification", notification);

        System.out.println(body.toString());

        //알람 정보 저장 registerNotifications

        return body.toString();
    }
}
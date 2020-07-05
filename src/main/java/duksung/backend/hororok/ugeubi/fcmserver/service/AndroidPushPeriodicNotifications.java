package duksung.backend.hororok.ugeubi.fcmserver.service;

import duksung.backend.hororok.ugeubi.notification.service.NotificationService;
import duksung.backend.hororok.ugeubi.taking.controller.TakingController;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.service.TakingInfoService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
public class AndroidPushPeriodicNotifications {

    private static TakingInfoService takingInfoService;
    private static DeviceTokenService deviceTokenService;
    private static NotificationService notificationService;

    public static String PeriodicNotificationJson() throws JSONException {

        LocalDate localDate = LocalDate.now();

        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기

        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        String today = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
        System.out.println("현재 요일: " + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1]);

        SimpleDateFormat df = new SimpleDateFormat ( "HH:mm");
        Calendar time = Calendar.getInstance();
        String current_time = df.format(time.getTime());

        System.out.println("test****"+today);  //월 화 수...
        System.out.println("test****"+current_time); //02:07

        //현재 요일과 시간을 고려해서 알람 받아야 할 유저 정보를 가져옴
        List<Long> todayTakingUserList = takingInfoService.findUserIdByTaking_day(today, current_time);
       // List<Long> todayTakingUserList = takingInfoService.findUserIdByTaking_day("월", "02:20");
        System.out.println("test++++null?"+todayTakingUserList.get(0));

        //알람을 받을 토큰 리스트
        List<String> tokenList = deviceTokenService.findTokenByUserId(todayTakingUserList);

        //알람을 받을 토큰 리스트
       // String sampleData[] = {"cPden3sLQKCuw8BVfDhhjl:APA91bGyJ2B0zP54Pty1xWl9Lr1R7xQKEXikGNlJXPloJonaBpQa_Up4O-1j-LUzKJmmmlEtQooqsusqzE0PfgxmPaCYoy7hzMaQjKLXISYEGmkYoo4rpxQzpBL6IEmOXGDXhcflkSez"};

        JSONObject body = new JSONObject();

       // List<String> tokenlist = new ArrayList<String>(); //알림을 보낼 디바이스의 디바이스토큰을 넣는 list

//        for(int i=0; i<sampleData.length; i++){
//            tokenlist.add(sampleData[i]);
//        }

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
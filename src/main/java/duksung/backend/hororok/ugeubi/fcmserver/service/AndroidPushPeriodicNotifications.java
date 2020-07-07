package duksung.backend.hororok.ugeubi.fcmserver.service;


import duksung.backend.hororok.ugeubi.fcmserver.domain.repository.DeviceTokenRepository;
import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import duksung.backend.hororok.ugeubi.medicine.domain.repository.MedicineRepository;
import duksung.backend.hororok.ugeubi.notification.domain.entity.Notification;
import duksung.backend.hororok.ugeubi.notification.domain.entity.NotificationType;
import duksung.backend.hororok.ugeubi.notification.domain.repository.NotificationRepository;
import duksung.backend.hororok.ugeubi.notification.dto.NotificationSaveRequestDTO;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AndroidPushPeriodicNotifications {

    private final TakingInfoDayRepository takingInfoDayRepository;
    private final MedicineRepository medicineRepository;
    private final DeviceTokenRepository deviceTokenRepository;
    private final NotificationRepository notificationRepository;

    public String PeriodicNotificationJson() throws JSONException, UnsupportedEncodingException {

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
        List<String> tokenList = deviceTokenRepository.findTokenByUserId(todayTakingUserList);

        if(tokenList.size()<=0){
            return null;
        }

        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        for(int i=0; i<tokenList.size(); i++) {
            array.put(tokenList.get(i));
        }

        body.put("registration_ids", array);

        //알람 보낼 내용
        JSONObject notification = new JSONObject();
        String title   = URLEncoder.encode("복용약 알림"  ,"UTF-8"); // 한글깨짐으로 URL인코딩해서 보냄
        String content = URLEncoder.encode("약 먹을 시간입니다.","UTF-8");

        notification.put("title", title);
        notification.put("body", content);

        body.put("notification", notification);

        System.out.println(body.toString());

        //알람 정보 저장 registerNotifications
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd");

        List<TakingInfoDay> takingInfoDayList = takingInfoDayRepository.findAllByTakingDayOfWeekAndAndTakingTime(today,current_time);
        takingInfoDayList.forEach(takingInfoDay -> {
            NotificationSaveRequestDTO notificationSaveRequestDTO =
                    NotificationSaveRequestDTO.builder()
                        .userId(takingInfoDay.getUserId())
                        .medicineId(takingInfoDay.getMedicineId())
                        .medicineName(takingInfoDay.getMedicineName())
                        .notificationDate(dateFormat.format(oCalendar.getTime()))
                        .notificationTime(takingInfoDay.getTakingTime())
                        .notificationType(NotificationType.TAKING_TIME)
                        .build();
                notificationRepository.save(notificationSaveRequestDTO.toEntity());
            });

        return body.toString();
    }

    public String validTermNotificationJson() throws JSONException, ParseException, UnsupportedEncodingException {

        SimpleDateFormat df = new SimpleDateFormat ( "yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        String medicineValidTerm = df.format(date.getTime());

        //현재 날짜를 고려해서 알람 받아야 할 유저 정보를 가져옴
        List<Long> todayUserList = medicineRepository.findUserIdValidTerm(medicineValidTerm);

        //알람을 받을 토큰 리스트
        List<String> tokenList = deviceTokenRepository.findTokenByUserId(todayUserList);

        if(tokenList.size()<=0){
            return null;
        }

        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        for(int i=0; i<tokenList.size(); i++) {
            array.put(tokenList.get(i));
        }

        body.put("registration_ids", array);

        //알람 보낼 내용
        JSONObject notification = new JSONObject();
        String title   = URLEncoder.encode("유효기간 약 알림"  ,"UTF-8"); // 한글깨짐으로 URL인코딩해서 보냄
        String content = URLEncoder.encode("약의 유효기간을 확인하세요!","UTF-8");

        notification.put("title", title);
        notification.put("body", content);

        body.put("notification", notification);

        System.out.println(body.toString());

        //알람 정보 저장 registerNotifications

        List<Medicine> medicineList = medicineRepository.findAllByValidTerm(medicineValidTerm);

        medicineList.stream().forEach(Medicine -> {
            NotificationSaveRequestDTO requestDTO = NotificationSaveRequestDTO.builder()
                    .medicineId(Medicine.getId())
                    .userId(Medicine.getUserId())
                    .medicineName(Medicine.getMedicineName())
                    .notificationDate(medicineValidTerm)
                    .notificationTime(null)
                    .notificationType(NotificationType.VALID_TERM)
                    .build();

            notificationRepository.save(requestDTO.toEntity());
        });


        return body.toString();
    }

}
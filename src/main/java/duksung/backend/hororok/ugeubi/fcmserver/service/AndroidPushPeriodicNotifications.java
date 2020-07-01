package duksung.backend.hororok.ugeubi.fcmserver.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"cPden3sLQKCuw8BVfDhhjl:APA91bGyJ2B0zP54Pty1xWl9Lr1R7xQKEXikGNlJXPloJonaBpQa_Up4O-1j-LUzKJmmmlEtQooqsusqzE0PfgxmPaCYoy7hzMaQjKLXISYEGmkYoo4rpxQzpBL6IEmOXGDXhcflkSez"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>(); //알림을 보낼 디바이스의 디바이스토큰을 넣는 list

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title","hello!");
        notification.put("body","Today is "+localDate.getDayOfWeek().name()+"!");

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}
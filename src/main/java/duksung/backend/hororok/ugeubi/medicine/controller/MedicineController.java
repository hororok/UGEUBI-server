package duksung.backend.hororok.ugeubi.medicine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/medicines", produces = "text/plain;charset=UTF-8")
public class MedicineController {

    //DUR정보 API RequestParam : 품목명, DUR유형
    @GetMapping("/DURPrdlstInfoService")
    public void DURPrdlstInfoService(@RequestParam(value = "itemName", required = false) String itemName, @RequestParam(value = "typeName", required = false) String typeName)throws IOException {

        //DUR 품목정보 key
        String DURPrdlstInfoServiceKey = "P9uW81CUZfBBczChK71KSYj%2FQqbwLfIKKPjaAHarPSUAp7Irn17Q0zF9d4zLDFfCcm7pGFZQtBvdrHOnqhCaJQ%3D%3D";


        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/");

        System.out.println("typeName"+typeName);
       // typeName = "병용금기";
       // itemName = "아스피도캡슐";
        if(typeName == null){
            //DUR품목정보 서비스
            urlBuilder.append("getDurPrdlstInfoList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        }
        else {
            //병용금기 정보조회
            if(typeName.equals("병용금기")) {
                urlBuilder.append("getUsjntTabooInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode(typeName, "UTF-8")); /*DUR유형*/
            }
            //특정연령대금기 정보조회
            else if(typeName.equals("특정연령대금기")){
                urlBuilder.append("getSpcifyAgrdeTabooInfoList");
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("특정연령대금기", "UTF-8")); /*DUR유형*/
            }
            //임부금기 정보조회
            else if(typeName.equals("임부금기")){
                urlBuilder.append("getPwnmTabooInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            //용량주의 정보조회
            else if(typeName.equals("용량주의")){
                urlBuilder.append("getCpctyAtentInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            //투여기간주의 정보조회
            else if(typeName.equals("투여기간주의")){
                urlBuilder.append("getMdctnPdAtentInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            //노인주의 정보조회
            else if(typeName.equals("노인주의")){
                urlBuilder.append("getOdsnAtentInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            //효능군중복 정보조회
            else if(typeName.equals("효능군중복")){
                urlBuilder.append("getEfcyDplctInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            //서방정분할주의 정보조회
            else if(typeName.equals("서방정분할주의")){
                urlBuilder.append("getSeobangjeongPartitnAtentInfoList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("typeName","UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/
            }
            if(itemName != null)
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8")); /*품목명*/
        }

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}

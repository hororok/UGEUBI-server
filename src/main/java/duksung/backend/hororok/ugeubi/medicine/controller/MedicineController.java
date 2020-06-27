package duksung.backend.hororok.ugeubi.medicine.controller;

import duksung.backend.hororok.ugeubi.medicine.dto.MedicineDURRequestDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/medicine", produces = "text/plain;charset=UTF-8")
public class MedicineController {

    private  static String DURPrdlstInfoServiceKey = "P9uW81CUZfBBczChK71KSYj%2FQqbwLfIKKPjaAHarPSUAp7Irn17Q0zF9d4zLDFfCcm7pGFZQtBvdrHOnqhCaJQ%3D%3D";

    //[병용금기] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getUsjntTabooInfoList")
    public String getUsjntTabooInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("병용금기", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }


    //[특정연령대금기] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getSpcifyAgrdeTabooInfoList")
    public String getSpcifyAgrdeTabooInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getSpcifyAgrdeTabooInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("특정연령대금기", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[임부금기] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getPwnmTabooInfoList")
    public String getPwnmTabooInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getPwnmTabooInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("임부금기", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }


    //[용량주의] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getCpctyAtentInfoList")
    public String getCpctyAtentInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getCpctyAtentInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("용량주의", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[투여기간주의] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getMdctnPdAtentInfoList")
    public String getMdctnPdAtentInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getMdctnPdAtentInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("투여기간주의", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[노인주의] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getOdsnAtentInfoList")
    public String getOdsnAtentInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getOdsnAtentInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("노인주의", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[효능군중복] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getEfcyDplctInfoList")
    public String getEfcyDplctInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getEfcyDplctInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("효능군중복", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[서방형 제제 분할주의] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getSeobangjeongPartitnAtentInfoList")
    public String getSeobangjeongPartitnAtentInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getSeobangjeongPartitnAtentInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("typeName", "UTF-8") + "=" + URLEncoder.encode("분할주의", "UTF-8")); /*DUR유형*/

        if(itemName != null)
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }

    //[DUR품목정보 조회] DUR정보 API RequestParam : 품목명(itemName), 페이지번호(pageNo)
    @PostMapping("/DURPrdlstInfoService/getDurPrdlstInfoList")
    public String getDurPrdlstInfoList(@RequestBody MedicineDURRequestDTO requestDTO)throws IOException {

        String itemName = requestDTO.getItemName();
        Long pageNo = requestDTO.getPageNo();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getDurPrdlstInfoList");
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + DURPrdlstInfoServiceKey); /*Service Key*/

        //***품목명이 필수 값으로 들어가야함***
       urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8"));/*품목명*/

        if(pageNo != null)
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); /*페이지 번호*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("url code: " + url);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        //xml to json
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        JSONObject jsonBody = xmlJSONObj.getJSONObject("response").getJSONObject("body");

        if(jsonBody.get("items") != "")
            return jsonBody.getJSONObject("items").toString();
        else
            return "{}";
    }
}

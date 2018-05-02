package com.gnstkd95.cafego;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alfo6-7 on 2018-05-01.
 */

public class APIExamMemberProfile {
    public static void queryTest(String accessToken) {
        HttpRequestManager requestManager = new HttpRequestManager();
        try {
            HttpRequestManager.VariableList variableList = new HttpRequestManager.VariableList("UTF-8");
            variableList.add("Authorization", "Bearer " + accessToken);
            HttpRequestManager.URLConnectionData data = requestManager.setRequestDetails("GET", "https", "openapi.naver.com/v1/nid/me", variableList);
            String result = requestManager.request(data);
            JSONObject rootObj = new JSONObject(result);
            JSONObject responseObj = rootObj.getJSONObject("response");
            String id = (String)responseObj.get("id");
            String nickname = (String)responseObj.get("nickname");
            Log.d("console", id + ", " + nickname);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




































    public static void main(String args) {
        String token = args;// 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
package com.traffic.api.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class PushNotificationService {

    private URL url;
    private HttpURLConnection con;
    private String topic = "meteoAll";

    public Map<String, String> subscribeTokenToTopic(String token) {
        Map <String, String> map = new HashMap<String, String>();
        int responseCode = 0;
        try {
            url = new URL("https://iid.googleapis.com/iid/v1/" + token + "/rel/topics/" + topic);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "Bearer " + "AAAAU3MO_rE:APA91bE86_7hUFEOtwJkHrOJGDf572I8TJEx5eH7YeWRGl6x3GpJJPdNHUEQTUwAxO9YL47lkgD6WpI9EdS8q9VUx6xQArsiBHxCasxHNvYs0QZGtzlgyQOw6JEcoqk3W4e3SDhf_1b6");
            con.setRequestMethod("POST");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.close();
            responseCode = con.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            map.put("status", "success");
            return map;
        }
        else{
            map.put("status", "error");
            return map;
        }
    }
}
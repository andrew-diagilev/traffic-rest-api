package com.traffic.api.services;


import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PushNotificationService {

    private URL url;
    private HttpURLConnection con;
    private String topic = "meteoAll";

    public String subscribeTokenToTopic(String token) {
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
            return new String("Підписка на тривожні повідомлення виконана");
        }
        else{
            return new String("Помилка підписки");
        }
    }
}
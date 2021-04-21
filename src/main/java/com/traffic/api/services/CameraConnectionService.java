package com.traffic.api.services;


import javassist.bytecode.ByteArray;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
@Service
public class CameraConnectionService {

    private URL url;
    private HttpURLConnection con;

    public CameraConnectionService() {
    }

    public CameraConnectionService(String urltext, String requestBody, String method, String auth) {
        try {
            url = new URL(urltext);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "Basic " + auth);
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Language", "en-US");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public byte[] doRequest() throws IOException{
        InputStream is = this.con.getInputStream();
          /*  byte[] buffer = new byte[500999];
            int byteReaded = is.read(buffer);
            while (byteReaded != 0) {
                baos.write(buffer, 0, byteReaded);
            }*/
            return IOUtils.toByteArray(is);

    }
}
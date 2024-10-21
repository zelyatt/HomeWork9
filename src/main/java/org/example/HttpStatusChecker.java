package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {

    public String getStatusImage(int code) throws Exception {
        String imageUrl = "https://http.cat/" + code + ".jpg";
        URL url = new URL(imageUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == 404) {
            throw new Exception("There is no image for HTTP status " + code);
        }
        return imageUrl;
    }

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            String imageUrl = checker.getStatusImage(200);
            System.out.println("Image URL: " + imageUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


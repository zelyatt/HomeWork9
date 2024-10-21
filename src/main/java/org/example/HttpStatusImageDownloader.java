package org.example;
import java.io.*;
import java.net.URL;

public class HttpStatusImageDownloader {

    private HttpStatusChecker checker = new HttpStatusChecker();

    public void downloadStatusImage(int code) throws Exception {
        try {
            String imageUrl = checker.getStatusImage(code);
            URL url = new URL(imageUrl);

            InputStream in = url.openStream();
            FileOutputStream out = new FileOutputStream(code + ".jpg");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            out.close();

            System.out.println("Image downloaded as " + code + ".jpg");
        } catch (Exception e) {
            throw new Exception("Failed to download image for status code: " + code);
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

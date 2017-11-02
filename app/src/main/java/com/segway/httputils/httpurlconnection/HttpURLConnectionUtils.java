package com.segway.httputils.httpurlconnection;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author No.47 create at 2017/11/2.
 */
public class HttpURLConnectionUtils {
    public static final String TAG = HttpURLConnectionUtils.class.getSimpleName();

    public static void get() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.juheapi.com/japi/toh?key=eff36bdaeeb868a6b8057a34f32d1326&v=1.0&month=11&day=1");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(true);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setConnectTimeout(3000);
                    if (httpURLConnection.getResponseCode() == 200) {
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(httpURLConnection.getInputStream()));
                        String msg = "", line;
                        while ((line = bufferedReader.readLine()) != null) {
                            msg += line + "\n";
                            Log.d(TAG, line);
                        }
                        bufferedReader.close();
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void post() {
        final Map<String, String> map = new HashMap<>();
        map.put("key","eff36bdaeeb868a6b8057a34f32d1326");
        map.put("v","1.0");
        map.put("month","11");
        map.put("day","1");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuffer sbRequest = new StringBuffer();
                if (map != null && map.size() > 0) {
                    for (String key : map.keySet()) {
                        sbRequest.append(key + "=" + map.get(key) + "&");
                    }
                }
                String request = sbRequest.substring(0, sbRequest.length() - 1);
                try {
                    URL url = new URL("http://api.juheapi.com/japi/toh");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(request.getBytes());
                    os.flush();
                    if (httpURLConnection.getResponseCode() == 200) {
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(httpURLConnection.getInputStream()));
                        String msg = "", line;
                        while ((line = bufferedReader.readLine()) != null) {
                            msg += line + "\n";
                            Log.d(TAG, line);
                        }
                        bufferedReader.close();
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}

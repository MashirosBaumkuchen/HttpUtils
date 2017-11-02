package com.segway.httputils.httpclient;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author No.47 create at 2017/11/2.
 */
public class HttpClientUtils {
    public static final String TAG = HttpClientUtils.class.getSimpleName();

    public static void get() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HttpClient httpCient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://api.juheapi.com/japi/toh?key=eff36bdaeeb868a6b8057a34f32d1326&v=1.0&month=11&day=1");
                try {
                    HttpResponse httpResponse = null;
                    httpResponse = httpCient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Log.d(TAG, response.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void post(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://api.juheapi.com/japi/toh");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("key", "eff36bdaeeb868a6b8057a34f32d1326"));
                params.add(new BasicNameValuePair("v", "1.0"));
                params.add(new BasicNameValuePair("month","11"));
                params.add(new BasicNameValuePair("day","1"));
                try {
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params);
                    httpPost.setEntity(urlEncodedFormEntity);
                    HttpResponse httpResponse = null;
                    httpResponse = httpClient.execute(httpPost);
                    HttpEntity entity = httpResponse.getEntity();
                    if (entity != null){
                        Log.d(TAG,EntityUtils.toString(entity));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
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

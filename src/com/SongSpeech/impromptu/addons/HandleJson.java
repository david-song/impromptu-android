package com.SongSpeech.impromptu.addons;

import android.util.Log;

import org.apache.http.HttpConnection;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by david on 9/24/14.
 */
public class HandleJson {
    private String quote;
    private String catagory;
    private String tags;
    public volatile boolean parsingComplete = true;
    private String urlString = null;

    public HandleJson(String url)
    {
        this.urlString = url;
    }
    public String getQuote()
    {
        return quote;
    }

    public String getCatagory() {
        return catagory;
    }

    public String getTags() {
        return tags;
    }

    public void readAndParseJson(String in)
    {
        try {
            JSONObject reader = new JSONObject(in);
            quote = reader.getString("quote");
            catagory = reader.getString("source");
            tags = reader.getString("tags");

            parsingComplete = false;

            Log.i("WordActivity", quote + catagory + tags);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void fetchJson()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    String data = convertStreamToString(inputStream);

                    readAndParseJson(data);
                    inputStream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
    static String convertStreamToString(InputStream is)
    {
        java.util.Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next(): "";
    }
}

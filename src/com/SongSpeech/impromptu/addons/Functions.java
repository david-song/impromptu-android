package com.SongSpeech.impromptu.addons;

import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

/**
 * Created by david on 9/26/14.
 */
public class Functions {

    public static void readFile(String filename)
    {

    }

    public static String getiheartPhrase(String url)
    {
        String sa = null;
        HandleJson obj = new HandleJson(url);
        obj.fetchJson();
        while(obj.parsingComplete);

        sa = obj.getQuote();
        return sa;
    }
    public void getMashJson() {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://andruxnet-random-famous-quotes.p.mashape.com/cat=movies")
                    .header("X-Mashape-Key", "QLRJu3fLWlmshLBOp0IanDy7XkwYp1OkZ3Hjsn7CpvJcYaSN55")
                    .asJson();
            Unirest.shutdown();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getRandomWord()
    {
        String word = null;
        try
        {
            URL url = new URL("http://randomword.setgetgo.com/get.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            word = HandleJson.convertStreamToString(inputStream);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }
}

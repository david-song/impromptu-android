package com.SongSpeech.impromptu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PhraseActivity extends Activity {
    int phrasenum = 0;
    AssetManager am;
    BufferedReader dict = null;
    ArrayAdapter<String> adapter;
    ListView list;
    ArrayList<String> activephrases;
    int phrasetime;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    TextView textView;
    CountDownTimer count;
    Button refresh;
    private ArrayList<String> phrasearray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.phraseactivity);

        Button refresh = (Button) findViewById(R.id.phrase_refreshbutton);
        builder = new AlertDialog.Builder(this);
        list = (ListView) findViewById(R.id.phrase_list_view);
        phrasearray = new ArrayList<String>();
        activephrases = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(PhraseActivity.this, R.layout.settings_listview_item, activephrases);
        list.setAdapter(adapter);
        am = this.getAssets();

        readSettings();
        CreatePhraseList();
        addListViews();

        final CountDownTimer count = new CountDownTimer((60000 * phrasetime),
                1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                int minutes = (int) (millisUntilFinished / 60000);
                int difference = (int) (millisUntilFinished % 60000);
                int seconds = difference / 1000;
                alertDialog.setMessage(minutes + " : " + seconds);
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                alertDialog.setMessage("Time is up!");
            }
        };
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                textView = (TextView) view;

                builder.setTitle(textView.getText())
                        .setMessage(textView.getText())
                        .setPositiveButton("Close",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub
                                        alertDialog.cancel();
                                        count.cancel();
                                    }

                                }
                        ).setCancelable(false);

                alertDialog = builder.create();
                alertDialog.show();
                count.start();
            }

        });
        refresh.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                activephrases.clear();
                addListViews();
            }
        });


    }

    private void CreatePhraseList() {
        try {
            dict = new BufferedReader(new InputStreamReader(am.open("quotes.txt")));
            int counter = 0;
            String line = null;
            String quote = null;
            StringBuilder sb = new StringBuilder();
            while ((line = dict.readLine()) != null) {
                if (line.length() > 5) {
                    sb.append(line);
                } else {
                    quote = sb.toString();
                    phrasearray.add(quote);
                    sb.setLength(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dict.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPhrase() {
        String phrase = phrasearray.get((int) (Math.random() * phrasearray.size()));
        phrase.trim().replaceAll("\\s+", " ");
        return phrase;
    }

    private void addListViews() {
        //Set array values

        for (int i = 0; i < phrasenum; i++) {
            activephrases.add(getPhrase());
        }
        adapter.notifyDataSetChanged();

    }

    private void readSettings() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        phrasenum = Integer.parseInt(sharedPref.getString("phrasenumkey", ""));
        phrasetime = Integer.parseInt(sharedPref.getString("phrasetime", ""));
        /*  wordnum = Integer.parseInt(sharedPref.getString("wordnumkey", ""));*/
    }
}

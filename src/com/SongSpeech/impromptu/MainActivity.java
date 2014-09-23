package com.SongSpeech.impromptu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		checkSettings();


		/*Button bWords = (Button) findViewById(R.id.words);
        Button bPhrases = (Button) findViewById(R.id.phrases);
		
		bWords.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkSettings()) {
					Intent i = new Intent(MainActivity.this, WordActivity.class);
					startActivity(i);
				}
				
			}
		});
		bPhrases.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO LockOrientation
				if (checkSettings()) {
				Intent i = new Intent(MainActivity.this, PhraseActivity.class);
					startActivity(i);
				}

			}
		});*/
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Settings Opened", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, SettingsMenu.class);
			startActivity(i);
			return true;
//		case R.id.action_alarms:
//			Intent i = new Intent(this, PracticeAlarm.class);
//			startActivity(i);
//			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	private boolean checkSettings()
	{
		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		if(sharedPref.getString("wordnumkey", "") == "" || sharedPref.getString("phrasenumkey", "") == "")
		{
			Toast.makeText(this,
					"Please set Phrase and Word Amount in Settings",
					Toast.LENGTH_LONG).show();
			return false;
		} else {
			return true;
		}
	}
	

}

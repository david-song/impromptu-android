package com.SongSpeech.impromptu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		checkSettings();
		Button bWords = (Button) findViewById(R.id.words);
		Button bPhrases = (Button) findViewById(R.id.phrases);
		
		bWords.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(MainActivity.this, WordActivity.class);
				startActivity(i);
			}
		});
		bPhrases.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO LockOrientation
				Intent i = new Intent(MainActivity.this, PhraseActivity.class);
				startActivity(i);
			}
		});
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

	private void checkSettings()
	{
		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		int wordnum = Integer.parseInt(sharedPref.getString("wordnumkey", ""));
		int phrasenum = Integer.parseInt(sharedPref.getString("phrasenumkey", ""));
		if(sharedPref.getString("wordnumkey", "") == "")
		{
			sharedPref.edit().putString("wordnumkey", "3");
		}
		if(sharedPref.getString("phrasenumkey", "") == "")
		{
			sharedPref.edit().putString("phrasenumkey", "3");
		}
	}
	

}

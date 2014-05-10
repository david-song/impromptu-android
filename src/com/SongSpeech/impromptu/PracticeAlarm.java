package com.SongSpeech.impromptu;

import java.net.URISyntaxException;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class PracticeAlarm extends Activity {
	private SharedPreferences sharedPref;
	private int hour;
	private int minute;
	public Context context;
	private TextView alarmMessage;
	private LinearLayout alarmPreviewLayout;
	private AnalogClock alarmAnalogClock;
	private TextView alarmAnalogClockTime;
	private Button alarmDeleteAlarm;
	private Button alarmModifyAlarm;
	private int test;
	private SharedPreferences.Editor editor;
	private String TAG = "Practice Alarm";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_alarm);
		findViews();
		
		context = getApplicationContext();
		sharedPref = context.getSharedPreferences("alarms", Context.MODE_PRIVATE);
		editor = sharedPref.edit();
		test = sharedPref.getInt("Hour", 0);
		Log.v("Imp","Hour is" + test);
		if(sharedPref.getInt("Hour", 0) == 0)
		{
			
			alarmPreviewLayout.setVisibility(View.GONE);
		}
		else if(sharedPref.getInt("Hour",0) != 0)
		{
			alarmMessage.setVisibility(View.GONE);
			alarmModifyAlarm.setVisibility(View.GONE);
		}
		else{
			Toast.makeText(PracticeAlarm.this, "An error has occured", Toast.LENGTH_LONG).show();
		}
		
		alarmDeleteAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, alarm_notification.class);
				PendingIntent pIntent = PendingIntent.getService(context, 0, intent, 0);
				AlarmManager alarmMgr = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);		
				 
				alarmMgr.cancel(pIntent);
		
				editor.clear();
				editor.commit();
				//Intent intent = new Intent(New_alarm.this,alarm_notification.class);
				//PendingIntent alarmIntent = PendingIntent.getBroadcast(New_alarm.this, 0, intent, 0);
				// TODO Auto-generated method stub
				/*if(sharedPref.getBoolean("Recurring", false) == true)
				{
					
				}
				if(sharedPref.getBoolean("Recurring", false) == false )
				{
					
				}
				else{
					Toast.makeText(PracticeAlarm.this, "An error has occured", Toast.LENGTH_LONG).show();
				}*/
			}
		});
		alarmModifyAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PracticeAlarm.this, New_alarm.class);
				startActivity(i);
			}
		});
	}
	
	;
	
	private void findViews()
	{
		alarmMessage = (TextView)findViewById( R.id.alarm_message );
		alarmPreviewLayout = (LinearLayout)findViewById( R.id.alarm_preview_layout );
		alarmAnalogClock = (AnalogClock)findViewById( R.id.alarm_analog_clock );
		alarmAnalogClockTime = (TextView)findViewById( R.id.alarm_analog_clock_time );
		alarmDeleteAlarm = (Button)findViewById( R.id.alarm_delete_alarm );
		alarmModifyAlarm = (Button)findViewById( R.id.alarm_modify_alarm );
	}
}

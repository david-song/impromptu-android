package com.SongSpeech.impromptu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class New_alarm extends Activity{
	public static Context context;
	private TimePicker time;
	private ToggleButton toggleMonday;
	private ToggleButton toggleTuesday;
	private ToggleButton toggleWednesday;
	private ToggleButton toggleThursday;
	private ToggleButton toggleFriday;
	private ToggleButton toggleSaturday;
	private ToggleButton toggleSunday;
	private Button alarmSubmit;
	private CheckedTextView alarmCheckRecursivealarm;
	private boolean[] days;
	boolean isrecurring;
	private int hour;
	private int minute;
	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	private CheckBox alarm_recurssive_checkbox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_alarm);
		findViews();
		}
	private void findViews() 
	{
		context = getApplicationContext();
		time = (TimePicker)findViewById( R.id.new_alarm_time );
		toggleMonday = (ToggleButton)findViewById( R.id.toggle_monday );
		toggleTuesday = (ToggleButton)findViewById( R.id.toggle_tuesday );
		toggleWednesday = (ToggleButton)findViewById( R.id.toggle_wednesday );
		toggleThursday = (ToggleButton)findViewById( R.id.toggle_thursday );
		toggleFriday = (ToggleButton)findViewById( R.id.toggle_friday );
		toggleSaturday = (ToggleButton)findViewById( R.id.toggle_saturday );
		toggleSunday = (ToggleButton)findViewById( R.id.toggle_sunday );
		alarmSubmit = (Button)findViewById( R.id.alarm_submit );
		alarmCheckRecursivealarm = (CheckedTextView)findViewById( R.id.alarm_check_recursivealarm );
		alarm_recurssive_checkbox = (CheckBox)findViewById(R.id.alarm_recurssive);
		days = new boolean[7];
		alarmSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Return back to PracticeAlarm.java with new alarm created
						days[0] = toggleMonday.isChecked();
						days[1] = toggleTuesday.isChecked();
						days[2] = toggleWednesday.isChecked();
						days[3] = toggleThursday.isChecked();
						days[4] = toggleFriday.isChecked();
						days[5] = toggleSaturday.isChecked();
						days[6] = toggleSunday.isChecked();
						
						isrecurring = alarm_recurssive_checkbox.isChecked();
						hour = time.getCurrentHour();
						minute = time.getCurrentMinute();
						
						Log.v("Imp",Integer.toString(hour));
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(System.currentTimeMillis());
						c.set(Calendar.HOUR_OF_DAY, hour);
						c.set(Calendar.MINUTE, minute);
						
						
						alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
						Intent intent = new Intent(context,alarm_notification.class);
						alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
						
						SharedPreferences sharedPref = context.getSharedPreferences("alarms", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPref.edit();
						editor.putInt("Hour", hour);
						editor.putInt("Minute", minute);
						editor.putBoolean("Monday", days[0]);
						editor.putBoolean("Tuesday", days[1]);
						editor.putBoolean("Wednesday", days[2]);
						editor.putBoolean("Thursday", days[3]);
						editor.putBoolean("Friday", days[4]);
						editor.putBoolean("Saturday", days[5]);
						editor.putBoolean("Sunday", days[6]);
						editor.putBoolean("Recurring", isrecurring);
						editor.commit();
						
						if(isrecurring == true)
						{
							for(int i = 0; i < 6; i++)
							{
								if(days[i] == true)
								{
									c.set(Calendar.DAY_OF_WEEK, getDay(i));
									alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24 * 60 * 60 * 1000, alarmIntent);
									Toast.makeText(getApplicationContext(), "Recurring Alarm Set.", Toast.LENGTH_LONG).show();
									finish();
								}
							}
							
							
						}
						else if(isrecurring == false)
						{
							for(int i = 0; i < 6; i++)
							{
								if(days[i] == true)
								{
									alarmMgr.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmIntent);
									Toast.makeText(getApplicationContext(), "Single Alarm Set.", Toast.LENGTH_LONG).show();
									finish();
								}//ADD ALARM CANCEL ONCE OPENED APP
							}

						}
						//Create Alarms using the info.
						else
						{
							Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
						}

				}
		});
		
	}
	private int getDay(int i)
	{
		//Changes values from arry to the required ints for Calendar days.
		//Array week starts at Monday,0 ; Calendar starts at 1, Sunday
		int day = 0;
		switch(i)
		{
		case 0:
			day = 2;
			break;
		case 1:
			day = 3;
			break;
		case 2:
			day = 4;
			break;
		case 3:
			day = 5;
			break;
		case 4:
			day = 6;
			break;
		case 5:
			day = 7;
			break;
		case 6:
			day = 1;
			break;
		}
		return day;
	}
	private boolean checkDays()
	{
		if(toggleFriday.isChecked() || toggleMonday.isChecked() ||toggleSaturday.isChecked() || toggleSunday.isChecked() ||
				toggleThursday.isChecked() || toggleTuesday.isChecked() || toggleWednesday.isChecked())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public void cancelAlarms()
	{
		alarmMgr.cancel(alarmIntent);
	}
}




package com.SongSpeech.impromptu;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;

public class alarm_notification extends BroadcastReceiver {
	
	private static final int MY_NOTIFICATION_ID = 1;
	private static final String TAG= "AlarmNotificationReceiver";
	
	private final CharSequence tickerText = "Time to Practice!";
	private final CharSequence contentTitle = "Reminder";
	private final CharSequence contextText = "Go practice some Impromptu";
	
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;
	
	Uri soundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	private long[] mVibratePattern = {0,200,200,300};
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		mNotificationIntent = new Intent(context,MainActivity.class);
		mContentIntent = PendingIntent.getActivity(context, 0, mNotificationIntent,Intent.FLAG_ACTIVITY_NEW_TASK);
		
		Notification.Builder notificationBuilder = new Notification.Builder(
				context).setTicker(tickerText)
				.setSmallIcon(R.drawable.ic_launcher)
				.setAutoCancel(true).setContentTitle(contentTitle)
				.setContentText(contextText).setContentIntent(mContentIntent)
				.setSound(soundURI).setVibrate(mVibratePattern);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(MY_NOTIFICATION_ID,notificationBuilder.build());
		Log.w(TAG, "Sending Notificaion");
				
	}
	
}

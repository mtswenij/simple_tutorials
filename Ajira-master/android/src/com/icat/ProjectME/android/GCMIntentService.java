package com.icat.ProjectME.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super("182495627612");
    }
	
	protected GCMIntentService(String senderId) {
		super(senderId);
		Log.d(TAG , "Constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		Log.d(TAG , "on Error");
	}

	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Log.d(TAG , "RECIEVED A MESSAGE");
		// Get the data from intent and send to notificaion bar
		generateNotification(arg0, arg1.getStringExtra("message"));
	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		// OK, we're registered, now we need to notify the server of our id
		// TODO Auto-generated method stub
		//Intent sendGCMToServer = new Intent(getApplicationContext(), SendGCMService.class);
		Bundle gcmParams = new Bundle();
		gcmParams.putString("gcm_key", GCMRegistrar.getRegistrationId(this));
		//sendGCMToServer.putExtras(gcmParams);
	    //startService(sendGCMToServer);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Tell the App that the related GCM is no longer registered?
		Log.d(TAG , "On Unregistered");
	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 * This is a deprecated approach, please see Google for latest approach.
	 */
	@SuppressWarnings("deprecation")
	private static void generateNotification(Context context, String message) {
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				message, when);
		String title = context.getString(R.string.app_name);
		Intent notificationIntent = new Intent(context,
				MainActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification);
	}
}

package com.icat.ProjectME.gcm;
/**
 * Implement this abstract class for any REST service
 * which does not have interaction with the ui
 *
 *
 * @author  Pat Cullen (pat.s.cullen@gmail.com)
 */

/*
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

public abstract class RestResponderServce extends Service {
	private static final String TAG = RestResponderServce.class.getName();
    private ResultReceiver mReceiver;
    public static final String ACTION = "com.seepatcode.quickmsg.services.ACTION";
    
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onCreate() {
       //code to execute when the service is first created
 	   mReceiver = new ResultReceiver(new Handler()) {

            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultData != null && resultData.containsKey(RESTService.REST_RESULT)) {
                    onRESTResult(resultCode, resultData.getString(RESTService.REST_RESULT));
                }
                else {
                    onRESTResult(resultCode, null);
                }
            }
 	   };
            
    }

    @Override
    public void onDestroy() {
       //code to execute when the service is shutting down
    }

    @Override
    public void onStart(Intent intent, int startid) {
    	//code to execute when the service is starting up
    	Bundle extras = intent.getExtras();
    	if(getAction()==null)
    	{
    		// Without an action , we don't know what URI to hit.
            Log.e(TAG, "You did not pass an action with the Intent.");
            return;
    	}
 	   	Intent i = new Intent(this.getApplicationContext(), RESTService.class);
        i.setData(RestClient.getURI(getAction()));
        i.putExtra(RESTService.EXTRA_PARAMS, RestClient.servicePrep(extras));
        i.putExtra(RESTService.EXTRA_RESULT_RECEIVER, mReceiver);
        startService(i);
    }
    
    public ResultReceiver getResultReceiver() {
        return mReceiver;
    }

    // Implementers of this Fragment will handle the result here.
    abstract public void onRESTResult(int code, String result);
    abstract protected String getAction();
}*/

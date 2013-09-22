package com.icat.ProjectME.gcm;
/*
 * Utility Helper Class, adds in auth token and user id's to messages
 */

/*import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
*/
public class RestClient {

	/*
	 * Prep bundle with URI, ID, Access Token
	 */
	/*public static Bundle prep(String action)
	{
		return prep(action, null);
	}*/
	/*
	 * Prep bundle with URI, ID, Access Token, and supplied params
	 */
	/*public static Bundle prep(String action, Bundle params)
	{
		if(params==null)
			params = new Bundle();
	    Uri uri = Uri.parse(Constants.BASEURL + action);
	    params.putString("id", SessionData.getInstance().systemId); //"Username"
	    //params.putString("access_token", SessionData.getAccessToken()); //"Password"
	    Bundle args = new Bundle();
	    args.putParcelable("URI", uri);
	    args.putParcelable("params",params);
	    return args;
	}*/
	/*
	 * Like prep but no need for URI
	 */
	/*public static Bundle servicePrep(Bundle params)
	{
		if(params==null)
			params = new Bundle();
	    params.putString("id", SessionData.getInstance().systemId); //"Username"
	    //params.putString("access_token", SessionData.getAccessToken()); //"Password"
	    return params;
	}*/
/*	public static Uri getURI(String action) {
		return Uri.parse(Constants.BASEURL + action);
	}
	public static boolean startService(String action, Bundle params, Context context, ResultReceiver mReceiver) {
		Intent intent = new Intent(context, RESTService.class);
        intent.setData(getURI(action));
        intent.putExtra(RESTService.EXTRA_PARAMS, servicePrep(params));
        intent.putExtra(RESTService.EXTRA_RESULT_RECEIVER, mReceiver);
        // Here we send our Intent to our RESTService.
        context.startService(intent);
        return true;
	}*/
}

package com.icat.ProjectME.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import com.google.gson.Gson;
import com.icat.ProjectME.models.User;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExtractData  extends Activity{
		private TextView info;
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
			
	        info = (TextView)findViewById(R.id.info);
	        com.icat.ProjectME.gcm.ExtractData ex = new com.icat.ProjectME.gcm.ExtractData();
	        ex.setContext(info);
	        ex.execute("http://hax4tech.appspot.com/Mobile/showdevices");
	        
	    }
		private class Extractor extends AsyncTask<String, Void, String>{
			@Override
		    protected String doInBackground(String... urls) {
				StringBuilder output = new StringBuilder();
			    try {
			    	BufferedReader extract  = new BufferedReader(
			    								new InputStreamReader(
			    										new URL("http://hax4tech.appspot.com/Mobile/showdevices").openStream()));
			    	
			    	String user;
			    	while((user = extract.readLine()) != null){
			    	User data = new Gson().fromJson(user, User.class);
			    	output.append(data.mobileToken+"\n" + "\n");
			    	}
			        
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
		      return output.toString();
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    	info.setText(result);
		    }
		
		}
	    

}

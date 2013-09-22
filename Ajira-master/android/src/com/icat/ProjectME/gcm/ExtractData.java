package com.icat.ProjectME.gcm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.icat.ProjectME.models.User;

public class ExtractData extends AsyncTask<String, Void, String>{
	TextView view;
	public void setContext(TextView a)
	{
		view = a;
	}
	@Override
    protected String doInBackground(String... urls) {
		StringBuilder output = new StringBuilder();
	    try {
	    	BufferedReader extract  = new BufferedReader(
	    								new InputStreamReader(
	    										new URL(urls[0]).openStream()));
	    	
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
    	view.setText(result);
    }

}
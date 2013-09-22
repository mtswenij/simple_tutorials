package com.icat.ProjectME.android;
// This is the activity where the application starts


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private SharedPreferences sharedPrefs;
	private Button signup, login;
	private EditText username , password;
	private Button jobscreen,evaluation,jobPost,device;
	Activity a= this;
	final Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
     
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        //username and passsword
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
          
        
        //create buttons add listeners
		signup = (Button) findViewById(R.id.signup);
		login = (Button) findViewById(R.id.login);
		signup.setOnClickListener(this);
		login.setOnClickListener(this);
		
		jobscreen = (Button)findViewById(R.id.jobScreen);
		jobscreen.setOnClickListener(this);
		evaluation = (Button)findViewById(R.id.evaluation);
		evaluation.setOnClickListener(this);
		jobPost = (Button)findViewById(R.id.jobPost);
		jobPost.setOnClickListener(this);
		device = (Button)findViewById(R.id.device);
		device.setOnClickListener(this);
		if (GCMRegistrar.isRegistered(this)) {
			Log.d("info", GCMRegistrar.getRegistrationId(this));
		}
		final String regId = GCMRegistrar.getRegistrationId(this);
		//generate a new id
		if (regId.equals("")) {
			GCMRegistrar.register(this, "182495627612");
			Log.d("info", GCMRegistrar.getRegistrationId(this));

		}
		//find the existing device id
		else {
			Log.d("info", "already registered as" + regId);
		}
		//store the device id in shared preferences
		Editor edit = sharedPrefs.edit();
		edit.putString("id", regId);
		edit.commit();
		
    }
	//@Override
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		//GO TO THE CREATE ACCOUNT SCREEN
		if (v == signup){
			startActivity(new Intent(this,CreateAccountActivity.class ));
		}
		if(v == jobscreen)
		{
			startActivity(new Intent(this,ShowJobDetailsActivity.class));
		}
		if(v == evaluation)
		{
			/*final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.evalform);
			dialog.setTitle("Evaluation form");
			
			Button accept = (Button)dialog.findViewById(R.id.accept);
			/*accept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}});*/
			//dialog.show();
			startActivity(new Intent(this, EvaluationActivity.class));
		}
		if(v == jobPost)
		{
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.activity_create_new_micro_job);
			dialog.setTitle("Post a Job");
			
			/*Button accept = (Button)dialog.findViewById(R.id.accept);
			accept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}});*/
			dialog.show();
		}
		//go to show job screen
		else if (v == login) {
			Editor edit = sharedPrefs.edit();
			edit.putString("username", username.getText().toString());
			edit.putString("password", password.getText().toString());
			edit.commit();
			startActivity(new Intent(this,ShowJobActivity.class ));
		}
		else if(v == device)
		{
			//SendData task = new SendData();
			//task.execute("");
			startActivity(new Intent(this,ExtractData.class ));
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.preferences, menu);
	    return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    //respond to menu item selection
		switch (item.getItemId()) {
			case R.id.menu:
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
				SlideoutActivity.prepare(LoginActivity.this, R.id.inner_content, width);
				startActivity(new Intent(LoginActivity.this, MenuActivity.class));
				overridePendingTransition(0, 0);
				return true;
		    default:
		    	return super.onOptionsItemSelected(item);
		}
	}
	private class SendData extends AsyncTask<String, Void, String>{
		@Override
	    protected String doInBackground(String... urls) {
			URL turl = null;
		    try {
		        String registrationUrl = String.format("http://hax4tech.appspot.com/Mobile/submit?User={\"firstName\":\"android\"}");
		        turl = new URL(registrationUrl);
		        URLConnection connection = turl.openConnection();
		        HttpURLConnection httpConnection = (HttpURLConnection) connection;
		        int responseCode = httpConnection.getResponseCode();
		        if (responseCode == HttpURLConnection.HTTP_OK) {
		            Log.d("MyApp", "Registration success");
		        } else {
		            Log.w("MyApp", "Registration failed for: " + registrationUrl);              
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
	      return "";
	    }

	    @Override
	    protected void onPostExecute(String result) {
	      //textView.setText(result);
	    }
	
	}
}

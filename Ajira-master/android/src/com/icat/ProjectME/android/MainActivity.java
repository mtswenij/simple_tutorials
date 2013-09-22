package com.icat.ProjectME.android;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icat.ProjectME.models.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private EditText fname, lname,idnum;
	private Button submit;
	private TextView regNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		regNumber = (TextView)findViewById(R.id.textView1);
		fname = (EditText)findViewById(R.id.fname);
		lname = (EditText)findViewById(R.id.lname);
		idnum = (EditText)findViewById(R.id.idnum);
		submit = (Button)findViewById(R.id.submit);
		//GCMRegistrar.checkDevice(this);
		//uncomment below lines to unregister the device 
//		GCMRegistrar.unregister(this);
//		Log.d("info",
//				"unregistereddd....." + GCMRegistrar.getRegistrationId(this));
		//GCMRegistrar.checkManifest(this);
		if (GCMRegistrar.isRegistered(this)) {
			Log.d("info", GCMRegistrar.getRegistrationId(this));
		}
		final String regId = GCMRegistrar.getRegistrationId(this);
		regNumber.setText(regId);
		if (regId.equals("")) {
			// replace this with the project ID
			GCMRegistrar.register(this, "182495627612");
			Log.d("info", GCMRegistrar.getRegistrationId(this));
			regNumber.setText(regId);
		} else {
			Log.d("info", "already registered as" + regId);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == submit)
		{
			/*User temp = new User(fname.getText().toString(), lname.getText().toString(), Long.parseLong(idnum.getText().toString()));
			Gson gson = new GsonBuilder().create();
			String example = gson.toJson(temp);*/
			String first = fname.getText().toString();
			String last = lname.getText().toString();
			URL url = null;
		    try {
		        String registrationUrl = String.format("http://hax4tech.appspot.com/Mobile/submit?User={\"firstname\":\"android\"}");
		        url = new URL(registrationUrl);
		        URLConnection connection = url.openConnection();
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
		}
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/

}

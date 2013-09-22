package com.icat.ProjectME.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ShowProfileActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private ListView contacts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilescreen);
        
        contacts = (ListView)findViewById(R.id.contactList);
        String[] names = getResources().getStringArray(R.array.names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.favblacklist,R.id.name,names);
        contacts.setAdapter(adapter);
    }
	//@Override
	public void onClick(View v) {
	}
}

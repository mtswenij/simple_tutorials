package com.icat.ProjectME.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class CreateAccountActivity extends Activity {
    /** Called when the activity is first created. */
	private MyCustomAdapter edapter;
	private ExpandableListView elist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        elist = (ExpandableListView)findViewById(R.id.information);
        
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        
        String[] userInfo = getResources().getStringArray(R.array.userInfo);
        int[] userInfoType = getResources().getIntArray(R.array.userInfoType);
        arrayParents.add(new Parent("User Information", userInfo, userInfoType));
        
        String[] paymentInfo = getResources().getStringArray(R.array.paymentInfo);
        int[] paymentInfoType = getResources().getIntArray(R.array.paymentInfoType);
        arrayParents.add(new Parent("Payment Information", paymentInfo, paymentInfoType));
        

        
        //sets the adapter that provides data to the list.
        edapter = new MyCustomAdapter(CreateAccountActivity.this,arrayParents);
        elist.setAdapter(edapter);
        
        //Need to test the Adapter for input methods
    }
}
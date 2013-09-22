package com.icat.ProjectME.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateNewMicroJob extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_micro_job);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_new_micro_job, menu);
        return true;
    }
}

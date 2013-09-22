package com.icat.ProjectME.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ReviewNewMicroJob extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_micro_job);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_review_new_micro_job, menu);
        return true;
    }
}

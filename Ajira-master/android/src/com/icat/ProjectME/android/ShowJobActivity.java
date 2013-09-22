package com.icat.ProjectME.android;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ShowJobActivity extends MapActivity implements OnClickListener{
	private MapView mapView;
	private MapController mapController;
	private Button profile, filter;
	private ToggleButton viewSwitch, gpsSwitch;
	private ViewFlipper viewSwitcher;
	private ListView jobs;
	final Context context = this;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoscreen);
        
        //creating top bar of screen
        profile = (Button)findViewById(R.id.profile);
        profile.setOnClickListener(this);
        filter = (Button)findViewById(R.id.filter);
        filter.setOnClickListener(this);
        gpsSwitch = (ToggleButton)findViewById(R.id.gpsSwitch);
        viewSwitch = (ToggleButton)findViewById(R.id.viewSwitch);
        viewSwitch.setOnClickListener(this);
        viewSwitcher =(ViewFlipper)findViewById(R.id.ViewFlipper01);
        
        jobs = (ListView)findViewById(R.id.jobResults);
        String[] names = getResources().getStringArray(R.array.JobTitles);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.joblist,R.id.title,names);
        jobs.setAdapter(adapter);
        
        mapView = (MapView)findViewById(R.id.map);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(18);
		
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == profile){
			startActivity(new Intent(this,ShowProfileActivity.class ));
		}
		if(arg0 == filter)
		{
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.filterscreen);
			dialog.setTitle("Fliter jobs By");
			
			Button accept = (Button)dialog.findViewById(R.id.accept);
			accept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}});
			dialog.show();
		}

		else if(arg0 == viewSwitch)
		{
			if(viewSwitch.isChecked()){
				viewSwitcher.showNext();
			}
			else
			{
				viewSwitcher.showPrevious();
			}
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
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
				SlideoutActivity.prepare(ShowJobActivity.this, R.id.inner_content, width);
				startActivity(new Intent(ShowJobActivity.this, MenuActivity.class));
				overridePendingTransition(0, 0);
				return true;
		    default:
		    	return super.onOptionsItemSelected(item);
		}
	}
}
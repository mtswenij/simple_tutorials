package com.icat.ProjectME.android;

import com.google.android.maps.MapActivity;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ShowJobDetailsActivity extends MapActivity  implements OnClickListener, OnSeekBarChangeListener{
    /** Called when the activity is first created. */
	private Button joinMarket;
	private TextView workerBid;
	private SeekBar bidbar;
	private Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobinfo);
        
        joinMarket = (Button)findViewById(R.id.joinMarket);
        joinMarket.setOnClickListener(this);
        
        bidbar = (SeekBar)findViewById(R.id.bidBar);
        workerBid = (TextView)findViewById(R.id.workerBid);
        bidbar.setOnSeekBarChangeListener(this);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	public void onClick(View v) {
		/*if(v == joinMarket)
		{
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.workerbidscreen);
			dialog.setTitle("Place your Bid");
			
			Button accept = (Button)dialog.findViewById(R.id.accept);
			accept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}});
			dialog.show();
		}*/

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
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
				SlideoutActivity.prepare(ShowJobDetailsActivity.this, R.id.inner_content, width);
				startActivity(new Intent(ShowJobDetailsActivity.this, MenuActivity.class));
				overridePendingTransition(0, 0);
				return true;
		    default:
		    	return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		workerBid.setText("$"+String.valueOf(progress) +  "   /   $10");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
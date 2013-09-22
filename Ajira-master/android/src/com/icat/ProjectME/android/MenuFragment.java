package com.icat.ProjectME.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] options = getResources().getStringArray(R.array.MicroWorkerOptions);
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.elist_text_icon,R.id.text, options));
		getListView().setCacheColorHint(0);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		((MenuActivity)getActivity()).getSlideoutHelper().close();
		switch(position)
		{
		//case 0:startActivity(new Intent(this, About.class)); break;
		//case 1:startActivity(new Intent(this, About.class)); break;
		//case 2:startActivity(new Intent((MenuActivity)getActivity(), ReviewNewMicroJob.class)); break;
		//case 3:startActivity(new Intent((MenuActivity)getActivity(), About.class)); break;
		//case 4:startActivity(new Intent((MenuActivity)getActivity(), About.class)); break;
		case 6:startActivity(new Intent((MenuActivity)getActivity(), ShowProfileActivity.class)); break;
		case 7:startActivity(new Intent((MenuActivity)getActivity(), QuickPrefsActivity.class)); break;
		}
	}

	
}

package com.icat.ProjectME.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MyCustomAdapter extends BaseExpandableListAdapter {
	 
	 
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
 
    public MyCustomAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }
 
 
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }
 
    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }
 
    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }
 
    @Override
    public long getGroupId(int i) {
        return i;
    }
 
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    @Override
    public boolean hasStableIds() {
        return true;
    }
 
    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
 
        if (view == null) {
            view = inflater.inflate(R.layout.elist_parent, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
 
        //return the entire view
        return view;
    }
    
 
    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
    	if(mParent.get(i).getInputType(i1) == 0)
    	{
    		view = inflater.inflate(R.layout.elist_text_prompt, viewGroup,false);
    	}
    	else if(mParent.get(i).getInputType(i1) == 1)
    	{
    		view = inflater.inflate(R.layout.elist_email_prompt, viewGroup,false);
    	}
    	else if(mParent.get(i).getInputType(i1) == 2)
    	{
    		view = inflater.inflate(R.layout.elist_phone_prompt, viewGroup,false);
    	}
    	else if(mParent.get(i).getInputType(i1) == 3)
    	{
    		view = inflater.inflate(R.layout.elist_number_prompt, viewGroup,false);
    	}
    	else if(mParent.get(i).getInputType(i1) == 4)
    	{
    		view = inflater.inflate(R.layout.elist_month_prompt, viewGroup,false);
    	}
    	else
    	{
    		view = inflater.inflate(R.layout.elist_text_prompt, viewGroup,false);
    	}
 
        TextView textView = (TextView) view.findViewById(R.id.text);
        //"i" is the position of the parent/group in the list and 
        //"i1" is the position of the child
        textView.setText(mParent.get(i).getArrayChildren().get(i1));
 
        //return the entire view
        return view;
    }
 
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    
    public String getChildInput(int i, int i1, View view)
    {
    	EditText input = (EditText)view.findViewById(R.id.input);
    	return input.getText().toString();
    }
}
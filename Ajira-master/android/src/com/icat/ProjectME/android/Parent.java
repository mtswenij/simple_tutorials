package com.icat.ProjectME.android;
// this class is for Lists in the GUI
import java.util.ArrayList;
public class Parent {
    private String mTitle;
    private ArrayList<String> mArrayChildren = new ArrayList<String>();
    private ArrayList<Integer>	inputType = new ArrayList<Integer>();
    
    public Parent(String title, ArrayList<String> newArrayChildren, ArrayList<Integer> newinputType)
    {
    	mTitle = title;
    	mArrayChildren = newArrayChildren;
    	inputType = newinputType;
    }
    public Parent(String title, String[] newArrayChildren, int[] newinputType)
    {
    	mTitle = title;
    	for(int i = 0; i < newArrayChildren.length; i++)
        {
        	mArrayChildren.add(newArrayChildren[i]);
        	inputType.add(newinputType[i]);
        }
    }
 
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<String> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<String> mArrayChildren, ArrayList<Integer> newInputType) {
        this.mArrayChildren = mArrayChildren;
        this.inputType = newInputType;
    }
    
    public Integer getInputType(int child)
    {
    	return inputType.get(child);
    }
}

/**
 * 
 */
package com.jabu.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author Mtswejs
 *
 */
public class MyLists {
	
	
	
	//create a list with few integers and strings

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Object> simpleStringList = new ArrayList<Object>();
		Vector<String> myVector = new Vector<String>();
		
		System.out.println("Size of the vector is -->" + myVector.capacity());
		
		for (int i=0; i<20; i++)
		{
						simpleStringList.add(i+"-");
						simpleStringList.add(String.valueOf(i));
						myVector.add(""+i);
		}
		
		System.out.println("simpleStringList created");
		System.out.println("Vector size " + myVector.capacity());
		
		Iterator<Object> iter = simpleStringList.iterator();
		
		
		while(iter.hasNext()){
			
			System.out.println("List Values = "+ ""+iter.next());
			
		}
		
		for (Object str : simpleStringList){
			
			System.out.println("For loop ==> " + str.toString());
		}
		
	}

}

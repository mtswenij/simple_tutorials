/**
 * 
 */
package com.jabu.datastructures;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mtswejs
 *
 */

public class myHashMap {
	
	
	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) {
		try {
 
			//create a hash map
			Map<String, String> mMap = new ConcurrentHashMap<String, String>();
			mMap.put("PostgreSQL", "Free Open Source Enterprise Database");
			mMap.put("DB2", "Enterprise Database , It's expensive");
			mMap.put("Oracle", "Enterprise Database , It's expensive");
			mMap.put("MySQL", "Free Open SourceDatabase");
 
			Iterator iter = mMap.entrySet().iterator();
		
			int i=0;
 
			while (iter.hasNext()) {
				
				Map.Entry mEntry = (Map.Entry) iter.next();
				System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
				mMap.put("IMB", ""+i);
				i++;
			}
 
			mMap.put("Oracle", "Enterprise Database , It's free now ! (hope)");
 
			System.out.println("One day Oracle.. : " + mMap.get("Oracle") + mMap.get("IMB"));
 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

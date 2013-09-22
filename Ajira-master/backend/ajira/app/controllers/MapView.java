package controllers;

import play.*;
import play.mvc.*;
import siena.Model;
import utils.Auth;

import java.text.DateFormat;
import java.util.*;

import models.*;

public class MapView extends Controller {

	@Before
    static void isConnected() {
        if(Auth.getGoogleUser() == null) {
            Users.login();
        } else {
            renderArgs.put("user", Auth.getGoogleEmail());
        }
    }
	
	public static void index(int mapType) {
			
		//Declare Variables
		List<Location> locations = new ArrayList<Location>();
		Iterable<MicroJob> Ijobs = null;
		
		//Render the map based on the type of user
		switch(mapType)
		{
			//View map for a Micro Worker
			case 0:
				Ijobs = Model.all(MicroJob.class)
							 .filter("microWorker", User.findByEmail(Auth.getGoogleEmail()))
							 .order("-submitionDateTime")	
							 .iter();
				for(MicroJob job : Ijobs)
				{
					locations.add(job.deliveryDestination);
				}
				render(locations);
			break;
			
			//View map for a Micro Employer
			case 1:
		    	Ijobs = Model.all(MicroJob.class)
							 .filter("microEmployer", User.findByEmail(Auth.getGoogleEmail()))
							 .order("-submitionDateTime")	
							 .iter();
		    	for(MicroJob job : Ijobs)
				{
					locations.add(job.deliveryDestination);
				}
				render(locations);
			break;
		}
	}
	
}
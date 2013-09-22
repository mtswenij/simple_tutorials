package controllers;

import play.*;
import play.mvc.*;
import siena.Model;
import utils.Auth;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import managers.ManagerAccess;
import models.*;

public class MicroWorker extends Controller {

	@Before
    static void checkIsLoggedIn() {
        if(Auth.getGoogleUser() == null) {
        	Users.login();
        } else {
            renderArgs.put("user", Auth.getGoogleEmail());
        }
    }
	
	public static void index() {
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetCurrentMicroJobsByWorkerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}

	public static void pastMJ() {
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetPastMicroJobsByWorkerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}

	public static void allMJ() {
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetAllMicroJobsByWorkerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}

	public static void recommendedMJ() {
		List<MicroJob> jobs = Model.all(MicroJob.class).fetch();

		int count = jobs.size();
		render(jobs, count);
	}

	public static void searchMJ() {
		List<MicroJob> jobs = new ArrayList<MicroJob>();

		int count = jobs.size();
		render(jobs, count);
	}

	public static void bids() {
		User user = Model.all(User.class).filter("id", Auth.getUserID()).get();
		List<Bid> bids = Model.all(Bid.class).filter("microWk",user).fetch();

		int count = bids.size();
		render(bids, count);
	}
	
	public static void placeBid(String microJobID) {
		
		MicroJob job = Model.all(MicroJob.class).filter("id",microJobID).get();
		render(job);
	}
	
	public static void placeBidPost(String microJobID) {
		
		Bid bid = new Bid();
		//Extract the date from the form
		try 
		{
			SimpleDateFormat dateParser = new SimpleDateFormat("MM-dd-yyyy HH:mm aa zzz");
			StringBuilder sb = new StringBuilder();
			sb.append(params.get("month"));
			sb.append("-");
			sb.append(params.get("day"));
			sb.append("-");
			sb.append(params.get("year"));
			sb.append(" ");
			sb.append(params.get("hour"));
			sb.append(":");
			sb.append(params.get("minute"));
			sb.append(" ");
			sb.append(params.get("timeOfDay"));
			sb.append(" ");
			sb.append(params.get("timeZone"));
			bid.deliveryTime = dateParser.parse(sb.toString());
		} 
		catch (ParseException e)
		{
			bid.deliveryTime = new Date();
		} 
		 
		bid.mJob = Model.all(MicroJob.class).filter("id", microJobID).get();
		bid.microWk = Model.all(User.class).filter("id", Auth.getUserID()).get();
		bid.price = Double.parseDouble(params.get("maxPrice"));
		bid.insert();
		MicroWorker.bids();
	}
}
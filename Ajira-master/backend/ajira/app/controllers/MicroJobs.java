package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import play.libs.F.Promise;
import play.mvc.Before;
import play.mvc.Controller;
import siena.Model;
import siena.Query;
import utils.Auth;
import utils.DataGeneratorBootStrap;

import managers.ManagerAccess;
import models.Account;
import jobs.Auction;
import models.Category;
import models.Bid;
import models.Notification;
import models.UserCategory;
import models.JobCategory;
import models.JobType;
import models.Location;
import models.MicroJob;
import models.QualityMetrics;
import models.Status;
import models.User;

public class MicroJobs extends Controller {
	
	@Before
	static void checkLogIn() {
		if (Auth.getGoogleUser() == null) {
			Users.login();
		} else {
			renderArgs.put("user", Auth.getGoogleEmail());
		}
	}
	
	//HTML page for creating a new Micro Job
	public static void newMJ() {
		List<Category> categories = ManagerAccess.CategoryManager.GetCategories();
		render(categories);
	}
	
	//View a selected Micro Job using its ID
	public static void viewMJ(String id) {
		MicroJob mj = ManagerAccess.MicroJobManager.GetMicroJob(id);
		renderJSON(mj);
	}

	//Edit a selected Micro Job using its ID
	public static void editMJ(String id) {
		MicroJob mj = ManagerAccess.MicroJobManager.GetMicroJob(id);
		renderJSON(mj);
	}
	
	//Update a selected Micro Job using its ID
	public static void updateMJ() {
		//ManagerAccess.MicroJobManager.UpdateMicroJob(microJob);
	}

	//Deletes a selected Micro Job using its ID
	public static void deleteMJ(String id) {
		ManagerAccess.MicroJobManager.DeleteMicroJob(id);
		MicroEmployer.index();
	}

	//Creates a new Micro Job using a HTML form
	public static void createMicroJob() 
	{		
		MicroJob mj = new MicroJob();
		mj.microEmployer = User.findByEmail(Auth.getGoogleEmail());	
		mj.jobStatus = Status.Pending;
		mj.description = params.get("description");
		mj.details = params.get("details");
		mj.instructions = params.get("instructions");
		
		//String c = params.get("categories");  // comma separated list
		//List<String> cats = Arrays.asList(c.split("\\s*,\\s*"));
		
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
			mj.deliveryDateTime = dateParser.parse(sb.toString());
		} 
		catch (ParseException e)
		{
			mj.deliveryDateTime = new Date();
		} 
		
		mj.submitionDateTime = new Date();
		mj.maxPrice = Double.parseDouble(params.get("maxPrice"));
		mj.auctionStatus = Status.Open;
		mj.jType = Integer.parseInt(params.get("jobType"));
		
		ManagerAccess.MicroJobManager.CreateMicrojob(mj);
		
		// link categories to microjob
		/*for(int i = 0; i < cats.size(); i++) { 
			Category aCat = ManagerAccess.CategoryManager.GetCategoryByName(cats.get(i));  
			JobCategory jc = new JobCategory(mj, aCat); 
			jc.save();
		}
		
		// open the auction
		//mj.auctionStatus = Status.Open;
		
		ManagerAccess.MicroJobManager.UpdateMicroJob(mj);
		
		//send notifications created new MicroJob
		ManagerAccess.NotificationManager.SendNotification(mj.microEmployer.id, Notification.EVERYONE, Notification.MICROJOB, Notification.CREATE, 5, mj.id);

		// hold auction in future based on auction end time 
		List<String> auctionResults = null; 
		Promise<List<String>> results = new Auction(mj).in(mj.getAuctionEndtime());
		 
		// AUCTION DOES NOT HAPPEN INSTANTANEOUSLY
		
		// get the results sometime in the future after the auction is done
		 auctionResults = await(results);
		  
		 mj.setAuctionResults(auctionResults);
		  
		 mj.auctionStatus = Status.Unconfirmed;
		  
		 ManagerAccess.MicroJobManager.UpdateMicroJob(mj);
		 
		 mj.sendWinnerNotifcation();*/
		  
		 MicroEmployer.index();
	}

	public void confirmWinner(String microJobID, String microWorkID,
			int conformationStatus) {
		MicroJob mj = MicroJob.all().getByKey(microJobID);

		User mWk = User.all().getByKey(microWorkID);

		if (mWk.equals(mj.getWinner().id)) // make sure it is not a bogus id
		{
			if (conformationStatus == Status.Accepted) {
				mj.auctionStatus = Status.Closed;
				mj.setFinalJobPrice();
				mj.accpetWinner();
			} else if (conformationStatus == Status.Declined)
				mj.declineWinner();
		}

		mj.update();

		// send notifications of winner and losers
		ManagerAccess.NotificationManager.SendNotification(mj.microEmployer.id, Notification.EVERYONE, Notification.MICROJOB, Notification.UPDATE, 5, mj.id);
	}

	public void submitBid(String microJobID, String microWorkID, double price,
			Date estDeliveryTime) {
		Bid b = new Bid();
		b.bidPostTime = new Date();
		b.deliveryTime = estDeliveryTime;
		b.microWk = User.all().getByKey(microWorkID);
		b.mJob = MicroJob.all().getByKey(microJobID);
		b.price = price;

		b.insert();
		b.save();
		
		// send notifications of creation
		ManagerAccess.NotificationManager.SendNotification(microWorkID, Notification.EVERYONE, Notification.BID, Notification.CREATE, 5,b.id);
	}

	public void updateBid(Long bidID, double price, Date estDeliveryTime) {
		Bid b = Bid.all(Bid.class).getByKey(bidID);
		b.bidPostTime = new Date();
		b.deliveryTime = estDeliveryTime;
		b.price = price;

		b.update();
		b.save();
		// send notifications of update
		ManagerAccess.NotificationManager.SendNotification(b.microWk.id, Notification.EVERYONE, Notification.BID, Notification.UPDATE, 5,b.id);
	}

	public void deleteBid(Long bidID) {
		Bid b = Bid.all(Bid.class).getByKey(bidID);
		b.delete();
		
		// send notifications of update
		ManagerAccess.NotificationManager.SendNotification(b.microWk.id, Notification.EVERYONE, Notification.BID, Notification.UPDATE, 5,b.id);

	}
}

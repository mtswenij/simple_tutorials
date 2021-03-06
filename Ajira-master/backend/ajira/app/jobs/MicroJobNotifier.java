package jobs;

import play.Play;
import play.jobs.*;

import java.util.*;

import models.Bid;
import models.Category;
import models.JobCategory;
import models.MicroJob;
import models.Notification;
import models.Role;
import models.User;
import models.UserCategory;
import siena.*;

// GCM imports
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

@Every("1mn")
// Execute every 1 minute
public class MicroJobNotifier extends Job {

	public static boolean lock = false;
	public static final double THRESHOLD = 0.70;

	public void doJob() {
		if(!isLocked()) {  // if there is already an account notifier running, don't run again

			// fetch at most 25 microjob notifications ordered by timestamp ascending
			List<Notification> bids2Notify = Model.all(Notification.class)
					.filter("type", Notification.MICROJOB).order("timeStamp")
					.fetch(25);

			// device list
			List<String> deviceList;

			// GCM_Key located in Application.conf
			String key = Play.configuration.getProperty("gcm.key");
			Sender sender = new Sender(key);

			if (bids2Notify != null) // if null then return
			{
				for (Notification n : bids2Notify) // push out notifications
				{
					// delete notification from db
					n.isDeleted = true;
					n.update();
				}

				for (Notification n : bids2Notify) // push out notifications
				{ 
					if(!n.isDeleted) {
						// get the device list

						deviceList = generateDeviceList(n);

						try {
							// create message with payload data
							Message message = new Message.Builder().collapseKey("1")
									.timeToLive(3).delayWhileIdle(true)
									.addData("message", n.toJSON()).build();

							// send the multicast out to all devices
							sender.send(message, deviceList, 1);
						} catch (Exception e) {
							// handle send error
						}
					}
				}
			}
			UnLock();
		}
	}

	public List<String> generateDeviceList(Notification n) {

		// use a hashtable to exclude duplicates
		Hashtable<String, String> dlist = new Hashtable<String, String>();

		// get all the userID's who placed a bid for the auction, and the id of
		// the employer of the microjob,
		// get all users with similar TAGs and preferences to the microjob
		// preferences, and use their MobileToken

		// get the Bid info
		Bid b = Model.all(Bid.class).getByKey(n.payLoadID);

		// Get MicroJob
		MicroJob mj = Model.all(MicroJob.class).getByKey(b.mJob.id);

		// Get all Workers who bidded
		List<Bid> blist = mj.GetBidPricesAssendingOrder(); // get bids for microjob
		//Model.all(Bid.class).filter("mjob", mj).fetch(); 									

		for (Bid bs : blist) { // get the users who placed the bid
			User u = Model.all(User.class).getByKey(bs.microWk.id);
			if (!dlist.containsKey(u.mobileToken))
				dlist.put(u.mobileToken, u.mobileToken);
		}

		// Get MicroJob Employer
		User u = Model.all(User.class).getByKey(mj.microEmployer.id);
		if (!dlist.containsKey(u.mobileToken))
			dlist.put(u.mobileToken, u.mobileToken);

		// get all Users who's categories match the microjob categories

		// get microjob categories
		List<JobCategory> jc = mj.GetCategories();

		List<String> cl = new ArrayList<String>(); // datastructure to hold
		// category names

		for (JobCategory j : jc) {
			Category c = Model.all(Category.class).getByKey(j.cat.id);
			cl.add(c.name); // add category to list
		}

		// find all UserCategories who's names are in the JobCategory list
		// created above
		List<UserCategory> ulist = Model.all(UserCategory.class)
				.filter("cat.name IN", cl).fetch();

		// put user's mobile tokens in distribution list that have same
		// categories as microjob, AND
		// the preferences match with a score > 0.70
		for (UserCategory uc : ulist) {
			u = Model.all(User.class).getByKey(uc.user.id);

			// check quality score
			double score = u.userProfile.userQuality.CalculateQualityScore(
					Role.MICRO_WORKER, mj.quality);

			if (score > THRESHOLD) {
				if (!dlist.containsKey(u.mobileToken))
					dlist.put(u.mobileToken, u.mobileToken);
			}
		}

		List<String> finalList = new ArrayList<String>(dlist.values());

		return finalList;

	}

	public synchronized void Lock() {
		lock = true;
	}

	public synchronized void UnLock() {
		lock = false;
	}

	public synchronized boolean isLocked() {
		if(!lock) {			
			Lock();
			return false;
		}
		else
			return lock;
	}
}

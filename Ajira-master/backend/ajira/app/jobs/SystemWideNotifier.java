package jobs;

import play.Play;
import play.jobs.*;

import java.util.*;

import models.Account;
import models.Notification;
import models.User;
import siena.*;

// GCM imports
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

@Every("1mn")
// Execute every 1 minute
public class SystemWideNotifier extends Job {
	public static boolean lock = false;
	public void doJob() {
		if(!isLocked()) {  // if there is already an account notifier running, don't run again

			// fetch at most 25 sys wide notifications ordered by timestamp ascending
			List<Notification> bids2Notify = Model.all(Notification.class)
					.filter("type", Notification.SYSTEM).order("timeStamp")
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
		List<String> dlist = new ArrayList<String>();

		// get MobileToken of User and Acct Administrator of user

		// get user
		List<User> ulist = Model.all(User.class).fetch();
		for (User uc : ulist) {
			dlist.add(uc.mobileToken);
		}

		return dlist;
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

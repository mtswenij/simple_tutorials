package managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import siena.Model;

import interfaces.NotificationManager;
import models.Notification;
import models.Role;
import models.User;

public class NotificationMgr implements NotificationManager {

	@Override
	public List<Notification> GetAllNotifications(int arg0) {
		List<Notification> nlist = Model.all(Notification.class).fetch();
		return nlist;
	}

	@Override
	public boolean SendNotification(String fromID, String toID, int type, int operation, int priority, String objectID) {
		
		Notification n = new Notification();
		
		n.fromID = fromID;
		n.operation = operation;
		n.priority = priority;
		n.timeStamp = new Date();
		n.toID = toID;  // send to everyone who is supposed to get it
		n.type = type;
		n.payLoadID = objectID;
		n.isDeleted = false;
		n.save();
		
		return false;
	}

	@Override
	public List<Notification> GetNotificationsByMicroWorkerID(
			String microWorkerID, int limit) {
		List<User> ulist = Model.all(User.class).filter("role", Role.MICRO_WORKER).fetchKeys();
		List<String> uid = new ArrayList<String>();
		
		for(int i = 0; i < ulist.size(); i++) {
			uid.add(ulist.get(i).id);
		}
		
		List<Notification> nlist = Model.all(Notification.class).filter("fromID IN",uid).fetch();
		return nlist;
	}

	@Override
	public List<Notification> GetNotificationsByMicroEmployerID(
			String microWorkerID, int limit) {
		List<User> ulist = Model.all(User.class).filter("role", Role.MICRO_EMPLOYER).fetchKeys();
		List<String> uid = new ArrayList<String>();
		
		for(int i = 0; i < ulist.size(); i++) {
			uid.add(ulist.get(i).id);
		}
		
		List<Notification> nlist = Model.all(Notification.class).filter("fromID IN",uid).fetch();
		return nlist;
	}

}

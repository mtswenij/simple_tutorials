package interfaces;

import java.util.*;

import models.Notification;

public interface NotificationManager {
	public boolean SendNotification(String fromID, String toID, int type, int operation, int priority, String objectID);

	public List<Notification> GetNotificationsByMicroWorkerID(
			String microWorkerID, int limit);

	public List<Notification> GetNotificationsByMicroEmployerID(
			String microWorkerID, int limit);

	public List<Notification> GetAllNotifications(int limit);
}

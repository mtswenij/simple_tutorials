package models;

import java.util.*;

import siena.*;

public class Notification extends Model {
	@Id(Generator.UUID)
	public String id;
	public Date timeStamp;
	public int operation;
	public int type;
	public String payLoadID;
	public String fromID;
	public String toID; // -1 to everyone
	public int priority;
	public boolean isDeleted;

	// Notification operation
	public static final int TX = 1;
	public static final int UPDATE = 2;
	public static final int CREATE = 3;
	public static final int DELETE = 4;
	public static final int ALERT = 5;
	public static final int WINNER = 6;
	public static final int LOSER = 7;

	// Notification types
	public static final int BID = 1;
	public static final int ACCOUNT = 2;
	public static final int USER = 3;
	public static final int MICROJOB = 4;
	public static final int SYSTEM = 5;
	public static final int AUCTION = 6;

	// ID to send to everyone
	public static final String EVERYONE = "EVERYONE";

	public Notification() {
		priority = 5;
		isDeleted = false;
	}

	public String getTypeAsString() {
		switch (type) {
		case Notification.TX:
			return "TX";
		case Notification.UPDATE:
			return "UPDATE";
		case Notification.DELETE:
			return "DELETE";
		case Notification.SYSTEM:
			return "SYSTEM";
		default:
			return "CREATE";
		}
	}

	public String toString() {
		return "NOTIFICATION[" + id + "]: " + getTypeAsString() + "Priority: "
				+ priority + " FROM: " + fromID + " TO: " + toID;
	}

	public String toJSON() {
		return "operation:" + operation + " type:" + type + " ID: " + payLoadID;
	}

	static Query<Notification> all() {
		return Model.all(Notification.class);
	}

	public static Notification findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<Notification> findBySender(User u) {
		return all().filter("fromID", u.id).fetch();
	}

	public static List<Notification> findByReceiver(User u) {
		return all().filter("toID", u.id).fetch();
	}

	public static List<Notification> findByNotificationType(int e) {
		return all().filter("type", e).fetch();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
		result = prime * result
				+ ((payLoadID == null) ? 0 : payLoadID.hashCode());
		result = prime * result + ((fromID == null) ? 0 : fromID.hashCode());
		result = prime * result + ((toID == null) ? 0 : toID.hashCode());
		result = prime * result + type;
		result = prime * result + priority;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (priority != other.priority)
			return false;

		if (type != other.type)
			return false;

		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;

		if (payLoadID == null) {
			if (other.payLoadID != null)
				return false;
		} else if (!payLoadID.equals(other.payLoadID))
			return false;
		if (fromID == null) {
			if (other.fromID != null)
				return false;
		} else if (!fromID.equals(other.fromID))
			return false;
		if (toID == null) {
			if (other.toID != null)
				return false;
		} else if (!toID.equals(other.toID))
			return false;
		return true;
	}
}

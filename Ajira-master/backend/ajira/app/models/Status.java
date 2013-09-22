package models;

public class Status { 
	
	// microjob status
	public static final int Accepted = 1;
	public static final int Approaching = 2;
	public static final int Pending = 3;
	public static final int Delivered = 4;
	public static final int Delayed = 5;
	public static final int Error = 6;
	public static final int InRoute = 7;
	public static final int Declined = 11;

	// auction status
	public static final int Open = 8;
	public static final int Unconfirmed = 9;
	public static final int Closed = 10;
	
	public static boolean isApproaching(int value) {
		return (value == Approaching) ? true : false;
	}

	public static boolean isDelivered(int value) {
		return (value == Delivered) ? true : false;
	}

	public static boolean isPending(int value) {
		return (value == Pending) ? true : false;
	}

	public static boolean isInRoute(int value) {
		return (value == InRoute) ? true : false;
	}

	public static boolean isDelayed(int value) {
		return (value == Delayed) ? true : false;
	}

	public static boolean isOpen(int value) {
		return (value == Open) ? true : false;
	}

	public static boolean isClosed(int value) {
		return (value == Closed) ? true : false;
	}

	public static boolean isUnconfirmed(int value) {
		return (value == Unconfirmed) ? true : false;
	}

	public static boolean isAccepted(int value) {
		return (value == Accepted) ? true : false;
	}

	public static boolean isDeclined(int value) {
		return (value == Declined) ? true : false;
	}

	public String toString(int value) {
		String s;

		switch (value) {
		case 1:
			s = "Accepted";
			break;
		case 2:
			s = "Approaching";
			break;
		case 3:
			s = "Pending";
			break;
		case 4:
			s = "Delivered";
			break;
		case 5:
			s = "Delayed";
			break;
		case 6:
			s = "Error";
			break;
		case 7:
			s = "InRoute";
			break;
		case 8:
			s = "Open";
			break;
		case 9:
			s = "Unconfirmed";
			break;
		case 10:
			s = "Closed";
		default:
			s = "Declined";
			break;
		}

		return s;
	}
}
package models;

public class JobType {
	public static final int Local = 1;
	public static final int Virtual = 2;

	public static String toString(int value) {
		String s;

		switch (value) {
		case 1:
			s = "Local";
			break;
		default:
			s = "Virtual";
			break;
		}

		return s;
	}
}
package models;

public class EvaluationType {
	public static final int Employer = 1;
	public static final int Worker = 2;

	
	public static String toString(int value) {
		String s;

		switch (value) {
		case 1:
			s = "Employer";
			break;
		default:
			s = "Worker";
			break;
		}

		return s;
	}
}

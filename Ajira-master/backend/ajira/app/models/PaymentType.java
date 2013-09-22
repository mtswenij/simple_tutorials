package models;

public class PaymentType {
	public static final int PayPal = 1;
	public static final int GoogleWallet = 2;
	public static final int AirTel = 3;
	public static final int MPESA = 4;
	public static final int CreditCard = 5;
	public static final int OrangeMoney = 6;
	public static final int YUCash = 7;
	
	public static String toString(int value) {
		String s;

		switch (value) {
		case 1:
			s = "PayPal";
			break;
		case 2:
			s = "GoogleWallet";
			break;
		case 3:
			s = "AirTel";
			break;
		case 4:
			s = "MPESA";
			break;
		case 5:
			s = "CreditCard";
			break;
		case 6:
			s = "OrangeMoney";
			break;
		default:
			s = "YUCash";
			break;
		}

		return s;
	}
}

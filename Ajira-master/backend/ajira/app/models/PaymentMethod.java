package models;

import java.io.Serializable;

import siena.*;
import siena.embed.Embedded;
import siena.embed.EmbeddedMap;
import siena.embed.Embedded.Mode;

@EmbeddedMap
public class PaymentMethod implements Serializable {
	// Credit Card Information
	public String cardName;

	@Embedded(mode = Mode.NATIVE)
	public Address cardAddress;

	public String cardType;
	public String cardNumber;
	public String expirationMonth;
	public String expirationYear;
	public String securityCode;

	public int paytype;

	// Pay Pal
	public String paypalID;

	// Google Wallet
	public String gwalletID;

	// Air-tel
	public String airtel;

	// Orange Money
	public String orangeMoneyID;

	// YU Cash
	public String yuCashID;

	// Mpesa
	public String mpesaID;

	public String toString() {
		String s = null;

		s = PaymentType.toString(paytype);
		switch (paytype) {
		case PaymentType.AirTel:
			s += "ID: " + airtel;
			break;
		case PaymentType.CreditCard:
			s += ": " + cardNumber;
			break;
		case PaymentType.GoogleWallet:
			s += "ID: " + gwalletID;
			break;
		case PaymentType.MPESA:
			s += "ID: " + mpesaID;
			break;
		case PaymentType.PayPal:
			s += "ID: " + paypalID;
		case PaymentType.OrangeMoney:
			s += "ID: " + orangeMoneyID;
		default:
			s += "ID: " + yuCashID;
			break;
		}

		return s;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paypalID == null) ? 0 : paypalID.hashCode());
		result = prime * result + ((mpesaID == null) ? 0 : mpesaID.hashCode());
		result = prime * result
				+ ((gwalletID == null) ? 0 : gwalletID.hashCode());
		result = prime * result + ((airtel == null) ? 0 : airtel.hashCode());
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result
				+ ((orangeMoneyID == null) ? 0 : orangeMoneyID.hashCode());
		result = prime * result
				+ ((yuCashID == null) ? 0 : yuCashID.hashCode());
		result = prime * paytype;
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
		PaymentMethod other = (PaymentMethod) obj;

		if(paytype != other.paytype)
			return false;
		
		if (paypalID == null) {
			if (other.paypalID != null)
				return false;
		} else if (!paypalID.equals(other.paypalID))
			return false;

		if (mpesaID == null) {
			if (other.mpesaID != null)
				return false;
		} else if (!mpesaID.equals(other.mpesaID))
			return false;

		if (gwalletID == null) {
			if (other.gwalletID != null)
				return false;
		} else if (!gwalletID.equals(other.gwalletID))
			return false;

		if (airtel == null) {
			if (other.airtel != null)
				return false;
		} else if (!airtel.equals(other.airtel))
			return false;

		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (orangeMoneyID == null) {
			if (other.orangeMoneyID != null)
				return false;
		} else if (!orangeMoneyID.equals(other.orangeMoneyID))
			return false;
		if (yuCashID == null) {
			if (other.yuCashID != null)
				return false;
		} else if (!yuCashID.equals(other.yuCashID))
			return false;
		return true;
	}

}

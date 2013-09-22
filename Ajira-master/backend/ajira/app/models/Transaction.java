package models;

import java.util.Date;
import java.util.List;

import siena.*;

public class Transaction extends Model {
	@Id(Generator.UUID)
	public String id;

	public Date timeStamp;
	public User from;
	public User to;
	public float amount;
	public int txType;
	public PaymentType payType;

	// transaction types
	public static final int DEBIT = 1;
	public static final int CREDIT = 2;
	public static final int VOID = 3;

	static Query<Transaction> all() {
		return Model.all(Transaction.class);
	}

	public static Transaction findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<Transaction> findByReceiver(User r) {
		return all().filter("to", r.id).fetch();
	}

	public static List<Transaction> findBySender(User s) {
		return all().filter("from", s.id).fetch();
	}

	public static List<Transaction> findByTXType(int t) {
		return all().filter("txType", t).fetch();
	}

	public static List<Transaction> findByPaymentType(PaymentType pt) {
		return all().filter("payType", pt).fetch();
	}

}

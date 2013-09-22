package models;

import java.util.*;

import play.data.validation.*;
import siena.*;
import siena.embed.Embedded;
import siena.embed.Embedded.Mode;

public class Account extends Model {
	@Id(Generator.UUID)
	public String id;

	// Date created
	@Required
	@DateTime
	public Date creationDate;

	//Default User ID
	@Required
	public String defaultUserID;
	
	public String acctName;
	
	public String email;
	
	public String phoneNumber;

	@Embedded(mode = Mode.NATIVE)
	public PaymentMethod billing;

	@Filter("account_index")
	public static Query<User> acctUsers;

	public static List<User> getAccountUsers() {
		return acctUsers.fetch();
	}

	static Query<Account> all() {
		return Model.all(Account.class);
	}

	public static Account findById(String id) {
		return all().filter("id", id).get();
	}

	public static Account findByEmail(String email) {
		return all().filter("email", email).get();
	}

	public static Account findByPasswd(String p) {
		return all().filter("passwd", p).get();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((acctName == null) ? 0 : acctName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((billing == null) ? 0 : billing.hashCode());
		return result;
	}

	public String toString() {
		return "Acct[" + id + "]:" + acctName + " created on " + creationDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;

		if (acctName == null) {
			if (other.acctName != null)
				return false;
		} else if (!acctName.equals(other.acctName))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;

		if (billing == null) {
			if (other.billing != null)
				return false;
		} else if (!billing.equals(other.billing))
			return false;
		return true;
	}
}

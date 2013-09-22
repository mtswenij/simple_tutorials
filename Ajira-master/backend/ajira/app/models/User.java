package models;

import java.util.*;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Phone;
import play.data.validation.Required;

import siena.*;
import siena.embed.Embedded;
import siena.embed.Embedded.Mode;

public class User extends Model {
	@Id(Generator.UUID)
	public String id;

	// token from 3rd party authenticator (i.e. google, facebook, yahoo...etc)
	public String authToken;

	// mobile Token to send notifications to GCM or Apple Notification System
	public String mobileToken;

	// Date created
	@DateTime
	public Date creationDate;

	// First Name
	@Required
	@Max(20)
	public String firstName;

	// Last Name
	@Required
	@Max(30)
	public String lastName;

	// Index of the users default location, could have many
	@Embedded(mode = Mode.NATIVE)
	public Location defaultLoc;

	@Embedded(mode = Mode.NATIVE)
	public Location currentLoc;

	@Embedded
	public List<Location> locations;

	// User belongs to one account
	@Index("account_index")
	public Account acct;

	// is this an Account Manager
	public Boolean isAcctManager;

	// The role of this account
	@Required
	@Column("role")
	@Index("role_index")
	public int role;

	@Column("userProfile")
	@Embedded(mode = Mode.NATIVE)
	public Profile userProfile;

	// Contact email
	@Required
	@Email
	public String email;

	// Contact mobile phone number
	@Required
	@Phone
	public String mobilePhoneNumber;

	@Required
	public String userName;

	@Required
	@Password
	public String passwd;

	@Filter("favList")
	public static Query<FavoriteList> favList;

	@Filter("blackList")
	public static Query<BlackList> blkList;

	// Category Tags
	@Filter("usercategory_index")
	public static Query<UserCategory> category;

	@Filter("memployer_index")
	public static Query<Evaluation> empEvals;

	@Filter("mworker_index")
	public static Query<Evaluation> workerEvals;

	@Filter("microEmp")
	public static Query<MicroJob> mJobsEmployers;

	@Filter("microWorker")
	public static Query<MicroJob> mJobsWorkers;

	public User() {
		super();
		creationDate = new Date();
		locations = new ArrayList<Location>();
	}

	public void setLocations(Location l) {
		locations.add(l);
	}

	public static List<UserCategory> getUserCategorys() {
		return category.fetch();
	}

	public static List<MicroJob> getAllMicroJobsWorked() {
		return mJobsWorkers.fetch();
	}

	public static List<MicroJob> getAllMicroJobsEmployed() {
		return mJobsEmployers.fetch();
	}

	public static Query<User> all() {
		return Model.all(User.class);
	}

	public static User findByEmail(String email) {
		return all().filter("email", email).get();
	}

	public static void addFavorite(User u, User f) {
		FavoriteList favList = new FavoriteList(u, f);
		favList.insert();
		favList.save();
	}

	public void addFavorite(User f) {
		FavoriteList favList = new FavoriteList(this, f);
		favList.insert();
		favList.save();
	}

	public static void addBlackList(User u, User b) {
		BlackList blkList = new BlackList(u, b);
		blkList.insert();
		blkList.save();
	}

	public void addBlackList(User b) {
		BlackList blkList = new BlackList(this, b);
		blkList.insert();
		blkList.save();
	}

	public String toString() {
		return "User: " + userName + " Role: " + role + " authToken: "
				+ authToken + " mobileToken: " + mobileToken;
	}

	public static User findByUserName(String u) {
		return all().filter("userName", u).get();
	}

	public static User findByPhoneNumber(String u) {
		return all().filter("mobilePhoneNumber", u).get();
	}

	public static List<User> findByRole(Role r) {
		return all().filter("role", r).fetch();
	}

	public static User findById(String id) {
		return all().filter("id", id).get();
	}

	public static User findByAuthToken(String token) {
		return all().filter("authToken", token).get();
	}

	public static User findByPasswd(String u, String p) {
		return all().filter("userName", u).filter("passwd", p).get();
	}

	public List<Location> getAllLocations() {
		return locations;
	}

	public List<Evaluation> getWorkerEvaluations() {
		return workerEvals.fetch();
	}

	public List<Evaluation> getEmployeeEvaluations() {
		return empEvals.fetch();
	}

	public List<BlackList> getBlacklist() {
		return blkList.fetch();
	}

	public List<FavoriteList> getFavoritelist() {
		return favList.fetch();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((locations == null) ? 0 : locations.hashCode());
		result = prime * result
				+ ((isAcctManager == null) ? 0 : isAcctManager.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime
				* result
				+ ((mobilePhoneNumber == null) ? 0 : mobilePhoneNumber
						.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((acct == null) ? 0 : acct.hashCode());

		result = prime * result
				+ ((authToken == null) ? 0 : authToken.hashCode());
		result = prime * result
				+ ((currentLoc == null) ? 0 : currentLoc.hashCode());
		result = prime * result
				+ ((defaultLoc == null) ? 0 : defaultLoc.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) role;
		result = prime * result
				+ ((userProfile == null) ? 0 : userProfile.hashCode());
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
		User other = (User) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
			return false;

		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;

		if (isAcctManager == null) {
			if (other.isAcctManager != null)
				return false;
		} else if (!isAcctManager.equals(other.isAcctManager))
			return false;

		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		if (mobilePhoneNumber == null) {
			if (other.mobilePhoneNumber != null)
				return false;
		} else if (!mobilePhoneNumber.equals(other.mobilePhoneNumber))
			return false;

		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;

		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;

		if (acct == null) {
			if (other.acct != null)
				return false;
		} else if (!acct.equals(other.acct))
			return false;

		if (authToken == null) {
			if (other.authToken != null)
				return false;
		} else if (!authToken.equals(other.authToken))
			return false;

		if (currentLoc == null) {
			if (other.currentLoc != null)
				return false;
		} else if (!currentLoc.equals(other.currentLoc))
			return false;

		if (defaultLoc == null) {
			if (other.defaultLoc != null)
				return false;
		} else if (!defaultLoc.equals(other.defaultLoc))
			return false;

		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;

		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;

		if (role != other.role)
			return false;

		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		return true;
	}
}

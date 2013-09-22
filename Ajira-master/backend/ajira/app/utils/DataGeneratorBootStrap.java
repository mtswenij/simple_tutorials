package utils;

import play.*;
import play.jobs.*;
import play.test.*;
import siena.Model;
import siena.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import models.Account;
import models.Address;
import models.Bid;
import models.Category;
import models.JobCategory;
import models.JobType;
import models.Location;
import models.MicroJob;
import models.PaymentMethod;
import models.PaymentType;
import models.Profile;
import models.QualityMetrics;
import models.Role;
import models.Status;
import models.User;
import models.UserCategory;

//@OnApplicationStart
public class DataGeneratorBootStrap extends Job {

	private static String[] first = { "Lee", "Antonio", "Wardell", "Prashant",
			"David", "Lou", "Ronald", "Mary", "Nicki", "Chunmei", "Martin" };
	private static String[] last = { "Jones", "James", "Johnson", "Simpson",
			"Tyler", "Wasahington", "Davis", "Carmichael", "Green" };

	private static String[] State = { "OK", "TX", "IL", "CA", "AZ", "GA", "IA",
			"CO", "DC", "MA", "VA", "WV", "OH", "AR", "OR", "ND" };
	private static String[] City = { "Oklahoma City", "Tubelow", "Miami",
			"Atlanta", "Washington", "Seattle", "Los Angelas", "Houston",
			"Dallas", "Chicago" };
	private static String[] Country = { "USA", "CAN", "RSA", "UK", "AUS" };
	private static String[] Street = { "Duke St", "Sixth St",
			"Martin Luther King Dr.", "St. Paul St.", "North Capitol",
			"Sunset Blvd", "Rodeo Drive", "Preston" };

	private static String[] Email = { "gmail.com", "yahoo.com", "hotmail.com",
			"howard.edu" };
	private static String[] Loc = { "home", "work", "friends house" };

	private static String[] Categories = { "BBQ", "McDonalds", "KFC", "Wendys", "Clydes", "Fudruckers", "BSmiths", "Taco Bell", "Grocery", "Applebees", "Chipotle","Bus Boys", "Checkers","Burgers", "Chicken", "Seafood", "Red Lobster", "Pizza", "Pizza Hut"};
	
	private static String[] jobDetails = { "McDonalds #3 Meal",
			"Burger King #8 meal", "China Wonder Shrimp Fried  Rice",
			"Negril Fish Sandwich", "Potbelly Chicken Sandwidtch",
			"Clydes Cheesburger", "Mortons Steak Dinner", "Taco Bell #2 meal" };

	private static String[] instr = { "Extra Sauce", "Well Done", "Large Coke",
			"Large Sprite", "Please Ring Door Bell twice",
			"Leave food with secretary", "No salt on fries" };
	private Random generator;

	public DataGeneratorBootStrap() {
		generator = new Random(new Date().getTime());
	}

	public void doJob() {
		// TODO : Check if the database is empty first, if so regenerate data

		genAllCategories();
		genRandomAccounts(10);
		genRandomUsers(20);
		genRandomMicroJobs(20);
	}

	public void genAllCategories() {
	    for(int i = 0; i < Categories.length; i++) {
	    	new Category(Categories[i]).save();
	    }
	}
	
	private String genPhoneNumber() {
		String strippedNum;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;

		num1 = generator.nextInt(600) + 100;// numbers can't include an 8 or 9,
											// can't go below 100.
		num2 = generator.nextInt(641) + 100;// number has to be less than
											// 742//can't go below 100.
		num3 = generator.nextInt(8999) + 1000; // make numbers 0 through 9 for
												// each digit.//can't go below
												// 1000.

		strippedNum = Integer.toOctalString(num1);

		return strippedNum + "-" + num2 + "-" + num3;
	}

	private String genRandomString(int len) {
		StringBuffer sb = new StringBuffer();

		for (int x = 0; x < len; x++) {
			sb.append((char) ((int) (Math.random() * 26) + 97));
		}

		return sb.toString();
	}

	public Profile genRandomProfile(int r) {
		Profile p = new Profile();

		switch (generator.nextInt(9)) {

		case 0:
			p.useEmail = true;
			break;

		case 1:
			p.useEmail = false;
			break;

		case 2:
			p.useFacebook = true;
			break;
		case 3:
			p.useFacebook = false;
			break;
		case 4:
			p.usePhone = true;
			break;
		case 5:
			p.usePhone = false;
			break;
		case 6:
			p.useSMS = true;
			break;
		case 7:
			p.useSMS = false;
			break;
		}

		int type = 0;
		if (r == Role.MICRO_EMPLOYER)
			type = 0;
		else if (r == Role.MICRO_WORKER)
			type = 1;
		p.userQuality = genRandomQualityMetric(type);

		return p;
	}

	private String genRandomName() {
		return first[generator.nextInt(first.length)] + " "
				+ last[generator.nextInt(last.length)];
	}

	private String genRandomCreditCard() {
		StringBuffer sb = new StringBuffer();
		int num;

		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < 4; x++) {
				num = generator.nextInt(9) + 1;
				sb.append(Integer.toString(num));
			}
			sb.append("-");
		}

		return sb.toString();
	}

	private List<Location> genRandomLocation(int limit) {

		List<Location> l = new ArrayList<Location>();

		for (int i = 0; i < limit; i++) {
			float lat = generator.nextInt(20) + 30.0F;
			float lon = generator.nextInt(40) + 80.0F;
			Location lt = new Location(lat, lon);

			lt.title = Loc[generator.nextInt(Loc.length)];

			l.add(lt);
		}

		return l;
	}

	private int genRandomRole() {
		if (generator.nextFloat() > 0.50)
			return Role.MICRO_EMPLOYER;
		else
			return Role.MICRO_WORKER;
	}

	private int genRandomJobType() {
		if (generator.nextFloat() > 0.50)
			return JobType.Local;
		else
			return JobType.Virtual;
	}

	private int genRandomAuctionStatus() {
		int s;
		switch (generator.nextInt(7)) {
		case 0:
			s = Status.InRoute;
			break;

		case 1:
			s = Status.Delayed;
			break;

		case 2:
			s = Status.Delivered;
			break;
		case 3:
			s = Status.Pending;
			break;
		case 4:
			s = Status.Approaching;
			break;
		default:
			s = Status.Accepted;
			break;
		}

		return s;
	}

	private Address genRandomAddress() {
		Address a = new Address();

		a.city = City[generator.nextInt(City.length)];
		a.street = generator.nextInt(800000) + " "
				+ Street[generator.nextInt(Street.length)];
		a.state = State[generator.nextInt(State.length)];
		a.zip = "" + generator.nextInt(80000) + 10000;
		a.country = Country[generator.nextInt(Country.length)];

		return a;
	}

	private QualityMetrics genRandomQualityMetric(int type) {
		QualityMetrics q = new QualityMetrics();

		if (type == 0) // A microEmployer or A MicroJob Quality
		{
			q.attitude = generator.nextDouble();
			q.timeAllowance = generator.nextDouble();
			q.additionalCompensation = generator.nextDouble();
			q.price = generator.nextDouble();
		} else {
			q.attitude = generator.nextDouble();
			q.deliveryTime = generator.nextDouble();
			q.deliveryCondition = generator.nextDouble();
			q.location = generator.nextDouble();
		}
		return q;
	}

	private int genRandomJobStatus() {
		int s;
		switch (generator.nextInt(4)) {
		case 0:
			s = Status.Unconfirmed;
			break;
		case 1:
			s = Status.Closed;
			break;

		default:
			s = Status.Open;
			break;
		}
		return s;
	}

	private PaymentMethod genRandomPaymentMethod() {
		PaymentMethod meth = new PaymentMethod();

		switch (generator.nextInt(8)) {
		case 0:
			meth.airtel = genRandomString(10);
			meth.paytype = PaymentType.AirTel;
			break;
		case 1:
			meth.cardAddress = genRandomAddress();
			meth.cardName = genRandomName();
			meth.cardNumber = genRandomCreditCard();
			meth.expirationMonth = "" + generator.nextInt(11) + 1;
			meth.expirationYear = "" + 2013 + generator.nextInt(6);
			meth.securityCode = "" + generator.nextInt(899) + 100;
			meth.paytype = PaymentType.CreditCard;
			break;
		case 2:
			meth.gwalletID = genRandomString(15);
			meth.paytype = PaymentType.GoogleWallet;
			break;
		case 3:
			meth.mpesaID = genRandomString(12);
			meth.paytype = PaymentType.MPESA;
			break;
		case 4:
			meth.paypalID = genRandomString(20);
			meth.paytype = PaymentType.PayPal;
			break;
		case 5:
			meth.orangeMoneyID = genRandomString(20);
			meth.paytype = PaymentType.OrangeMoney;
			break;
		default:
			meth.yuCashID = genRandomString(20);
			meth.paytype = PaymentType.YUCash;
			break;
		}

		return meth;
	}

	public void genRandomAccounts(int limit) {
		int x;

		// generate limit accounts and acct managers and store them in the DB
		for (x = 0; x < limit; x++) {
			Account a = new Account();
			User u = new User();

			a.acctName = "This is Test acct#" + x;

			a.billing = genRandomPaymentMethod();

			u.creationDate = a.creationDate = new Date(generator.nextLong());
			u.email = a.email = "user" + x + "@"
					+ Email[generator.nextInt(Email.length)];
			u.mobilePhoneNumber = a.phoneNumber = genPhoneNumber();
			u.isAcctManager = true;

			// insert into DB so ID is generated
			a.save();

			u.acct = a; // link a user to an account (this is the acct
						// manager...so it must be done at the creation time of
						// the account
			u.authToken = genRandomString(30);

			u.save(); // commit

			u.locations = genRandomLocation(generator.nextInt(2) + 1);
			Location[] l = u.locations
					.toArray(new Location[u.locations.size()]);

			// select a ramdom locations
			u.currentLoc = l[generator.nextInt(l.length)];
			u.defaultLoc = l[generator.nextInt(l.length)];

			u.firstName = first[generator.nextInt(first.length)];
			u.lastName = last[generator.nextInt(last.length)];

			u.role = genRandomRole();

			u.userProfile = genRandomProfile(u.role);

			// update the DB
			u.update();
		}
	}

	
	private void genRandomJabCategories(MicroJob mj) {
		
		int numOfCat;
		List<Category> clist = Category.all(Category.class).fetch();
		Category[] cat = clist.toArray(new Category[clist.size()]);
		
		numOfCat = generator.nextInt(cat.length);
		
		for(int i = 0; i < numOfCat; i++) {
			new JobCategory(mj, cat[generator.nextInt(cat.length)]);
		}
	}
	
	private void genRandomUserCategories(User u) {
		
		int numOfCat;
		List<Category> clist = Category.all(Category.class).fetch();
		Category[] cat = clist.toArray(new Category[clist.size()]);
		
		numOfCat = generator.nextInt(cat.length);
		
		for(int i = 0; i < numOfCat; i++) {
			new UserCategory(u, cat[generator.nextInt(cat.length)]);
		}
	}
	
	public void genRandomUsers(int limit) {
		int x;

		List<Account> alist = Account.all(Account.class).fetch();
		Account[] acct = alist.toArray(new Account[alist.size()]);

		// generate limit users and store them in the DB
		for (x = 0; x < limit; x++) {
			// generate some users
			User u = new User();
			u.creationDate = new Date(generator.nextLong());
			u.email = "user" + (x + 100) + "@"
					+ Email[generator.nextInt(Email.length)];
			u.passwd = genRandomString(8);
			u.mobilePhoneNumber = genPhoneNumber();
			u.userName = "user" + (x + 100);
			u.isAcctManager = true;

			u.acct = acct[generator.nextInt(acct.length)]; // link a user to a
															// random account
			u.authToken = genRandomString(30);

			u.save(); // commit

			u.locations = genRandomLocation(generator.nextInt(2) + 1);
			Location[] l = u.locations
					.toArray(new Location[u.locations.size()]);

			// select a ramdom locations
			u.currentLoc = l[generator.nextInt(l.length)];
			u.defaultLoc = l[generator.nextInt(l.length)];

			u.firstName = first[generator.nextInt(first.length)];
			u.lastName = last[generator.nextInt(last.length)];

			u.role = genRandomRole();

			u.userProfile = genRandomProfile(u.role);

			genRandomUserCategories(u);
			
			// update the DB
			u.update();
		}
	}

	private void genRandomBids(MicroJob mj, User[] workers) {

		int limit = generator.nextInt(workers.length) + 1; // number of bids
		int x;

		// generate limit Bids and associate them to the microJob and store in
		// DB
		for (x = 0; x < limit; x++) {

			Bid b = new Bid();

			b.bidPostTime = new Date(mj.submitionDateTime.getTime()
					+ generator.nextInt(100000) + 1000);
			b.deliveryTime = new Date(mj.deliveryDateTime.getTime() 
					+ generator.nextInt(1000000000) + 10000000);
			b.microWk = workers[generator.nextInt(workers.length)];
			b.mJob = mj;
			b.price = generator.nextInt((int) mj.maxPrice - 1) + 1.0;

			// insert into DB
			b.save();
		}
	}

	public void genRandomMicroJobs(int limit) {
		int x;
		
		List<User> ulist = Model.all(User.class)
				.filter("role", Role.MICRO_EMPLOYER).fetch(); 
																
		User[] eUsers = ulist.toArray(new User[ulist.size()]);

		ulist = Model.all(User.class).filter("role", Role.MICRO_WORKER)
				.fetch(); 
		User[] wUsers = ulist.toArray(new User[ulist.size()]);

		// generate limit microJobs and store them in the DB
		for (x = 0; x < limit; x++) {
			User emp = eUsers[generator.nextInt(eUsers.length)];
			User worker = wUsers[generator.nextInt(wUsers.length)];

			String details = "Get me some "
					+ jobDetails[generator.nextInt(jobDetails.length)];
			Date deliveryDateTime = new Date(new Date().getTime()
					+ generator.nextInt(1000000000) + 100000000);
			Date submitionDateTime = new Date(new Date().getTime()
					+ generator.nextInt(10000000) + 10000);

			List<Location> lo = genRandomLocation(1);
			Location deliveryDestination = lo.get(0);

			QualityMetrics quality = genRandomQualityMetric(0); // 0 for
																// microEmp
			double totalRadius = generator.nextInt(5) + 1.0;
			float maxPrice = generator.nextInt(1000) + 25.0F;
			int auctionStatus = genRandomAuctionStatus();

			int jstat = genRandomJobStatus();

			if (Status.isClosed(jstat)) // if the job status is random selected as
									// closed then the auction should be over
				auctionStatus = Status.Accepted;
			else {
				if (!Status.isAccepted(auctionStatus)) // if we haven't finished the
													// auction then there is no
													// worker assigned
					worker = null;
			}

			int jType = genRandomJobType();

			MicroJob job = new MicroJob(emp, worker, details,
					deliveryDestination, deliveryDateTime, submitionDateTime,
					quality, totalRadius, maxPrice, auctionStatus, jType);

			job.jobStatus = jstat;
			job.instructions = instr[generator.nextInt(instr.length)];

			job.save();

			genRandomBids(job, wUsers); // Generate some bids
			genRandomJabCategories(job);
			
			job.update();
		}
	}
}

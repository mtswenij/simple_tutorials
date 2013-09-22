package models;

import java.util.*;

import managers.ManagerAccess;

import siena.*;
import siena.embed.Embedded;
import siena.embed.Embedded.Mode;

import play.data.validation.Required;

public class MicroJob extends Model {
	public final static double fractionTime = 0.10;

	public final double defaultRadius = 2; // miles

	@Id(Generator.UUID)
	public String id;

	// Job Description
	@Required
	@Max(250)
	public String description;

	// Special instructions
	@Max(250)
	public String instructions;

	// The ID of the person that sent the request
	@Required
	@Index("microEmp")
	public User microEmployer;

	// The provider selected to fulfill the request
	@Index("microWorker")
	@Required
	public User microWorker;

	@Required
	public double totalRadius;

	@Embedded
	public List<String> AuctionResults;
	
	public MicroJob(User mE, User mW, String dets, Location dest, Date del,
			Date submitdt, QualityMetrics met, double totRad, float maxp,
			int auctst, int jt) {
		microEmployer = mE;
		microWorker = mW;
		details = dets;
		deliveryDateTime = del;
		submitionDateTime = submitdt;
		deliveryDestination = dest;
		quality = met;
		maxPrice = maxp;
		auctionStatus = auctst;
		totalRadius = totRad;
		jType = jt;
	}

	public void setAuctionResults(List<String> res) {
		AuctionResults = res;
	}
	
	public MicroJob()
	{
		
	}

	public List<String> getAuctionResults() {
		// .filter("id IN", idList).fetch()
		return AuctionResults;
	}

	public int getAuctionEndtime() {

		long dif = deliveryDateTime.getTime() - submitionDateTime.getTime();
		return (int) (dif / 1000);
	}

	public void accpetWinner() {    // set the winner, and notify all others 
		String id = AuctionResults.get(0);

		Bid b = bids.filter("id", id).get();
		microWorker = Model.all(User.class).filter("id", b.microWk.id).get();
		
		//send notifications to all loser
	    ManagerAccess.NotificationManager.SendNotification(this.microEmployer.id, "", Notification.LOSER, Notification.AUCTION, 5, this.id);

	}
	
	public void declineWinner() {    // when winner declines, select a new one and send a notification
		String id = AuctionResults.get(0);
		AuctionResults.remove(0);

		bids.filter("id", id).delete();
		
		// get the new winner
		id = AuctionResults.get(0);

		Bid b = bids.filter("id", id).get();

		//send notification to new winner
	    ManagerAccess.NotificationManager.SendNotification(this.microEmployer.id, b.microWk.id, Notification.WINNER, Notification.AUCTION, 5, this.id);

	}

	public void sendWinnerNotifcation() {
		String id = AuctionResults.get(0);
		
		Bid b = bids.filter("id", id).get();

		//send notification to winner
	    ManagerAccess.NotificationManager.SendNotification(this.microEmployer.id, b.microWk.id, Notification.WINNER, Notification.AUCTION, 5, this.id);
	}
	
	public Bid getWinner() {
		String id = AuctionResults.get(0);
		
		Bid b = bids.filter("id", id).get();

	    return b;
	}

	public void setFinalJobPrice() {
		String id = AuctionResults.get(0);

		actPrice = bids.filter("id", id).get().price;
	}

	/* MICRO_JOB INFORMATION */

	// What is being requested?
	@Required
	public String details;

	// The preferred delivery time
	@Required
	@DateTime
	public Date deliveryDateTime;

	// The time when the request was submitted
	@Required
	@DateTime
	public Date submitionDateTime;

	@Required
	public int jType; // the Job Type

	// Request Destination
	@Embedded(mode = Mode.NATIVE)
	public Location deliveryDestination;

	// Preferred Quality Metrics
	@Embedded(mode = Mode.NATIVE)
	public QualityMetrics quality;

	// Maximum Price
	@Required
	public double maxPrice;

	public double actPrice; // actual price that the microworker gets paid

	/* STATUSES */

	// Job Status
	public int jobStatus;

	public int auctionStatus;

	// Current Status of the request
	public int deliveryStatus;

	/* Filters */

	// Bids by price
	@Filter("employer_index")
	public Query<Bid> bids;

	// Category Tags
	@Filter("jobcategory_index")
	public Query<JobCategory> category;

	/* method Section */

	// set microjob status
	public void setJobStatus(int stat) {
		jobStatus = stat;
	}

	// set auction status
	public void setAuctionStatus(int stat) {
		auctionStatus = stat;
	}

	// check to see if auction is open
	public boolean isOpen() {
		return Status.isOpen(auctionStatus);
	}

	// check to see if auction has unconfirmed winner
	public boolean isUnconfirmed() {
		return Status.isUnconfirmed(auctionStatus);
	}

	// check to see if auction is closed
	public boolean isClosed() {
		return Status.isClosed(auctionStatus);
	}

	public static Query<MicroJob> all() {
		return Model.all(MicroJob.class);
	}

	public static List<MicroJob> findByEmployer(User employer) {
		return all().filter("microEmployer", employer).fetch();
	}

	public static MicroJob findById(String id) {
		return all().getByKey(id);
	}

	public static List<MicroJob> findByEmployerAndWorker(User employer,
			User worker) {
		return all().filter("microEmployer", employer)
				.filter("microWorker", worker).fetch();
	}

	public static List<MicroJob> findByWorker(User worker) {
		return all().filter("microWorker", worker).fetch();
	}

	public String toString() {
		return "MicroJobID: "
				+ id
				+ "Employer: "
				+ microEmployer
				+ " Worker: "
				+ ((microWorker != null) ? microWorker : "Auction Not Complete");
	}

	public List<JobCategory> GetCategories() {
		return category.fetch();
	}

	public List<Bid> GetBidPricesAssendingOrder() {

		// ordered by price

		return bids.filter("mJob", this).order("price").fetch();

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) this.actPrice;
		result = prime * result + this.auctionStatus;
		result = prime * result + (int) this.defaultRadius;
				
		result = prime * result + this.deliveryStatus;
		result = prime
				* result
				+ ((this.description == null) ? 0 : this.description
						.hashCode());
		result = prime * result
				+ ((this.details == null) ? 0 : this.details.hashCode());
		result = prime * result + ((this.instructions == null) ? 0 : this.instructions.hashCode());
		result = prime * result + this.jobStatus;

		result = prime * result + this.jType;
		result = prime * result
				+ (int) this.maxPrice;
		result = prime * result + (int) this.totalRadius;
		result = prime * result
				+ ((this.deliveryDateTime == null) ? 0 : this.deliveryDateTime.hashCode());
		result = prime * result
				+ ((this.microEmployer == null) ? 0 : this.microEmployer.hashCode());
		result = prime * result
				+ ((this.microWorker == null) ? 0 : this.microWorker.hashCode());
		result = prime * result
				+ ((this.quality == null) ? 0 : this.quality.hashCode());
		result = prime * result
				+ ((this.submitionDateTime == null) ? 0 : this.submitionDateTime.hashCode());
		result = prime * result + (int) this.fractionTime;
		
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
		MicroJob other = (MicroJob) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (actPrice == other.actPrice) 
			return false;
		if (auctionStatus == other.auctionStatus) 
			return false;
		if (defaultRadius == other.defaultRadius) 
			return false;
		if (deliveryStatus == other.deliveryStatus) 
			return false;
		if (jobStatus == other.jobStatus) 
			return false;
		if (jType == other.jType) 
			return false;
		if (maxPrice == other.maxPrice) 
			return false;
		if (totalRadius == other.totalRadius) 
			return false;
		if (fractionTime == other.fractionTime) 
			return false;
		
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;

		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (deliveryDateTime == null) {
			if (other.deliveryDateTime != null)
				return false;
		} else if (!deliveryDateTime.equals(other.deliveryDateTime))
			return false;

		if (microEmployer == null) {
			if (other.microEmployer != null)
				return false;
		} else if (!microEmployer.equals(other.microEmployer))
			return false;

		if (microWorker == null) {
			if (other.microWorker != null)
				return false;
		} else if (!microWorker.equals(other.microWorker))
			return false;

		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;

		if (submitionDateTime == null) {
			if (other.submitionDateTime != null)
				return false;
		} else if (!submitionDateTime.equals(other.submitionDateTime))
			return false;
		return true;
	}
}
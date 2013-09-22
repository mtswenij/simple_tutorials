package models;

import java.util.*;

import siena.*;

import play.data.validation.Required;

public class Bid extends Model {
	@Id(Generator.UUID)
	public String id;

	// Micro Job and Employer Bid belongs to
	@Index("employer_index")
	@Required
	public MicroJob mJob;

	// Service provider ID
	@Required
	public User microWk;

	// When the bid was placed or last updated
	@Required
	public Date bidPostTime;

	// Proposed price to fulfill the request
	@Required
	public double price;

	// Predicted time it will take to fulfill the request (In minutes)
	@Required
	public Date deliveryTime;

	@Override
	public String toString() {
		return "[" + bidPostTime + "] Bid: $" + price + "on Job[" + mJob
				+ "] from user(" + microWk + ")";
	}

	static Query<Bid> all() {
		return Model.all(Bid.class);
	}

	public static Bid findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<Bid> findByMicroEmployer(User me) {
		return all().filter("id", me.id).fetch();
	}

	public int compareTo(Bid compareObject) {
		if (this.price < compareObject.price)
			return -1;
		else if (this.price == compareObject.price)
			return 0;
		else
			return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mJob == null) ? 0 : mJob.hashCode());
		result = prime * result + ((microWk == null) ? 0 : microWk.hashCode());
		result = prime * result
				+ ((bidPostTime == null) ? 0 : bidPostTime.hashCode());
		result = prime * result + (int) price;
		result = prime * result
				+ ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
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
		Bid other = (Bid) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (price != other.price)
			return false;

		if (mJob == null) {
			if (other.mJob != null)
				return false;
		} else if (!mJob.equals(other.mJob))
			return false;

		if (microWk == null) {
			if (other.microWk != null)
				return false;
		} else if (!microWk.equals(other.microWk))
			return false;
		if (bidPostTime == null) {
			if (other.bidPostTime != null)
				return false;
		} else if (!bidPostTime.equals(other.bidPostTime))
			return false;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;

		return true;
	}

}

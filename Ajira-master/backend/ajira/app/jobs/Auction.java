package jobs;

import play.jobs.*;

import java.util.*;

import models.Bid;
import models.Location;
import models.MicroJob;
import models.RankBid;
import models.Status;
import models.User;
import siena.*;

public class Auction extends Job<List<String>> {
	private MicroJob mj;

	public Auction(MicroJob m) {
		// read Job from DB
		mj = MicroJob.findById(m.id);
	}

	public List<String> doJobWithResult() {
		List<String> resultList = new ArrayList<String>();

		List<Bid> bids = mj.GetBidPricesAssendingOrder();
		// location calculations

		User microEmp = User.findById(mj.microEmployer.id);

		Location cLoc;
		double locQuality;
		List<RankBid> rBids = new ArrayList<RankBid>();
		for (Bid b : bids) {
			RankBid rb = new RankBid(b);
			cLoc = b.microWk.currentLoc; // get the current loc of the bidder
			locQuality = cLoc.getQuality(mj.defaultRadius, mj.totalRadius,
					mj.deliveryDestination); // calculate the quality of the
												// location between bidder and
												// employer

			// set the location qaulty score
			b.microWk.userProfile.userQuality.setLocationQuality(locQuality);

			// calculate the quality score
			rb.qualityScore = b.microWk.userProfile.userQuality
					.CalculateQualityScore(0, microEmp.userProfile.userQuality);
			
			// calculate the rank
			rb.calcRank();
			
			// add to sorted list by rank
			rBids.add(rb);
		}

		ArrayList k = new ArrayList<RankBid>(rBids); // convert to arraylist to sort
		Collections.sort(k);  // sort by rank

		// calculate the actual price received
		RankBid r0, r1, prev;
	    for(int i = 0; i < k.size(); i++) {
	         r0 = (RankBid) k.get(i);
	         
	         prev = r0;   // keep a ref to the previous bid
	         
	         if((i+1) < k.size()) {
	        	 r1 = (RankBid) k.get(i);
	         
	             r0.price = r1.getRank() / r0.qualityScore;
	         }
	         else
	        	 r0.price = prev.price;  // set the last two bids to have the price
	         
	         // store id of worker who bidded
	         resultList.add(r0.b.id);
	    }

		mj.auctionStatus = Status.Unconfirmed;

		return resultList;
	}
}

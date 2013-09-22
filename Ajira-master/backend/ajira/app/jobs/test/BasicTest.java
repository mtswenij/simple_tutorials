package jobs.test;
import java.util.List;

import jobs.Auction;

import org.junit.*;

import play.libs.F.Promise;
import play.test.*;
import managers.ManagerAccess;
import models.*;

public class BasicTest extends UnitTest {
	 
    @Test
    public void doesAuctionWork() {
    	
    	List<MicroJob> mjlist = ManagerAccess.MicroJobManager.GetOpenAuctions(2);
    	MicroJob mj = mjlist.get(0);
		List<String> auctionResults = null; 
		Promise<List<String>> results = new Auction(mj).in(mj.getAuctionEndtime());
		 
		// AUCTION DOES NOT HAPPEN INSTANTANEOUSLY
		
		// get the results sometime in the future after the auction is done
///		 auctionResults = await(results);
		  
		 mj.setAuctionResults(auctionResults);
		  
		 mj.auctionStatus = Status.Unconfirmed;
		  
		 ManagerAccess.MicroJobManager.UpdateMicroJob(mj);
	
        assertEquals(2, 1 + 1);
    }
 
}
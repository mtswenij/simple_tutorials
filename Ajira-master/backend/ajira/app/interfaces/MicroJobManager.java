package interfaces;

import java.util.*;

import jobs.Auction;

import models.*;

public interface MicroJobManager {
	
	/*CRUD MICROJOBS*/
	
	public boolean CreateMicrojob(MicroJob model);

	public MicroJob GetMicroJob(String microJobID);

	public MicroJob UpdateMicroJob(MicroJob model);

	public boolean DeleteMicroJob(String microJobID);
	
	/*GET MICROJOBS*/

	public List<MicroJob> GetAllMicroJobsByEmployerID(String microEmployerID);
	
	public List<MicroJob> GetAllMicroJobsByWorkerID(String microEmployerID);
	
	public List<MicroJob> GetCurrentMicroJobsByEmployerID(String microEmployerID);
	
	public List<MicroJob> GetCurrentMicroJobsByWorkerID(String microEmployerID);
	
	public List<MicroJob> GetPastMicroJobsByEmployerID(String microEmployerID);
	
	public List<MicroJob> GetPastMicroJobsByWorkerID(String microEmployerID);

	public List<MicroJob> GetMicroJobsByLocation(Location location, String microWorkerID, boolean IsMarketOpen);

	public List<MicroJob> GetMicroJobsByCategories(List<JobCategory> categories, String microWorkerID, boolean IsMarketOpen);

	public List<MicroJob> GetMicroJobsByLocationAndByCategories(Location location, List<JobCategory> categories, String microWorkerID, boolean IsMarketOpen);

	/*CRUD AUCTION*/
	
	public boolean CreateMicroJobAuction(String microJobID);

	public boolean UpdateMicroJobAuction(String microJobID, MicroJob mj);

	public boolean DeleteMicroJobAuction(String microJobID);

	public List<Bid> GetBids(String microJobID);

	public List<MicroJob> GetOpenAuctions(int limit);

	public List<MicroJob> GetClosedAuctions(int limit);

	public List<MicroJob> GetPendingAuctions(int limit);

	public List<MicroJob> GetAuctions(int limit);

	/*OTHERS*/

	public boolean AcceptMicroJob(String microJobID, String microWorkerID);

	public boolean DeclineMicroJob(String microJobID, String microWorkerID);

	public void SetMicroJobStatus(String microJobID, Status status);

	public boolean CompleteMicroJob(String microJobID, String microWorkerPin, String microEmployerPin);
}

package managers;

import java.util.ArrayList;
import java.util.List;

import siena.Model;
import utils.Auth;

import interfaces.MicroJobManager;
import models.*;

public class MicroJobMgr implements MicroJobManager {

	@Override
	public boolean CreateMicrojob(MicroJob newMicroJob) {
		newMicroJob.insert();
		newMicroJob.save();
		return true;
	}

	@Override
	public MicroJob GetMicroJob(String microJobID) {
		return Model.all(MicroJob.class).filter("id", microJobID).get();
	}

	@Override
	public MicroJob UpdateMicroJob(MicroJob microJob) {
		microJob.update();
		return null;
	}

	@Override
	public boolean DeleteMicroJob(String microJobID) {
		MicroJob mj = Model.all(MicroJob.class).filter("id", microJobID).get();
		mj.delete();
		return false;
	}

	@Override
	public List<MicroJob> GetAllMicroJobsByEmployerID(String microEmployerID) {
		List<MicroJob> jobs = Model.all(MicroJob.class)
				.filter("microEmployer", User.findByEmail(Auth.getGoogleEmail()))
				.order("-submitionDateTime").fetch();
		return jobs;
	}

	@Override
	public List<MicroJob> GetAllMicroJobsByWorkerID(String microEmployerID) {
		List<MicroJob> jobs = Model.all(MicroJob.class)
				   .filter("microWorker", User.findByEmail(Auth.getGoogleEmail()))
				   .order("-submitionDateTime")
				   .fetch();
		return jobs;
	}

	@Override
	public List<MicroJob> GetCurrentMicroJobsByEmployerID(String microEmployerID) {
		List<MicroJob> jobs = new ArrayList<MicroJob>();

		Iterable<MicroJob> Ijobs = Model.all(MicroJob.class)
				.filter("microEmployer", User.findById(microEmployerID))
				.order("-submitionDateTime").iter();
		for (MicroJob job : Ijobs) {
			if (!Status.isDelivered(job.jobStatus)) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public List<MicroJob> GetCurrentMicroJobsByWorkerID(String microWorkerID) {
		List<MicroJob> jobs = new ArrayList<MicroJob>();
		
    	Iterable<MicroJob> Ijobs = Model.all(MicroJob.class)
	    								.filter("microWorker", User.findById(microWorkerID))
	    								.order("-submitionDateTime")	
										.iter();
		for(MicroJob job : Ijobs) {
			if(!Status.isDelivered(job.jobStatus)) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public List<MicroJob> GetPastMicroJobsByEmployerID(String microEmployerID) {
		List<MicroJob> jobs = new ArrayList<MicroJob>();

		Iterable<MicroJob> Ijobs = Model.all(MicroJob.class)
				.filter("microEmployer", User.findById(microEmployerID))
				.order("-submitionDateTime").iter();
		for (MicroJob job : Ijobs) {
			if (Status.isDelivered(job.jobStatus)) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public List<MicroJob> GetPastMicroJobsByWorkerID(String microWorkerID) {
		List<MicroJob> jobs = new ArrayList<MicroJob>();
		
    	Iterable<MicroJob> Ijobs = Model.all(MicroJob.class)
	    								.filter("microWorker", User.findById(microWorkerID))
	    								.order("-submitionDateTime")	
										.iter();
		for(MicroJob job : Ijobs) {
			if(job.jobStatus == Status.Delivered) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public List<MicroJob> GetMicroJobsByLocation(Location location,
			String microWorkerID, boolean IsMarketOpen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetMicroJobsByCategories(
			List<JobCategory> categories, String microWorkerID,
			boolean IsMarketOpen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetMicroJobsByLocationAndByCategories(
			Location location, List<JobCategory> categories,
			String microWorkerID, boolean IsMarketOpen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CreateMicroJobAuction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateMicroJobAuction(String microJobID, MicroJob mj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteMicroJobAuction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Bid> GetBids(String microJobID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetOpenAuctions(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetClosedAuctions(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetPendingAuctions(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MicroJob> GetAuctions(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AcceptMicroJob(String microJobID, String microWorkerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeclineMicroJob(String microJobID, String microWorkerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void SetMicroJobStatus(String microJobID, Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CompleteMicroJob(String microJobID, String microWorkerPin,
			String microEmployerPin) {
		// TODO Auto-generated method stub
		return false;
	}

}

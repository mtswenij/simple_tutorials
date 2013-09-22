package managers.mobile;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import play.mvc.Controller;

import siena.Model;
import utils.Auth;

import interfaces.MicroJobManager;
import models.*;
import managers.ManagerAccess;

import com.google.gson.Gson;

public class MicroJobMgrMobile /*implements MicroJobManager*/ extends Controller {

	
	/*MICROJOB MANAGER MOBILE IMPLEMENTATION*/
	
	private static Gson gson = new Gson();

	public static void CreateMicrojob(String authToken) {
		// boolean TODO Auto-generated method stub
		MicroJob microJob = gson.fromJson(params.get("microJob"), MicroJob.class);
		renderJSON(ManagerAccess.MicroJobManager.CreateMicrojob(microJob));
	}

	public static void GetMicroJob(String authToken) {
		// MicroJob TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		renderJSON(ManagerAccess.MicroJobManager.GetMicroJob(microJobID));
	}

	public static void UpdateMicroJob(String authToken) {
		// MicroJob TODO Auto-generated method stub
		MicroJob microJob = gson.fromJson(params.get("microJob"), MicroJob.class);
		renderJSON(ManagerAccess.MicroJobManager.UpdateMicroJob(microJob));
	}

	public static void DeleteMicroJob(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		renderJSON(ManagerAccess.MicroJobManager.DeleteMicroJob(microJobID));
	}

	public static void GetAllMicroJobsByEmployerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microEmployerID = params.get("microEmployerID");
		renderJSON(ManagerAccess.MicroJobManager.GetAllMicroJobsByEmployerID(microEmployerID));
	}

	public static void GetAllMicroJobsByWorkerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microWorkerID = params.get("microWorkerID");
		renderJSON(ManagerAccess.MicroJobManager.GetAllMicroJobsByWorkerID(microWorkerID));
	}

	public static void GetCurrentMicroJobsByEmployerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microEmployerID = params.get("microEmployerID");
		renderJSON(ManagerAccess.MicroJobManager.GetCurrentMicroJobsByEmployerID(microEmployerID));
	}

	public static void GetCurrentMicroJobsByWorkerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microWorkerID = params.get("microWorkerID");
		renderJSON(ManagerAccess.MicroJobManager.GetCurrentMicroJobsByWorkerID(microWorkerID));
	}

	public static void GetPastMicroJobsByEmployerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microEmployerID = params.get("microEmployerID");
		renderJSON(ManagerAccess.MicroJobManager.GetPastMicroJobsByEmployerID(microEmployerID));
	}

	public static void GetPastMicroJobsByWorkerID(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		String microWorkerID = params.get("microWorkerID");
		renderJSON(ManagerAccess.MicroJobManager.GetPastMicroJobsByWorkerID(microWorkerID));
	}

	public static void GetMicroJobsByLocation(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		Location location = gson.fromJson(params.get("location"), Location.class);
		String microWorkerID = params.get("microWorkerID");
		boolean IsMarketOpen = Boolean.parseBoolean(params.get("IsMarketOpen"));
		renderJSON(ManagerAccess.MicroJobManager.GetMicroJobsByLocation(location, microWorkerID, IsMarketOpen));
	}

	public static void GetMicroJobsByCategories(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		List<JobCategory> categories = Arrays.asList(gson.fromJson(params.get("categories"), JobCategory[].class));
		String microWorkerID = params.get("microWorkerID");
		boolean IsMarketOpen = Boolean.parseBoolean(params.get("IsMarketOpen"));
		renderJSON(ManagerAccess.MicroJobManager.GetMicroJobsByCategories(categories, microWorkerID, IsMarketOpen));
	}

	public static void GetMicroJobsByLocationAndByCategories(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		Location location = gson.fromJson(params.get("location"), Location.class);
		List<JobCategory> categories = Arrays.asList(gson.fromJson(params.get("categories"), JobCategory[].class));
		String microWorkerID = params.get("microWorkerID");
		boolean IsMarketOpen = Boolean.parseBoolean(params.get("IsMarketOpen"));
		renderJSON(ManagerAccess.MicroJobManager.GetMicroJobsByLocationAndByCategories(location, categories, microWorkerID, IsMarketOpen));
	}

	public static void CreateMicroJobAuction(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		renderJSON(ManagerAccess.MicroJobManager.CreateMicroJobAuction(microJobID));
	}

	public static void UpdateMicroJobAuction(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		MicroJob microJob = gson.fromJson(params.get("microJob"), MicroJob.class);
		renderJSON(ManagerAccess.MicroJobManager.UpdateMicroJobAuction(microJobID, microJob));
	}

	public static void DeleteMicroJobAuction(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		renderJSON(ManagerAccess.MicroJobManager.DeleteMicroJobAuction(microJobID));
	}

	public static void GetBids(String authToken) {
		// List<Bid> TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		renderJSON(ManagerAccess.MicroJobManager.GetBids(microJobID));
	}

	public static void GetOpenAuctions(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		int limit = Integer.parseInt(params.get("limit"));
		renderJSON(ManagerAccess.MicroJobManager.GetOpenAuctions(limit));
	}

	public static void GetClosedAuctions(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		int limit = Integer.parseInt(params.get("limit"));
		renderJSON(ManagerAccess.MicroJobManager.GetClosedAuctions(limit));
	}

	public static void GetPendingAuctions(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		int limit = Integer.parseInt(params.get("limit"));
		renderJSON(ManagerAccess.MicroJobManager.GetPendingAuctions(limit));
	}

	public static void GetAuctions(String authToken) {
		// List<MicroJob> TODO Auto-generated method stub
		int limit = Integer.parseInt(params.get("limit"));
		renderJSON(ManagerAccess.MicroJobManager.GetAuctions(limit));
	}

	public static void AcceptMicroJob(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		String microWorkerID = params.get("microWorkerID");
		renderJSON(ManagerAccess.MicroJobManager.AcceptMicroJob(microJobID, microWorkerID));
	}

	public static void DeclineMicroJob(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		String microWorkerID = params.get("microWorkerID");
		renderJSON(ManagerAccess.MicroJobManager.DeclineMicroJob(microJobID, microWorkerID));
	}

	public static void SetMicroJobStatus(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		int status = Integer.parseInt(params.get("status"));
		renderJSON("");
	}

	public static void CompleteMicroJob(String authToken) {
		// boolean TODO Auto-generated method stub
		String microJobID = params.get("microJobID");
		String microWorkerPin = params.get("microWorkerPin");
		String microEmployerPin = params.get("microEmployerPin");
		renderJSON(ManagerAccess.MicroJobManager.CompleteMicroJob(microJobID, microWorkerPin, microEmployerPin));
	}

}

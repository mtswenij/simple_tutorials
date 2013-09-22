package controllers;

import play.*;
import play.modules.gae.GAE;
import play.mvc.*;
import siena.Model;
import utils.Auth;

import java.text.DateFormat;
import java.util.*;

import com.google.gson.Gson;

import managers.ManagerAccess;
import models.*;

public class MicroEmployer extends Controller {

	@Before
	static void checkConnected() {
		if (Auth.getGoogleUser() == null) {
			Users.login();
		} else {
			renderArgs.put("user", Auth.getGoogleEmail());
		}
	}

	public static void index() {
		/*
		 * MicroJob j = new MicroJob(User.findById(173L), null, null, null,
		 * null, null, null, 0, 0, null, null); j.deliveryDateTime = new Date();
		 * j.submitionDateTime = new Date(); j.jobStatus = new
		 * Status(Status.Delivered); j.insert(); j.save();
		 * 
		 * BootStrap b = new BootStrap(); b.doJob();
		 */

		if (Auth.isLoggedInGoogle()) {
			User user = User.findByEmail(Auth.getGoogleEmail());
			if (user == null) {
				Users.registerGoogleUser();
			}
		}
		
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetCurrentMicroJobsByEmployerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}

	public static void pastMJ() {		
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetPastMicroJobsByEmployerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}

	public static void allMJ() {
		List<MicroJob> jobs = ManagerAccess.MicroJobManager.GetAllMicroJobsByEmployerID(Auth.getUserID());
		int count = jobs.size();
		render(jobs, count);
	}
}
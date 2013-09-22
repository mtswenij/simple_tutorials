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

import jobs.AccountNotifier;
import jobs.BidNotifier;
import jobs.MicroJobNotifier;
import jobs.SystemWideNotifier;
import jobs.UserNotifier;

import models.Account;
import models.Address;
import models.Bid;
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

//@OnApplicationStart
public class BootStrap extends Job {


	public BootStrap() {
		
	}

	public void doJob() {
		
        startNotifiers();  // launch cron jobs
	}

	private void startNotifiers() {
		
		new AccountNotifier();
		new BidNotifier();
		new MicroJobNotifier();
		new SystemWideNotifier();
		new UserNotifier();
	}
}

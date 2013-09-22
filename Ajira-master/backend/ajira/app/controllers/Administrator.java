package controllers;

import play.*;
import play.mvc.*;
import siena.Model;
import utils.Auth;

import java.text.DateFormat;
import java.util.*;

import models.*;

public class Administrator extends Controller {

	@Before
	static void checkConnected() {
		if (Auth.getGoogleUser() == null) {
			Users.login();
		} else {
			renderArgs.put("user", Auth.getGoogleEmail());
		}
	}

	public static void index() {

		List<MicroJob> jobs = Model.all(MicroJob.class).fetch();

		render(jobs);

	}
}
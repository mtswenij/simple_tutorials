package controllers;

import play.*;
import play.mvc.*;
import utils.Auth;

import java.text.DateFormat;
import java.util.*;

import models.*;

public class Application extends Controller {

	@Before
	static void checkConnected() {
		if (Auth.getGoogleUser() != null) {
			renderArgs.put("user", Auth.getGoogleEmail());
		}
	}

	public static void index() {
		render();
	}

	public static void about() {
		render();
	}

	public static void contact() {
		render();
	}
}
package managers.mobile;

import interfaces.*;

import managers.ManagerAccess;
import models.Bid;
import models.JobCategory;
import models.Location;
import models.Status;
import models.User;
import models.MicroJob;
import play.mvc.Controller;
import java.util.*;

import siena.Model;
import play.*;
import play.modules.gae.GAE;
import play.mvc.*;
import utils.Auth;
import java.util.ArrayList;

import java.text.DateFormat;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.*;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

public class Mobile extends Controller {

	// Logger for AppEngine
	protected static final Logger logger = Logger.getLogger("Mobile");
	
	//GSON instance to be used for deserializing parameters
	protected static Gson gson = new Gson();

	public static void login() {
		// First create a user with a email, password, and UUID mobileToken.
		// Then to login use the URL
		// http://localhost:9000/Mobile/Login?email=someEmail&passwd=somePassword&mobileToken=somemobileToken

		if (params.get("email") == null || params.get("email") == "") {
			renderJSON("Login failed [No email provided]");
		} else if (params.get("passwd") == null || params.get("passwd") == "") {
			renderJSON("Login failed [No password provided]");
		} else if (params.get("mobileToken") == null
				|| params.get("mobileToken") == "") {
			renderJSON("Login failed [No mobileToken provided]");
		} else {
			User user = Model.all(User.class)
					.filter("email", params.get("email"))
					.filter("passwd", params.get("passwd")).get();
			if (user == null) {
				renderJSON("Login failed, user [" + params.get("email")
						+ "] not found.");
			} else {
				if (params.get("mobileToken") == user.mobileToken) {
					renderJSON(user.authToken);
				} else {
					renderJSON("Login failed [" + params.get("email")
							+ "] incorrect mobileToken.");
				}
			}
		}
	}

	public static void logout(String mobileToken) {
		// To logout use the URL http://localhost:9000/Mobile/Logout/{mobileToken}
		User user = Model.all(User.class).filter("mobileToken", mobileToken).get();
		if (user != null) {
			renderJSON("Logout successful for user [" + user.email + "]");
		} else {
			renderJSON("Logout unsuccessful, check mobileToken");
		}
	}

	// To register use the URL
	// http://bogus.appspot.com/Mobile/register?regID={Device Token}
	public static void register() {
		String regID = params.get("regID");
		String info = "";
		info += "This is your Device ID: " + regID + "\n";
		User temp = new User();
		temp.mobileToken = regID;
		temp.insert();
		temp.save();
		info += "adding the regID as a micro job";
		logger.info("Registering device: " + regID);
		renderJSON(info);
	}

	// To register use the URL
	// http://bogus.appspot.com/Mobile/send?message={message that is sent to devices}
	@SuppressWarnings("unused")
	public static void send() {
		String info = params.get("message");
		ArrayList<User> users = new ArrayList<User>();

		Iterable<User> Iusers = Model.all(User.class).iter();
		String regId;
		for (User user : Iusers) {
			if (user.mobileToken != null) {
				regId = user.mobileToken;
				Sender sender = new Sender(
						"AIzaSyDhDLsxYCyWuo9vvhiQCGiY6wpARtosgCU");
				Message message = new Message.Builder().collapseKey("1")
						.timeToLive(3).delayWhileIdle(true)
						.addData("message", info).build();
				Result result;

				try {
					result = sender.sendNoRetry(message, regId);
				} catch (IOException e) {
					renderJSON("IO Exception when posting message");
					return;
				}
				if (result.getMessageId() != null) {
					logger.info("Succesfully sent message to device " + regId);
					String canonicalRegId = result.getCanonicalRegistrationId();
					if (canonicalRegId != null) {
						// same device has more than on registration id: update
						// it
						logger.finest("canonicalRegId " + canonicalRegId);
					}
				} else {
					String error = result.getErrorCodeName();
					if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
						// application has been removed from device - unregister
						// it

					} else {
						logger.severe("Error sending message to device "
								+ regId + ": " + error);
					}

				}
				if (result == null) 
				{
					return;
				}
			}
		}
		renderJSON("Sent a message to all devices");
	}
}

package utils;

import managers.ManagerAccess;
import play.modules.gae.*;
import siena.Filter;
import siena.Query;

import com.google.appengine.api.users.*;

public class Auth {
	
	public static User currentUser;
	
    public static boolean isLoggedInGoogle() {
        return GAE.isLoggedIn();
    }
    
    public static String getGoogleEmail() {
        return GAE.getUser().getEmail();
    }
    
    public static User getGoogleUser() {
        return GAE.getUser();
    }
 
    public static void loginGoogle(String action) {
        GAE.login(action);
    }
 
    public static void logoutGoogle(String action) {
        GAE.logout(action);
    }
    
    public static String getGoogleUsername() {
    	return GAE.getUserService().getCurrentUser().getNickname();
    }
    
    public static String getUserID()
    {
    	return models.User.findByEmail(Auth.getGoogleEmail()).id;
    }
}

package controllers;

import java.util.Date;
import java.util.UUID;

import play.mvc.Before;
import utils.Auth;
import utils.BCrypt;
import models.*;
import play.libs.Crypto;
import play.libs.Crypto.HashType;

public class Users extends controllers.CRUD {

	@Before
    static void checkIsLoggedIn() {
        if(Auth.getGoogleUser() != null) {
            renderArgs.put("user", Auth.getGoogleEmail());
        }
    }
	
	public static void login() {
	    render();
	}
	
	public static void validateLogin() {
		if(params.get("UserName").toLowerCase().equals("admin")){
			Administrator.index();
		}
		else {
			MicroEmployer.index();
		}
	}	
	
	public static void register() {
	    render();
	}
	
	public static void createUser() {
		User user = new User();
		
		//Google Information
        user.email = params.get("email");
        
        //Auto Generated Values
		user.creationDate = new Date();
		user.authToken = UUID.randomUUID().toString();
		user.mobileToken = UUID.randomUUID().toString();
		
		//Form Values
		user.userName = params.get("userName");
		user.firstName = params.get("firstName");
		user.lastName = params.get("lastName");
		user.mobilePhoneNumber = params.get("mobilePhoneNumber");
		
		//Password Validation
		if(params.get("passwd").equals(params.get("passwdConfirm")))
		{
			user.passwd = BCrypt.hashpw(Crypto.passwordHash(params.get("passwd"), HashType.SHA512), BCrypt.gensalt());
		}
		else
		{
			Users.register();
		}
		
        user.insert();
        user.save();
        
        MicroEmployer.index();
	}
	
	public static void logout() {
		if(Auth.isLoggedInGoogle())
		{
			Users.logoutGoogleUser();
		}
		Application.index();
	}
	
	public static void settings() {
		User currentUser;
		
		if(Auth.isLoggedInGoogle())
		{
			currentUser = User.findByEmail(Auth.getGoogleEmail());
			boolean production = true;
			if(request.host == "localhost")
			{
				production = false;
			}
			render(currentUser, production);
		}
		else
		{
			render();
		} 
	}	
	
	/*Google Users*/
	
	public static void loginGoogleUser() {
		Auth.loginGoogle("Users.registerGoogleUser");
	}
	
	public static void logoutGoogleUser() {
		Auth.logoutGoogle("Application.index");
	}
	
	public static void registerGoogleUser() {
		if(Auth.isLoggedInGoogle()) 
		{
            User user = User.findByEmail(Auth.getGoogleEmail());
            if(user == null) {
            	User newUser = new User();
            	newUser.email = Auth.getGoogleEmail();
            	newUser.userName = Auth.getGoogleUsername();
                render(newUser);				
            }
            else {
            	MicroEmployer.index();
            }
        }
	}
	
	public static void createGoogleUser() {
		User user = new User();
		
		//Google Information
        user.email = Auth.getGoogleEmail();
        
        //Auto Generated Values
		user.creationDate = new Date();
		user.authToken = UUID.randomUUID().toString();
		//user.mobileToken = UUID.randomUUID().toString();
		
		//Form Values
		user.userName = params.get("userName");
		user.firstName = params.get("firstName");
		user.lastName = params.get("lastName");
		user.mobilePhoneNumber = params.get("mobilePhoneNumber");
		
		//Password Validation
		if(params.get("passwd").equals(params.get("passwdConfirm")))
		{
			user.passwd = BCrypt.hashpw(Crypto.passwordHash(params.get("passwd"), HashType.SHA512), BCrypt.gensalt());
		}
		else
		{
			Users.registerGoogleUser();
		}
		
        user.insert();
        user.save();
        
        MicroEmployer.index();
	}
}
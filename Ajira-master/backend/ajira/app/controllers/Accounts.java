package controllers;

import java.util.Date;
import java.util.UUID;

import play.mvc.Before;
import utils.Auth;
import utils.BCrypt;
import models.*;
import play.libs.Crypto;
import play.libs.Crypto.HashType;

public class Accounts extends controllers.CRUD {

	@Before
    static void checkIsLoggedIn() {
        if(Auth.getGoogleUser() != null) {
            renderArgs.put("user", Auth.getGoogleEmail());
        }
    }
}
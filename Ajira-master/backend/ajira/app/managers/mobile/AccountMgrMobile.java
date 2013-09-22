package managers.mobile;

import java.util.List;

import interfaces.AccountManager;
import models.Account;
import models.Role;
import models.User;

import play.mvc.Controller;

public class AccountMgrMobile /*implements AccountManager*/ extends Controller {

	public boolean CreateAccount(Account arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Account> GetAccounts(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Account GetAccount(String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Account UpdateAccount(String accountID, Account model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean DeleteAccount(String accountID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean CreateUser(String accountID, User model) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public User GetUser(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User UpdateUser(String UserID, User model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean DeleteUser(String UserID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<User> GetUsers(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> GetUsers(String accountID, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> GetUsersByType(Role role, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> GetUsersByType(String accountID, Role role, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean AddFavoriteMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean AddFavoriteMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean DeleteFavortieMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean DeleteFavortieMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<User> GetFavoriteMicroEmployers(String mircoWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> GetFavoriteMicroWorkers(String mircoEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean AddBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean AddBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean DeleteBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean DeleteBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<User> GetBlacklistMicroEmployers(String mircoWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> GetBlacklistMicroWorkers(String mircoEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean SetAvailablityStatus(String userID, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

}

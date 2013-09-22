package managers;

import java.util.List;

import interfaces.AccountManager;
import models.Account;
import models.Role;
import models.User;

public class AccountMgr implements AccountManager{

	

	@Override
	public boolean CreateAccount(Account arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public List<Account> GetAccounts(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Account GetAccount(String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account UpdateAccount(String accountID, Account model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteAccount(String accountID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CreateUser(String accountID, User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User GetUser(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User UpdateUser(String UserID, User model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteUser(String UserID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> GetUsers(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetUsers(String accountID, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetUsersByType(Role role, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetUsersByType(String accountID, Role role, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AddFavoriteMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean AddFavoriteMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteFavortieMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteFavortieMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> GetFavoriteMicroEmployers(String mircoWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetFavoriteMicroWorkers(String mircoEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AddBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean AddBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> GetBlacklistMicroEmployers(String mircoWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetBlacklistMicroWorkers(String mircoEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean SetAvailablityStatus(String userID, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}

}

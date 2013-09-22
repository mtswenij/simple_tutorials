package interfaces;

import java.util.*;

import models.Account;
import models.Role;
import models.User;

public interface AccountManager {
	/* CRUD ACCOUNT */

	// Create a new account
	public boolean CreateAccount(Account model);

	// Retrieve an account using an account ID
	public Account GetAccount(String accountID);

	// Update an existing account using an account ID associated with the target
	// account
	public Account UpdateAccount(String accountID, Account model);

	// Delete an account
	public boolean DeleteAccount(String accountID);

	// Retrieve all accounts
	public List<Account> GetAccounts(int limit);

	/* CRUD User */

	// Create a new account
	public boolean CreateUser(String accountID, User model);

	// Retrieve an User using an User ID
	public User GetUser(String UserID);

	// Update an existing User using an User ID associated with the target User
	public User UpdateUser(String UserID, User model);

	// Delete an User
	public boolean DeleteUser(String UserID);

	// Retrieve all Users up to limit
	public List<User> GetUsers(int limit);

	// Retrieve all Users within an Account up to limit
	public List<User> GetUsers(String accountID, int limit);

	// Retrieve all users by a certain role type
	public List<User> GetUsersByType(Role role, int limit);

	// Retrieve all users by a certain role type
	public List<User> GetUsersByType(String accountID, Role role, int limit);

	/* CRUD FAVORITES */

	// Favorite a MicroEmployer
	public boolean AddFavoriteMicroEmployer(String mircoWorkerID,
			String microEmployerID);

	// Favorite a MicroWorker
	public boolean AddFavoriteMicroWorker(String mircoWorkerID,
			String microEmployerID);

	// Delete a favorite MicroEmployer
	public boolean DeleteFavortieMicroEmployer(String mircoWorkerID,
			String microEmployerID);

	// Delete a favorite MicroWorker
	public boolean DeleteFavortieMicroWorker(String mircoWorkerID,
			String microEmployerID);

	// Get favorite MicroEmployers
	public List<User> GetFavoriteMicroEmployers(String mircoWorkerID);

	// Get favorite MicroWorkers
	public List<User> GetFavoriteMicroWorkers(String mircoEmployerID);

	/* CRUD BLACKLIST */

	// Blacklist a MicroEmployer
	public boolean AddBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID);

	// Blacklist a MicroMicroWorker
	public boolean AddBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID);

	// Remove a MicroEmployer from a Blacklist
	public boolean DeleteBlacklistMicroEmployer(String mircoWorkerID,
			String microEmployerID);

	// Remove a MicroWorker from a Blacklist
	public boolean DeleteBlacklistMicroWorker(String mircoWorkerID,
			String microEmployerID);

	// Get Blacklist
	public List<User> GetBlacklistMicroEmployers(String mircoWorkerID);

	// Get Blacklist
	public List<User> GetBlacklistMicroWorkers(String mircoEmployerID);

	/* AVAILABILITY */

	// Set Availability for MicroWorkers
	public boolean SetAvailablityStatus(String userID, boolean status);
}

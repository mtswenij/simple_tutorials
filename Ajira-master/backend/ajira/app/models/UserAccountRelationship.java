package models;

import siena.Generator;
import siena.Id;
import siena.Model;

public class UserAccountRelationship extends Model{
	
	@Id(Generator.UUID)
	public String id;
	
	//Account ID
	public String accountID;
	
	//User associated with the account
	public String userID;
	
	//Admin or Staff(Collaborative)
	public int userRole;
	
	//MicroWorker or MicroEmployer
	public int userPosition;
}
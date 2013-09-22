package models.test;
import java.util.List;

import org.junit.*;
import play.test.*;
import siena.Model;
import utils.DataGeneratorBootStrap;
import models.*;

public class BasicTest extends UnitTest {
	public DataGeneratorBootStrap dg;
	
	public BasicTest() {
		dg = new DataGeneratorBootStrap();
	}
	
    @Test
    public void createCategories() {
   	
		dg.genAllCategories();
        // Retrieve the category with tab KFC
		Category cat = Category.all().filter("name", "KFC").get();
        
        // Test 
        assertNotNull(cat);
        assertEquals("KFC", cat.name);
    }
    
    @Test
    public void createUser() {

		dg.genRandomUsers(20);
        // Retrieve the all users
        List<User> myUsers = User.all().fetch();
        
        // Tests
        assertEquals(1, myUsers.size());
        User firstPost = myUsers.get(0);
        assertNotNull(firstPost);
        
        assertNotNull(firstPost.email);
        assertNotNull(firstPost.mobilePhoneNumber);
        assertNotNull(firstPost.acct);
        assertNotNull(firstPost.locations);
        assertNotNull(firstPost.userProfile);
        assertNotNull(firstPost.lastName);
    }
 
    @Test
    public void createAccounts() {

    	dg.genRandomAccounts(10);

        Account a = Account.all(Account.class).filter("userName", "user2").get();
        
        // Test 
        assertNotNull(a);
        
        // Retrieve the all account
        List<Account> myAccts = Account.all(Account.class).fetch();
        
        // Tests
        assertEquals(10, myAccts.size());
        Account firstPost = myAccts.get(0);
        assertNotNull(firstPost);

        assertNotNull(firstPost.email);
        assertNotNull(firstPost.phoneNumber);
        assertNotNull(firstPost.billing);
        assertNotNull(firstPost.creationDate);
    }
    
    @Test
    public void createMicroJobs() {
        
    	dg.genRandomMicroJobs(20);
    	
        // Retrieve the all account
        List<MicroJob> myJobs = MicroJob.all(MicroJob.class).fetch();

        // Tests if 20 microjobs
        assertEquals(20, myJobs.size());
        MicroJob firstPost = myJobs.get(0);
        assertNotNull(firstPost);

        assertNotNull(firstPost.description);
        assertNotNull(firstPost.deliveryDestination);
        assertNotNull(firstPost.microEmployer);
        assertNotNull(firstPost.quality);
        assertNotNull(firstPost.totalRadius);
        
        MicroJob mj = myJobs.get(0);
        List<Bid> mbids = mj.GetBidPricesAssendingOrder();
        assertEquals(1, mbids.size());
        assertNotNull(mbids.get(0).bidPostTime);
        assertNotNull(mbids.get(0).microWk);
        assertEquals(mbids.get(0).mJob.id, mj.id);
    }
    
    @Test
    public void createAll() {
    	createCategories();
    	createAccounts();
    	createUser();
    	createMicroJobs();
    }
}
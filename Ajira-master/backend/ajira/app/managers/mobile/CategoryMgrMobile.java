package managers.mobile;


import java.util.List;

import siena.Model;
import utils.Auth;

import play.mvc.Controller;

import interfaces.CategoryManager;
import models.JobCategory;
import models.MicroJob;
import models.Category;
import models.User;
import models.UserCategory;

public class CategoryMgrMobile /*implements CategoryManager*/ extends Controller {
	
	public boolean CreateUserCategory(UserCategory model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public UserCategory GetUserCategory(String categoryID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean UpdateUserCategory(String categoryID, UserCategory model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean DeleteUserCategory(String categoryID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Category> GetCategories() {
		List<Category> categories = Model.all(Category.class).fetch();
		return categories;
	}
	
	
	public Category GetCategoryByName(String n) {
		Category categories = Model.all(Category.class).filter("name", n).get();
		return categories;
	}

	
	public boolean CreateJobCategory(JobCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public JobCategory GetJobCategory(String categoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean UpdateJobCategory(String categoryID, JobCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean DeleteJobCategory(String categoryID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<JobCategory> GetJobCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<UserCategory> GetUserCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}

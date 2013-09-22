package managers;

import java.util.List;

import siena.Model;
import utils.Auth;

import interfaces.CategoryManager;
import models.JobCategory;
import models.MicroJob;
import models.Category;
import models.User;
import models.UserCategory;

public class CategoryMgr implements CategoryManager {

	

	@Override
	public boolean CreateUserCategory(UserCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserCategory GetUserCategory(String categoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean UpdateUserCategory(String categoryID, UserCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteUserCategory(String categoryID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> GetCategories() {
		List<Category> categories = Model.all(Category.class).fetch();
		return categories;
	}
	
	@Override
	public Category GetCategoryByName(String n) {
		Category categories = Model.all(Category.class).filter("name", n).get();
		return categories;
	}

	@Override
	public boolean CreateJobCategory(JobCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JobCategory GetJobCategory(String categoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean UpdateJobCategory(String categoryID, JobCategory model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteJobCategory(String categoryID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<JobCategory> GetJobCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCategory> GetUserCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}

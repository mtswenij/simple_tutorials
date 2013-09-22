package interfaces;

import java.util.*;

import models.Category;
import models.JobCategory;
import models.UserCategory;

public interface CategoryManager {
	/* CRUD CATEGORY */

	public boolean CreateUserCategory(UserCategory model);

	public UserCategory GetUserCategory(String categoryID);

	public boolean UpdateUserCategory(String categoryID, UserCategory model);

	public boolean DeleteUserCategory(String categoryID);

	public List<Category> GetCategories();
	
	public Category GetCategoryByName(String n);
	
	public List<UserCategory> GetUserCategories();

	public boolean CreateJobCategory(JobCategory model);

	public JobCategory GetJobCategory(String categoryID);

	public boolean UpdateJobCategory(String categoryID, JobCategory model);

	public boolean DeleteJobCategory(String categoryID);

	public List<JobCategory> GetJobCategories();
}

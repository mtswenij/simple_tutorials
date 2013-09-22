package models;

import java.util.List;

import play.data.validation.Required;
import siena.*;

public class JobCategory extends Model {
	@Id(Generator.UUID)
	public String id;

	@Index("jobcategory_index")
	public MicroJob mjob;

	@Index("category_index")
	@Required
	public Category cat;

	public JobCategory(MicroJob u, Category c) {
		mjob = u;
		cat = c;
	}

	public JobCategory() {
		// TODO Auto-generated constructor stub
	}

	public static Query<JobCategory> all() {
		return Model.all(JobCategory.class);
	}

	public static JobCategory findByMicroJobAndCategory(String c,
			MicroJob mj) {
		return all().filter("mjob", mj).filter("cat.name", c).get();
	}

	public static JobCategory findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<JobCategory> findByMicroJob(MicroJob mj) {
		return all().filter("mjob", mj).fetch();
	}

	public static List<JobCategory> findByCategory(String c) {
		return all().filter("cat.name", c).fetch();
	}

	public String toString() {
		return "Category: " + cat + "MicroJobID: " + mjob.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		result = prime * result + ((mjob == null) ? 0 : mjob.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobCategory other = (JobCategory) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (mjob == null) {
			if (other.mjob != null)
				return false;
		} else if (!mjob.equals(other.mjob))
			return false;
		
		if (cat == null) {
			if (other.cat != null)
				return false;
		} else if (!cat.equals(other.cat))
			return false;
		return true;
	}
}

package models;

import java.util.List;

import siena.*;

public class Evaluation extends Model {
	@Id(Generator.UUID)
	public String id;

	// eval belongs to one user
	@Index("memployer_index")
	public User microEmp;

	// eval belongs to one user
	@Index("mworker_index")
	public User microWorker;

	@Index("evalType_index")
	public int evalType;

	public int attitude;

	public int productCondition;

	public int timeliness;

	public int serviceFulfillment;

	public int additionalCompensation;

	public String toString() {
		return evalType + " Eval: Attitude[" + attitude + "] Condition["
				+ productCondition + " Timeliness[" + timeliness
				+ " ServiceFullfillment[" + serviceFulfillment
				+ " AdditionalCompensation[" + additionalCompensation + "]";
	}

	static Query<Evaluation> all() {
		return Model.all(Evaluation.class);
	}

	public static Evaluation findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<Evaluation> findByMicroEmployer(User me) {
		return all().filter("id", me.id).fetch();
	}

	public static List<Evaluation> findByMicroWorker(User mw) {
		return all().filter("id", mw.id).fetch();
	}

	public static List<Evaluation> findByEvaluationType(int e) {
		return all().filter("evalType", e).fetch();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((microEmp == null) ? 0 : microEmp.hashCode());
		result = prime * result
				+ ((microWorker == null) ? 0 : microWorker.hashCode());
		result = prime * result + evalType;
		result = prime * result + attitude;
		result = prime * result + productCondition;
		result = prime * result + timeliness;
		result = prime * result + serviceFulfillment;
		result = prime * result + additionalCompensation;

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
		Evaluation other = (Evaluation) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (attitude != other.attitude)
			return false;

		if (microEmp == null) {
			if (other.microEmp != null)
				return false;
		} else if (!microEmp.equals(other.microEmp))
			return false;

		if (microWorker == null) {
			if (other.microWorker != null)
				return false;
		} else if (!microWorker.equals(other.microWorker))
			return false;
		if (evalType != other.evalType) 
			return false;
		
		if (productCondition != other.productCondition)
			return false;

		if (timeliness != other.timeliness)
			return false;

		if (serviceFulfillment != other.serviceFulfillment)
			return false;

		if (additionalCompensation != other.additionalCompensation)
			return false;

		return true;
	}
}

package models;

import java.util.List;

import siena.*;
import siena.embed.Embedded;
import siena.embed.Embedded.Mode;

public class EvaluationQuestion extends Model {
	@Id(Generator.UUID)
	public String id;

	@Max(40)
	public String question;

	@Index("questionType_index")
	@Embedded(mode = Mode.NATIVE)
	public EvaluationType evalType;

	public String toString() {
		return question;
	}

	static Query<EvaluationQuestion> all() {
		return Model.all(EvaluationQuestion.class);
	}

	public static EvaluationQuestion findById(String id) {
		return all().filter("id", id).get();
	}

	public static List<EvaluationQuestion> findByEvaluationType(int type) {
		return all().filter("evalType.value", type).fetch();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
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
		EvaluationQuestion other = (EvaluationQuestion) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
}
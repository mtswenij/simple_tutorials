package models;

import java.io.Serializable;

import siena.*;
import siena.embed.EmbeddedMap;

@EmbeddedMap
public class QualityMetrics implements Serializable {
	// Preferred time it should take to deliver the service
	public double deliveryTime;

	// A static price or range of prices preferred
	// Service offing has a reasonable price
	// 5 stars is awaly's good price
	// 1 star is terrible price

	public double price;

	// Delivery Condition
	public double deliveryCondition;

	// Attitude of the person
	public double attitude;

	// Additional compensation provided by the MicroEmployer
	public double additionalCompensation;

	// The amount of time the MicroEmployer gives to complete the job
	public double timeAllowance;

	// the closer the person is to the job the higher their preference to get
	// notified about the job.
	public double location;

	public String toString() {
		return "Quality Metrics[DeliveryTime: " + deliveryTime + " Price: "
				+ price + " DeliveryCondition: " + deliveryCondition
				+ " Attitude: " + attitude + " Additional Compensation: "
				+ additionalCompensation + " Time Allowance: " + timeAllowance
				+ " Location: " + location + "]";
	}

	public void setLocationQuality(double location) {
		this.location = location;
	}

	public double getLoctation() {
		return this.location;
	}

	public double[] GetMicroWorkerQualityVector() {
		return new double[] { this.deliveryTime, this.deliveryCondition,
				this.attitude, this.location };
	}

	public double[] GetMicroEmployerQualityVector() {
		return new double[] { this.additionalCompensation, this.price,
				this.attitude, this.timeAllowance };
	}

	// Calculates the quality score.
	public double CalculateQualityScore(int roleType /*
													 * 0 if its a MicroWorker 1
													 * if its a MicroEmployer
													 */, QualityMetrics x) {
		// If MicroEmployer
		if (roleType == 0) {
			return (100 * CosSim(x.GetMicroEmployerQualityVector(),
					this.GetMicroWorkerQualityVector()));
		}
		// If MicroWorker
		else if (roleType == 1) {
			return (100 * CosSim(x.GetMicroWorkerQualityVector(),
					this.GetMicroEmployerQualityVector()));
		} else {
			return -1;
		}
	}

	// Calculates the cosine similarity
	public double CosSim(double[] x, double[] y) {
		double similarity = 0;
		double xlen = 0;
		double ylen = 0;

		for (int i = 0; i < x.length; i++) {
			similarity += x[i] * y[i];
		}

		for (int j = 0; j < x.length; j++) {
			xlen += x[j] * x[j];
		}
		xlen = Math.sqrt(xlen);

		for (int k = 0; k < y.length; k++) {
			ylen += y[k] * y[k];
		}
		ylen = Math.sqrt(ylen);

		similarity = similarity / (xlen * ylen);
		return similarity;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) deliveryCondition;
		result = prime * result + (int) attitude;
		result = prime * result + (int) additionalCompensation;
		result = prime * result + (int) timeAllowance;
		result = prime * result + (int) location;
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
		QualityMetrics other = (QualityMetrics) obj;

		if (deliveryCondition != other.deliveryCondition)
			return false;

		if (attitude != other.attitude)
			return false;

		if (additionalCompensation != other.additionalCompensation)
			return false;

		if (timeAllowance != other.timeAllowance)
			return false;

		if (location != other.location)
			return false;
		return true;
	}
}

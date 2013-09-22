package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import siena.*;
import siena.embed.Embedded;
import siena.embed.Embedded.Mode;
import siena.embed.EmbeddedMap;

@EmbeddedMap
public class Profile implements Serializable {
	// Metrics that describe their preferred server type.

	@Embedded(mode = Mode.NATIVE)
	public QualityMetrics userQuality;

	// Notification preferences
	public boolean useEmail;
	public boolean useSMS;
	public boolean useFacebook;
	public boolean usePhone;

	public String toString() {
		return "useEmail: " + useEmail + " useSMS: " + useSMS
				+ " useFacebook: " + useFacebook + " usePhone: " + usePhone;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userQuality == null) ? 0 : userQuality.hashCode());
		result = prime * result + ((useEmail) ? 1 : 0);
		result = prime * result + ((useSMS) ? 1 : 0);
		result = prime * result + ((useFacebook) ? 1 : 0);
		result = prime * result + ((usePhone) ? 1 : 0);
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
		Profile other = (Profile) obj;

		if (userQuality == null) {
			if (other.userQuality != null)
				return false;
		} else if (!userQuality.equals(other.userQuality))
			return false;

		if (useEmail != other.useEmail)
			return false;

		if (useSMS != other.useSMS)
			return false;

		if (useFacebook != other.useFacebook)
			return false;

		if (usePhone != other.usePhone)
			return false;

		return true;
	}
}

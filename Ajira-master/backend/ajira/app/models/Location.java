package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import siena.*;
import siena.embed.Embedded;
import siena.embed.EmbeddedMap;
import siena.embed.Embedded.Mode;

@EmbeddedMap
public class Location implements Serializable {
	// The user defined name of the location
	public String title;

	// Address of the location
	@Embedded(mode = Mode.NATIVE)
	public Address address;

	// Geographic coordinates of this location (Not Required)
	public double lon, lat;

	// earthRadius in miles
	private final double earthRadius = 3958.75;

	// meters in a mile
	private final int meterConversion = 1609;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) lon;
		result = prime * result + (int) lat;
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
		Location other = (Location) obj;

		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;

		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;

		if (lon != other.lon)
			return false;

		if (lat != other.lat)
			return false;

		return true;
	}

	public Location(float lat, float lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public String toString() {
		return "Location: (Lon:" + lon + " Lat:" + lat + ")  OR Address: "
				+ address;
	}

	public double getQuality(double defRad, double totRad, Location loc) {

		double dist = distanceTo(this.lat, this.lon, loc.lat, loc.lon);
		if (dist < defRad) {
			return 1.0;
		} else if ((dist > defRad && (dist <= totRad))) {
			return Math.exp((dist * -1) / (((totRad - defRad) / 2) + 1));
		} else if (dist > totRad) {
			return 0.0;
		} else {
			return -1.0;
		}
	}

	// return distance in Meters
	public double distanceTo(double latA, double lonA, double latB, double lonB) {

		double dLat = Math.toRadians(latB - latA);
		double dLng = Math.toRadians(lonB - lonA);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(latA))
				* Math.cos(Math.toRadians(latB)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return new Float(dist * meterConversion).floatValue();
	}
}

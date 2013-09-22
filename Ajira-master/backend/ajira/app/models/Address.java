package models;

import java.io.Serializable;

import siena.embed.EmbeddedMap;

@EmbeddedMap
public class Address implements Serializable {
	public String street;
	public String city;
	public String state;
	public String zip;
	public String country;

	public Address(String street, String city, String state, String zip,
			String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String toString() {
		return street + " " + city + " " + state + " " + zip + "  " + country;
	}

	public Address() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		Address other = (Address) obj;

		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;

		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;

		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;

		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;

		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}
}

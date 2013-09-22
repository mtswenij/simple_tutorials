package com.icat.ProjectME.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;
public class User {
	@SerializedName("id")
	public Long id;
	@SerializedName("firstName")
	public String firstName;
	@SerializedName("lastName")
	public String lastName;
	@SerializedName("mobileToken")
	public String mobileToken = "";
	public User(String firstName,String lastName,Long id)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
}

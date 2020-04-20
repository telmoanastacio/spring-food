package com.tsilva.springFood.user;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public class CrmUser
{
	private String userName;
	private String password;
	private String matchingPassword;
	private String firstName;
	private String lastName;
	private String email;

	public CrmUser() {}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}

	public String getMatchingPassword()
	{
		return matchingPassword;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setMatchingPassword(String matchingPassword)
	{
		this.matchingPassword = matchingPassword;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}

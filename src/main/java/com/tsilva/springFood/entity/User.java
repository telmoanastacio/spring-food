package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Entity
@Table(name = "user")
public class User implements Serializable
{
	private static final long serialVersionUID = 42865132230003L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<UsersRoles> usersRoles = new ArrayList<>();

	public User() {}

	public User(String userName, String password, String firstName, String lastName, String email)
	{
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String userName, String password, String firstName, String lastName, String email,
			Collection<UsersRoles> usersRoles)
	{
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.usersRoles = usersRoles;
	}

	public Long getId()
	{
		return id;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
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

	public Collection<UsersRoles> getUsersRoles()
	{
		return usersRoles;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
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

	public void setUsersRoles(Collection<UsersRoles> usersRoles)
	{
		this.usersRoles = usersRoles;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(userName, user.userName) &&
				Objects.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(email, user.email);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, userName, password, firstName, lastName, email);
	}

	@Override
	public String toString()
	{
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", userRoleRole=" + usersRoles +
				'}';
	}
}

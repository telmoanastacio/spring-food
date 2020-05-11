package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Entity
@Table(name = "role")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "role",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<UserRole> userRoleUser = new ArrayList<>();

	public Role() {}

	public Role(String name)
	{
		this.name = name;
	}

	public Role(String name, Collection<UserRole> userRoles)
	{
		this.name = name;
		this.userRoleUser = userRoles;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Collection<UserRole> getUserRoleUser()
	{
		return userRoleUser;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setUserRoleUser(Collection<UserRole> userRoleUser)
	{
		this.userRoleUser = userRoleUser;
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
		Role role = (Role) o;
		return Objects.equals(id, role.id) &&
				Objects.equals(name, role.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name);
	}

	@Override
	public String toString()
	{
		return "Role{" +
				"id=" + id +
				", name='" + name + '\'' +
				", userRoleUser=" + userRoleUser +
				'}';
	}
}

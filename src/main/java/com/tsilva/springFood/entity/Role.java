package com.tsilva.springFood.entity;

import javax.persistence.*;

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

	public Role() {}

	public Role(String name)
	{
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}

package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "cuisine")
public class Cuisine implements Serializable
{
	private static final long serialVersionUID = 42865132230011L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "cuisine",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailCuisines> recipeDetailCuisines = new ArrayList<>();

	public Cuisine() {}

	public Cuisine(String name)
	{
		this.name = name;
	}

	public Cuisine(String name, Collection<RecipeDetailCuisines> recipeDetailCuisines)
	{
		this.name = name;
		this.recipeDetailCuisines = recipeDetailCuisines;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Collection<RecipeDetailCuisines> getRecipeDetailCuisines()
	{
		return recipeDetailCuisines;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setRecipeDetailCuisines(Collection<RecipeDetailCuisines> recipeDetailCuisines)
	{
		this.recipeDetailCuisines = recipeDetailCuisines;
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
		Cuisine dishType = (Cuisine) o;
		return Objects.equals(id, dishType.id) &&
				Objects.equals(name, dishType.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name);
	}

	@Override
	public String toString()
	{
		return "Cuisine{" +
				"id=" + id +
				", name='" + name + '\'' +
				", recipeDetailCuisines=" + recipeDetailCuisines +
				'}';
	}
}

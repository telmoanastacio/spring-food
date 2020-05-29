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
@Table(name = "dish_type")
public class DishType implements Serializable
{
	private static final long serialVersionUID = 42865132230008L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "dish_type",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailDishTypes> recipeDetailDishTypes = new ArrayList<>();

	public DishType() {}

	public DishType(String name)
	{
		this.name = name;
	}

	public DishType(String name, Collection<RecipeDetailDishTypes> recipeDetailDishTypes)
	{
		this.name = name;
		this.recipeDetailDishTypes = recipeDetailDishTypes;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Collection<RecipeDetailDishTypes> getRecipeDetailDishTypes()
	{
		return recipeDetailDishTypes;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setRecipeDetailDishTypes(Collection<RecipeDetailDishTypes> recipeDetailDishTypes)
	{
		this.recipeDetailDishTypes = recipeDetailDishTypes;
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
		DishType dishType = (DishType) o;
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
		return "DishType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", recipeDetailDishTypes=" + recipeDetailDishTypes +
				'}';
	}
}

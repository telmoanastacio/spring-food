package com.tsilva.springFood.entity;

import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.ExtendedIngredient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable
{
	private static final long serialVersionUID = 42865132230014L;

	@Transient
	private ExtendedIngredient extendedIngredient = null;

	@Transient
	private com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.Ingredient saveIngredient = null;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "spoonacular_id")
	private Long spoonacularId;

	@Column(name = "aisle")
	private String aisle;

	@Column(name = "image")
	private String image;

	@Column(name = "consistency")
	private String consistency;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "original")
	private String original;

	@Column(name = "original_string")
	private String originalString;

	@Column(name = "original_name")
	private String originalName;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "unit")
	private String unit;

	@OneToMany(mappedBy = "ingredient",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailIngredients> recipeDetailIngredients = new ArrayList<>();

	@OneToOne(mappedBy = "ingredient",
			orphanRemoval = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Measure measure;

	@OneToMany(mappedBy = "ingredient",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<IngredientMetaInformations> ingredientMetaInformations = new ArrayList<>();

	@OneToMany(mappedBy = "ingredient",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<IngredientMetas> ingredientMetas = new ArrayList<>();

	public Ingredient() {}

	public Ingredient(Long spoonacularId,
			String aisle,
			String image,
			String consistency,
			String name,
			String original,
			String originalString,
			String originalName,
			Double amount,
			String unit,
			com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.Ingredient ingredient)
	{
		this.spoonacularId = spoonacularId;
		this.aisle = aisle;
		this.image = image;
		this.consistency = consistency;
		this.name = name;
		this.original = original;
		this.originalString = originalString;
		this.originalName = originalName;
		this.amount = amount;
		this.unit = unit;
		this.saveIngredient = ingredient;
	}

	public Ingredient(Long spoonacularId,
		  	String aisle,
			String image,
			String consistency,
			String name,
			String original,
			String originalString,
			String originalName,
			Double amount,
			String unit,
			ExtendedIngredient extendedIngredient)
	{
		this.spoonacularId = spoonacularId;
		this.aisle = aisle;
		this.image = image;
		this.consistency = consistency;
		this.name = name;
		this.original = original;
		this.originalString = originalString;
		this.originalName = originalName;
		this.amount = amount;
		this.unit = unit;
		this.extendedIngredient = extendedIngredient;
	}

	public Long getId()
	{
		return id;
	}

	public Long getSpoonacularId()
	{
		return spoonacularId;
	}

	public String getAisle()
	{
		return aisle;
	}

	public String getImage()
	{
		return image;
	}

	public String getConsistency()
	{
		return consistency;
	}

	public String getName()
	{
		return name;
	}

	public String getOriginal()
	{
		return original;
	}

	public String getOriginalString()
	{
		return originalString;
	}

	public String getOriginalName()
	{
		return originalName;
	}

	public Double getAmount()
	{
		return amount;
	}

	public String getUnit()
	{
		return unit;
	}

	public Collection<RecipeDetailIngredients> getRecipeDetailIngredients()
	{
		return recipeDetailIngredients;
	}

	public Measure getMeasure()
	{
		return measure;
	}

	public Collection<IngredientMetaInformations> getIngredientMetaInformations()
	{
		return ingredientMetaInformations;
	}

	public Collection<IngredientMetas> getIngredientMetas()
	{
		return ingredientMetas;
	}

	public ExtendedIngredient getExtendedIngredient()
	{
		return extendedIngredient;
	}

	public com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.Ingredient getSaveIngredient()
	{
		return saveIngredient;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setSpoonacularId(Long spoonacularId)
	{
		this.spoonacularId = spoonacularId;
	}

	public void setAisle(String aisle)
	{
		this.aisle = aisle;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public void setConsistency(String consistency)
	{
		this.consistency = consistency;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setOriginal(String original)
	{
		this.original = original;
	}

	public void setOriginalString(String originalString)
	{
		this.originalString = originalString;
	}

	public void setOriginalName(String originalName)
	{
		this.originalName = originalName;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public void setRecipeDetailIngredients(Collection<RecipeDetailIngredients> recipeDetailIngredients)
	{
		this.recipeDetailIngredients = recipeDetailIngredients;
	}

	public void setMeasure(Measure measure)
	{
		this.measure = measure;
	}

	public void setIngredientMetaInformations(Collection<IngredientMetaInformations> ingredientMetaInformations)
	{
		this.ingredientMetaInformations = ingredientMetaInformations;
	}

	public void setIngredientMetas(Collection<IngredientMetas> ingredientMetas)
	{
		this.ingredientMetas = ingredientMetas;
	}

	public void setExtendedIngredient(ExtendedIngredient extendedIngredient)
	{
		this.extendedIngredient = extendedIngredient;
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
		Ingredient that = (Ingredient) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(spoonacularId, that.spoonacularId) &&
				Objects.equals(aisle, that.aisle) &&
				Objects.equals(image, that.image) &&
				Objects.equals(consistency, that.consistency) &&
				Objects.equals(name, that.name) &&
				Objects.equals(original, that.original) &&
				Objects.equals(originalString, that.originalString) &&
				Objects.equals(originalName, that.originalName) &&
				Objects.equals(amount, that.amount) &&
				Objects.equals(unit, that.unit);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id,
				spoonacularId,
				aisle,
				image,
				consistency,
				name,
				original,
				originalString,
				originalName,
				amount,
				unit);
	}

	@Override
	public String toString()
	{
		return "Ingredient{" +
				"id=" + id +
				", spoonacularId='" + spoonacularId + '\'' +
				", aisle='" + aisle + '\'' +
				", image='" + image + '\'' +
				", consistency='" + consistency + '\'' +
				", name='" + name + '\'' +
				", original='" + original + '\'' +
				", originalString='" + originalString + '\'' +
				", originalName='" + originalName + '\'' +
				", amount=" + amount +
				", unit='" + unit + '\'' +
				", recipeDetailIngredients=" + recipeDetailIngredients +
				'}';
	}
}

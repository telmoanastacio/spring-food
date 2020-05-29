package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "ingredient_meta_informations")
public class IngredientMetaInformations implements Serializable
{
	private static final long serialVersionUID = 42865132230022L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "meta_information")
	private String metaInformation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	public IngredientMetaInformations() {}

	public IngredientMetaInformations(String metaInformation)
	{
		this.metaInformation = metaInformation;
	}

	public IngredientMetaInformations(String metaInformation, Ingredient ingredient)
	{
		this.metaInformation = metaInformation;
		this.ingredient = ingredient;
	}

	public Long getId()
	{
		return id;
	}

	public String getMetaInformation()
	{
		return metaInformation;
	}

	public Ingredient getIngredient()
	{
		return ingredient;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setMetaInformation(String metaInformation)
	{
		this.metaInformation = metaInformation;
	}

	public void setIngredient(Ingredient ingredient)
	{
		this.ingredient = ingredient;
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
		IngredientMetaInformations that = (IngredientMetaInformations) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(metaInformation, that.metaInformation);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, metaInformation);
	}

	@Override
	public String toString()
	{
		return "IngredientMetaInformations{" +
				"id=" + id +
				", metaInformation='" + metaInformation + '\'' +
				", ingredient=" + ingredient +
				'}';
	}
}

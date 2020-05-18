package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "ingredient_metas")
public class IngredientMetas implements Serializable
{
	private static final long serialVersionUID = 42865132230023L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "meta")
	private String meta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	public IngredientMetas() {}

	public IngredientMetas(String meta)
	{
		this.meta = meta;
	}

	public IngredientMetas(String meta, Ingredient ingredient)
	{
		this.meta = meta;
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
		IngredientMetas that = (IngredientMetas) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(meta, that.meta);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, meta);
	}

	@Override
	public String toString()
	{
		return "IngredientMetas{" +
				"id=" + id +
				", meta='" + meta + '\'' +
				", ingredient=" + ingredient +
				'}';
	}
}

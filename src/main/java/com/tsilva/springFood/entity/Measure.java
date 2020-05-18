package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "measure")
public class Measure implements Serializable
{
	private static final long serialVersionUID = 42865132230021L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "unit_short")
	private String unitShort;

	@Column(name = "unit_long")
	private String unitLong;

	@Column(name = "imp_unit_short")
	private String impUnitShort;

	@Column(name = "imp_unit_long")
	private String impUnitLong;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	public Measure() {}

	public Measure(String unitShort, String unitLong, String impUnitShort, String impUnitLong, Ingredient ingredient)
	{
		this.unitShort = unitShort;
		this.unitLong = unitLong;
		this.impUnitShort = impUnitShort;
		this.impUnitLong = impUnitLong;
		this.ingredient = ingredient;
	}

	public Long getId()
	{
		return id;
	}

	public String getUnitShort()
	{
		return unitShort;
	}

	public String getUnitLong()
	{
		return unitLong;
	}

	public String getImpUnitShort()
	{
		return impUnitShort;
	}

	public String getImpUnitLong()
	{
		return impUnitLong;
	}

	public Ingredient getIngredient()
	{
		return ingredient;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setUnitShort(String unitShort)
	{
		this.unitShort = unitShort;
	}

	public void setUnitLong(String unitLong)
	{
		this.unitLong = unitLong;
	}

	public void setImpUnitShort(String impUnitShort)
	{
		this.impUnitShort = impUnitShort;
	}

	public void setImpUnitLong(String impUnitLong)
	{
		this.impUnitLong = impUnitLong;
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
		Measure measure = (Measure) o;
		return Objects.equals(id, measure.id) &&
				Objects.equals(unitShort, measure.unitShort) &&
				Objects.equals(unitLong, measure.unitLong) &&
				Objects.equals(impUnitShort, measure.impUnitShort) &&
				Objects.equals(impUnitLong, measure.impUnitLong);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, unitShort, unitLong, impUnitShort, impUnitLong);
	}

	@Override
	public String toString()
	{
		return "Measure{" +
				"id=" + id +
				", unitShort='" + unitShort + '\'' +
				", unitLong='" + unitLong + '\'' +
				", impUnitShort='" + impUnitShort + '\'' +
				", impUnitLong='" + impUnitLong + '\'' +
				", ingredient=" + ingredient +
				'}';
	}
}

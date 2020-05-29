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
@Table(name = "step")
public class Step implements Serializable
{
	private static final long serialVersionUID = 42865132230015L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "number", nullable = false)
	private Long number;

	@Column(name = "step", nullable = false)
	private String step;

	@Column(name = "length_number")
	private Long lengthNumber;

	@Column(name = "length_unit")
	private String lengthUnit;

	@OneToMany(mappedBy = "step",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailSteps> recipeDetailSteps = new ArrayList<>();

	public Step() {}

	public Step(Long number, String step, Long lengthNumber, String lengthUnit)
	{
		this.number = number;
		this.step = step;
		this.lengthNumber = lengthNumber;
		this.lengthUnit = lengthUnit;
	}

	public Long getId()
	{
		return id;
	}

	public Long getNumber()
	{
		return number;
	}

	public String getStep()
	{
		return step;
	}

	public Long getLengthNumber()
	{
		return lengthNumber;
	}

	public String getLengthUnit()
	{
		return lengthUnit;
	}

	public Collection<RecipeDetailSteps> getRecipeDetailSteps()
	{
		return recipeDetailSteps;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setNumber(Long number)
	{
		this.number = number;
	}

	public void setStep(String step)
	{
		this.step = step;
	}

	public void setLengthNumber(Long lengthNumber)
	{
		this.lengthNumber = lengthNumber;
	}

	public void setLengthUnit(String lengthUnit)
	{
		this.lengthUnit = lengthUnit;
	}

	public void setRecipeDetailSteps(Collection<RecipeDetailSteps> recipeDetailSteps)
	{
		this.recipeDetailSteps = recipeDetailSteps;
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
		Step step1 = (Step) o;
		return Objects.equals(id, step1.id) &&
				Objects.equals(number, step1.number) &&
				Objects.equals(step, step1.step) &&
				Objects.equals(lengthNumber, step1.lengthNumber) &&
				Objects.equals(lengthUnit, step1.lengthUnit);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, number, step, lengthNumber, lengthUnit);
	}

	@Override
	public String toString()
	{
		return "Step{" +
				"id=" + id +
				", number=" + number +
				", step='" + step + '\'' +
				", lengthNumber=" + lengthNumber +
				", lengthUnit='" + lengthUnit + '\'' +
				", recipeDetailSteps=" + recipeDetailSteps +
				'}';
	}
}

package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_base")
public class RecipeBase implements Serializable
{
	private static final long serialVersionUID = 42865132230007L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "spoonacular_id", unique = true)
	private Long spoonacularId;

	@Column(name = "title")
	private String title;

	@Column(name = "ready_in_minutes")
	private Long readyInMinutes;

	@Column(name = "servings")
	private Long servings;

	@Column(name = "image")
	private String image;

	@Column(name = "update_time_stamp")
	private Long updateTimeStamp;

	@OneToOne(mappedBy = "recipeBase",
			orphanRemoval = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private RecipeDetail recipeDetail;

	public RecipeBase() {}

	public RecipeBase(
			Long spoonacularId,
			String title,
			Long readyInMinutes,
			Long servings,
			String image,
			Long updateTimeStamp)
	{
		this.spoonacularId = spoonacularId;
		this.title = title;
		this.readyInMinutes = readyInMinutes;
		this.servings = servings;
		this.image = image;
		this.updateTimeStamp = updateTimeStamp;
	}

	public Long getId()
	{
		return id;
	}

	public Long getSpoonacularId()
	{
		return spoonacularId;
	}

	public String getTitle()
	{
		return title;
	}

	public Long getReadyInMinutes()
	{
		return readyInMinutes;
	}

	public Long getServings()
	{
		return servings;
	}

	public String getImage()
	{
		return image;
	}

	public RecipeDetail getRecipeDetail()
	{
		return recipeDetail;
	}

	public Long getUpdateTimeStamp()
	{
		return updateTimeStamp;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setSpoonacularId(Long spoonacularId)
	{
		this.spoonacularId = spoonacularId;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setReadyInMinutes(Long readyInMinutes)
	{
		this.readyInMinutes = readyInMinutes;
	}

	public void setServings(Long servings)
	{
		this.servings = servings;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public void setRecipeDetail(RecipeDetail recipeDetail)
	{
		this.recipeDetail = recipeDetail;
	}

	public void setUpdateTimeStamp(Long updateTimeStamp)
	{
		this.updateTimeStamp = updateTimeStamp;
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
		RecipeBase that = (RecipeBase) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(spoonacularId, that.spoonacularId) &&
				Objects.equals(title, that.title) &&
				Objects.equals(readyInMinutes, that.readyInMinutes) &&
				Objects.equals(servings, that.servings) &&
				Objects.equals(image, that.image) &&
				Objects.equals(updateTimeStamp, that.updateTimeStamp);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, spoonacularId, title, readyInMinutes, servings, image, updateTimeStamp);
	}

	@Override
	public String toString()
	{
		return "RecipeBase{" +
				"id=" + id +
				", spoonacularId=" + spoonacularId +
				", title='" + title + '\'' +
				", readyInMinutes=" + readyInMinutes +
				", servings=" + servings +
				", image='" + image + '\'' +
				", updateTimeStamp=" + updateTimeStamp +
				", recipeDetail=" + recipeDetail +
				'}';
	}
}

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
@Table(name = "recipe_detail")
public class RecipeDetail implements Serializable
{
	private static final long serialVersionUID = 42865132230006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "vegetarian")
	private Boolean vegetarian;

	@Column(name = "vegan")
	private Boolean vegan;

	@Column(name = "gluten_free")
	private Boolean glutenFree;

	@Column(name = "dairy_free")
	private Boolean dairyFree;

	@Column(name = "very_healthy")
	private Boolean veryHealthy;

	@Column(name = "cheap")
	private Boolean cheap;

	@Column(name = "very_popular")
	private Boolean veryPopular;

	@Column(name = "sustainable")
	private Boolean sustainable;

	@Column(name = "low_fodmap")
	private Boolean lowFodmap;

	@Column(name = "weight_watcher_smart_points")
	private Long weightWatcherSmartPoints;

	@Column(name = "gaps")
	private String gaps;

	@Column(name = "preparation_in_minutes")
	private Long preparationInMinutes;

	@Column(name = "cooking_minutes")
	private Long cookingMinutes;

	@Column(name = "source_url")
	private String sourceUrl;

	@Column(name = "spoonacular_source_url")
	private String spoonacularSourceUrl;

	@Column(name = "aggregate_likes")
	private Long aggregateLikes;

	@Column(name = "spoonacular_score")
	private Double spoonacularScore;

	@Column(name = "health_score")
	private Double healthScore;

	@Column(name = "credits_text")
	private String creditsText;

	@Column(name = "source_name")
	private String sourceName;

	@Column(name = "price_per_serving")
	private Double pricePerServing;

	@Column(name = "title")
	private String title;

	@Column(name = "ready_in_minutes")
	private Long readyInMinutes;

	@Column(name = "servings")
	private Long servings;

	@Column(name = "image")
	private String image;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "summary")
	private String summary;

	@Column(name = "instructions")
	private String instructions;

	@Column(name = "original_id")
	private Long originalId;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="recipe_base_id")
	private RecipeBase recipeBase;

	@OneToMany(mappedBy = "recipeDetail",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailDishTypes> recipeDetailDishTypes = new ArrayList<>();

	@OneToMany(mappedBy = "recipeDetail",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailCuisines> recipeDetailCuisines = new ArrayList<>();

	@OneToMany(mappedBy = "recipeDetail",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailIngredients> recipeDetailIngredients = new ArrayList<>();

	@OneToMany(mappedBy = "recipeDetail",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Collection<RecipeDetailSteps> recipeDetailSteps = new ArrayList<>();

	public RecipeDetail() {}

	public RecipeDetail(Boolean vegetarian,
			Boolean vegan,
			Boolean glutenFree,
			Boolean dairyFree,
			Boolean veryHealthy,
			Boolean cheap,
			Boolean veryPopular,
			Boolean sustainable,
			Boolean lowFodmap,
			Long weightWatcherSmartPoints,
			String gaps,
			Long preparationInMinutes,
			Long cookingMinutes,
			String sourceUrl,
			String spoonacularSourceUrl,
			Long aggregateLikes,
			Double spoonacularScore,
			Double healthScore,
			String creditsText,
			String sourceName,
			Double pricePerServing,
			String title,
			Long readyInMinutes,
			Long servings,
			String image,
			String imageType,
			String summary,
			String instructions,
			Long originalId)
	{
		this.vegetarian = vegetarian;
		this.vegan = vegan;
		this.glutenFree = glutenFree;
		this.dairyFree = dairyFree;
		this.veryHealthy = veryHealthy;
		this.cheap = cheap;
		this.veryPopular = veryPopular;
		this.sustainable = sustainable;
		this.lowFodmap = lowFodmap;
		this.weightWatcherSmartPoints = weightWatcherSmartPoints;
		this.gaps = gaps;
		this.preparationInMinutes = preparationInMinutes;
		this.cookingMinutes = cookingMinutes;
		this.sourceUrl = sourceUrl;
		this.spoonacularSourceUrl = spoonacularSourceUrl;
		this.aggregateLikes = aggregateLikes;
		this.spoonacularScore = spoonacularScore;
		this.healthScore = healthScore;
		this.creditsText = creditsText;
		this.sourceName = sourceName;
		this.pricePerServing = pricePerServing;
		this.title = title;
		this.readyInMinutes = readyInMinutes;
		this.servings = servings;
		this.image = image;
		this.imageType = imageType;
		this.summary = summary;
		this.instructions = instructions;
		this.originalId = originalId;
	}

	public Long getId()
	{
		return id;
	}

	public Boolean getVegetarian()
	{
		return vegetarian;
	}

	public Boolean getVegan()
	{
		return vegan;
	}

	public Boolean getGlutenFree()
	{
		return glutenFree;
	}

	public Boolean getDairyFree()
	{
		return dairyFree;
	}

	public Boolean getVeryHealthy()
	{
		return veryHealthy;
	}

	public Boolean getCheap()
	{
		return cheap;
	}

	public Boolean getVeryPopular()
	{
		return veryPopular;
	}

	public Boolean getSustainable()
	{
		return sustainable;
	}

	public Boolean getLowFodmap()
	{
		return lowFodmap;
	}

	public Long getWeightWatcherSmartPoints()
	{
		return weightWatcherSmartPoints;
	}

	public String getGaps()
	{
		return gaps;
	}

	public Long getPreparationInMinutes()
	{
		return preparationInMinutes;
	}

	public Long getCookingMinutes()
	{
		return cookingMinutes;
	}

	public String getSourceUrl()
	{
		return sourceUrl;
	}

	public String getSpoonacularSourceUrl()
	{
		return spoonacularSourceUrl;
	}

	public Long getAggregateLikes()
	{
		return aggregateLikes;
	}

	public Double getSpoonacularScore()
	{
		return spoonacularScore;
	}

	public Double getHealthScore()
	{
		return healthScore;
	}

	public String getCreditsText()
	{
		return creditsText;
	}

	public String getSourceName()
	{
		return sourceName;
	}

	public Double getPricePerServing()
	{
		return pricePerServing;
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

	public String getImageType()
	{
		return imageType;
	}

	public String getSummary()
	{
		return summary;
	}

	public String getInstructions()
	{
		return instructions;
	}

	public Long getOriginalId()
	{
		return originalId;
	}

	public RecipeBase getRecipeBase()
	{
		return recipeBase;
	}

	public Collection<RecipeDetailDishTypes> getRecipeDetailDishTypes()
	{
		return recipeDetailDishTypes;
	}

	public Collection<RecipeDetailCuisines> getRecipeDetailCuisines()
	{
		return recipeDetailCuisines;
	}

	public Collection<RecipeDetailIngredients> getRecipeDetailIngredients()
	{
		return recipeDetailIngredients;
	}

	public Collection<RecipeDetailSteps> getRecipeDetailSteps()
	{
		return recipeDetailSteps;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setVegetarian(Boolean vegetarian)
	{
		this.vegetarian = vegetarian;
	}

	public void setVegan(Boolean vegan)
	{
		this.vegan = vegan;
	}

	public void setGlutenFree(Boolean glutenFree)
	{
		this.glutenFree = glutenFree;
	}

	public void setDairyFree(Boolean dairyFree)
	{
		this.dairyFree = dairyFree;
	}

	public void setVeryHealthy(Boolean veryHealthy)
	{
		this.veryHealthy = veryHealthy;
	}

	public void setCheap(Boolean cheap)
	{
		this.cheap = cheap;
	}

	public void setVeryPopular(Boolean veryPopular)
	{
		this.veryPopular = veryPopular;
	}

	public void setSustainable(Boolean sustainable)
	{
		this.sustainable = sustainable;
	}

	public void setLowFodmap(Boolean lowFodmap)
	{
		this.lowFodmap = lowFodmap;
	}

	public void setWeightWatcherSmartPoints(Long weightWatcherSmartPoints)
	{
		this.weightWatcherSmartPoints = weightWatcherSmartPoints;
	}

	public void setGaps(String gaps)
	{
		this.gaps = gaps;
	}

	public void setPreparationInMinutes(Long preparationInMinutes)
	{
		this.preparationInMinutes = preparationInMinutes;
	}

	public void setCookingMinutes(Long cookingMinutes)
	{
		this.cookingMinutes = cookingMinutes;
	}

	public void setSourceUrl(String sourceUrl)
	{
		this.sourceUrl = sourceUrl;
	}

	public void setSpoonacularSourceUrl(String spoonacularSourceUrl)
	{
		this.spoonacularSourceUrl = spoonacularSourceUrl;
	}

	public void setAggregateLikes(Long aggregateLikes)
	{
		this.aggregateLikes = aggregateLikes;
	}

	public void setSpoonacularScore(Double spoonacularScore)
	{
		this.spoonacularScore = spoonacularScore;
	}

	public void setHealthScore(Double healthScore)
	{
		this.healthScore = healthScore;
	}

	public void setCreditsText(String creditsText)
	{
		this.creditsText = creditsText;
	}

	public void setSourceName(String sourceName)
	{
		this.sourceName = sourceName;
	}

	public void setPricePerServing(Double pricePerServing)
	{
		this.pricePerServing = pricePerServing;
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

	public void setImageType(String imageType)
	{
		this.imageType = imageType;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}

	public void setOriginalId(Long originalId)
	{
		this.originalId = originalId;
	}

	public void setRecipeBase(RecipeBase recipeBase)
	{
		this.recipeBase = recipeBase;
	}

	public void setRecipeDetailDishTypes(Collection<RecipeDetailDishTypes> recipeDetailDishTypes)
	{
		this.recipeDetailDishTypes = recipeDetailDishTypes;
	}

	public void setRecipeDetailCuisines(Collection<RecipeDetailCuisines> recipeDetailCuisines)
	{
		this.recipeDetailCuisines = recipeDetailCuisines;
	}

	public void setRecipeDetailIngredients(Collection<RecipeDetailIngredients> recipeDetailIngredients)
	{
		this.recipeDetailIngredients = recipeDetailIngredients;
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
		RecipeDetail that = (RecipeDetail) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(vegetarian, that.vegetarian) &&
				Objects.equals(vegan, that.vegan) &&
				Objects.equals(glutenFree, that.glutenFree) &&
				Objects.equals(dairyFree, that.dairyFree) &&
				Objects.equals(veryHealthy, that.veryHealthy) &&
				Objects.equals(cheap, that.cheap) &&
				Objects.equals(veryPopular, that.veryPopular) &&
				Objects.equals(sustainable, that.sustainable) &&
				Objects.equals(lowFodmap, that.lowFodmap) &&
				Objects.equals(weightWatcherSmartPoints, that.weightWatcherSmartPoints) &&
				Objects.equals(gaps, that.gaps) &&
				Objects.equals(preparationInMinutes, that.preparationInMinutes) &&
				Objects.equals(cookingMinutes, that.cookingMinutes) &&
				Objects.equals(sourceUrl, that.sourceUrl) &&
				Objects.equals(spoonacularSourceUrl, that.spoonacularSourceUrl) &&
				Objects.equals(aggregateLikes, that.aggregateLikes) &&
				Objects.equals(spoonacularScore, that.spoonacularScore) &&
				Objects.equals(healthScore, that.healthScore) &&
				Objects.equals(creditsText, that.creditsText) &&
				Objects.equals(sourceName, that.sourceName) &&
				Objects.equals(pricePerServing, that.pricePerServing) &&
				Objects.equals(title, that.title) &&
				Objects.equals(readyInMinutes, that.readyInMinutes) &&
				Objects.equals(servings, that.servings) &&
				Objects.equals(image, that.image) &&
				Objects.equals(imageType, that.imageType) &&
				Objects.equals(summary, that.summary) &&
				Objects.equals(instructions, that.instructions) &&
				Objects.equals(originalId, that.originalId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id,
				vegetarian,
				vegan,
				glutenFree,
				dairyFree,
				veryHealthy,
				cheap,
				veryPopular,
				sustainable,
				lowFodmap,
				weightWatcherSmartPoints,
				gaps,
				preparationInMinutes,
				cookingMinutes,
				sourceUrl,
				spoonacularSourceUrl,
				aggregateLikes,
				spoonacularScore,
				healthScore,
				creditsText,
				sourceName,
				pricePerServing,
				title,
				readyInMinutes,
				servings,
				image,
				imageType,
				summary,
				instructions,
				originalId);
	}

	@Override
	public String toString()
	{
		return "RecipeDetail{" +
				"id=" + id +
				", vegetarian=" + vegetarian +
				", vegan=" + vegan +
				", glutenFree=" + glutenFree +
				", dairyFree=" + dairyFree +
				", veryHealthy=" + veryHealthy +
				", cheap=" + cheap +
				", veryPopular=" + veryPopular +
				", sustainable=" + sustainable +
				", lowFodmap=" + lowFodmap +
				", weightWatcherSmartPoints=" + weightWatcherSmartPoints +
				", gaps='" + gaps + '\'' +
				", preparationInMinutes=" + preparationInMinutes +
				", cookingMinutes=" + cookingMinutes +
				", sourceUrl='" + sourceUrl + '\'' +
				", spoonacularSourceUrl='" + spoonacularSourceUrl + '\'' +
				", aggregateLikes=" + aggregateLikes +
				", spoonacularScore=" + spoonacularScore +
				", healthScore=" + healthScore +
				", creditsText='" + creditsText + '\'' +
				", sourceName='" + sourceName + '\'' +
				", pricePerServing=" + pricePerServing +
				", title='" + title + '\'' +
				", readyInMinutes=" + readyInMinutes +
				", servings=" + servings +
				", image='" + image + '\'' +
				", imageType='" + imageType + '\'' +
				", summary='" + summary + '\'' +
				", instructions='" + instructions + '\'' +
				", originalId=" + originalId +
				", recipeBase=" + recipeBase +
				", recipeDetailDishTypes=" + recipeDetailDishTypes +
				", recipeDetailCuisines=" + recipeDetailCuisines +
				", recipeDetailIngredients=" + recipeDetailIngredients +
				", recipeDetailSteps=" + recipeDetailSteps +
				'}';
	}
}

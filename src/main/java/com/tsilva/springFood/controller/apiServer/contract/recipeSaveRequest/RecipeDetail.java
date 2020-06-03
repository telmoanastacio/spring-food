package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDetail
{
    private Boolean vegetarian;
    private Boolean vegan;
    private Boolean glutenFree;
    private Boolean dairyFree;
    private Boolean veryHealthy;
    private Boolean cheap;
    private Boolean veryPopular;
    private Boolean sustainable;
    private Boolean lowFodmap;
    private Long weightWatcherSmartPoints;
    private String gaps;
    private Long preparationInMinutes;
    private Long cookingMinutes;
    private String sourceUrl;
    private Long aggregateLikes;
    private Double popularityScore;
    private Double healthScore;
    private String creditsText;
    private String sourceName;
    private Double pricePerServing;
    private String title;
    private Long readyInMinutes;
    private Long servings;
    private String image;
    private String imageType;
    private String summary;
    private String instructions;
    private Long originalId;
    private List<String> dishTypes;
    private List<String> cuisines;
    private List<List<Step>> stepOptionList;
    private List<Ingredient> ingredients;

    private RecipeDetail() {}

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

    public Long getAggregateLikes()
    {
        return aggregateLikes;
    }

    public Double getPopularityScore()
    {
        return popularityScore;
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

    public List<String> getDishTypes()
    {
        return dishTypes;
    }

    public List<String> getCuisines()
    {
        return cuisines;
    }

    public List<List<Step>> getStepOptionList()
    {
        return stepOptionList;
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
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

    public void setAggregateLikes(Long aggregateLikes)
    {
        this.aggregateLikes = aggregateLikes;
    }

    public void setPopularityScore(Double popularityScore)
    {
        this.popularityScore = popularityScore;
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

    public void setDishTypes(List<String> dishTypes)
    {
        this.dishTypes = dishTypes;
    }

    public void setCuisines(List<String> cuisines)
    {
        this.cuisines = cuisines;
    }

    public void setStepOptionList(List<List<Step>> stepOptionList)
    {
        this.stepOptionList = stepOptionList;
    }

    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }
}

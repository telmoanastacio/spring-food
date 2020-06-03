package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient
{
    private String aisle;
    private String image;
    private String consistency;
    private String name;
    private String original;
    private String originalString;
    private String originalName;
    private Double amount;
    private String unit;
    private List<String> ingredientMetaList;
    private List<String> ingredientMetaInformationList;
    private Measure measure;

    private Ingredient() {}

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

    public List<String> getIngredientMetaList()
    {
        return ingredientMetaList;
    }

    public List<String> getIngredientMetaInformationList()
    {
        return ingredientMetaInformationList;
    }

    public Measure getMeasure()
    {
        return measure;
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

    public void setIngredientMetaList(List<String> ingredientMetaList)
    {
        this.ingredientMetaList = ingredientMetaList;
    }

    public void setIngredientMetaInformationList(List<String> ingredientMetaInformationList)
    {
        this.ingredientMetaInformationList = ingredientMetaInformationList;
    }

    public void setMeasure(Measure measure)
    {
        this.measure = measure;
    }
}

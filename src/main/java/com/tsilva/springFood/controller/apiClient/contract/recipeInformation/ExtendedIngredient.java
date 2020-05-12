package com.tsilva.springFood.controller.apiClient.contract.recipeInformation;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class ExtendedIngredient
{
    public Long id;
    public String aisle;
    public String image;
    public String consistency;
    public String name;
    public String original;
    public String originalString;
    public String originalName;
    public Double amount;
    public String unit;
    public List<String> meta;
    public List<String> metaInformation;
    public Measures measures;
}

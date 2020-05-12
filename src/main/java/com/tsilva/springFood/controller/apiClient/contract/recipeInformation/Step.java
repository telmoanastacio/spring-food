package com.tsilva.springFood.controller.apiClient.contract.recipeInformation;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class Step
{
    public Long number;
    public String step;
    public List<Ingredient> ingredients;
    public List<Equipment> equipment;
}

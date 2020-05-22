package com.tsilva.springFood.controller.apiClient;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class ApiConfig
{
    // original - ff757ffedaa5461991013c18cd42ebdc
    // 599c6408128643deb027e69e7f9d0acc
    // a0632677ed914bcd957ca64b143092ee
    // 03f8f15a9f754977a80929db5abe80c1
    // 7104f4e418ff4b92a331b14b38e6feb0
    // e324e0d43ed6492b858c5b74963baf8c
    // ae2c8d94ba304e50883151675d3ba528
    // 818da5ec867b45888f1a243c1e7e36a6
    // 12f1e330bead4c11a6150012ef62a9af
    // 26036fddee1d45ef8bb1ea274ccba169
    public static final String SPOONACULAR_API_KEY = "12f1e330bead4c11a6150012ef62a9af";
    public static final String SPOONACULAR_API_ENDPOINT = "https://api.spoonacular.com/";
    public static final long MAXIMUM_NUMBER_OF_RECIPES_PER_REQUEST = 100L;
    public static final int WAIT_AFTER_SUCCESSFUL_SEARCH_SECONDS = 30 * 24 * 3600;
}
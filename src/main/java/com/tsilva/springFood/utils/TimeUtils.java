package com.tsilva.springFood.utils;

import java.util.Date;

/**
 * Created by Telmo Silva on 22.05.2020.
 */

public class TimeUtils
{
    public static Date now()
    {
        return new Date();
    }

    public static Date dateFromTimeStamp(Long timeStamp)
    {
        return new Date(timeStamp);
    }
}

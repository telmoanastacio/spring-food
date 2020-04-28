package com.tsilva.springFood.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public class ResourceUtils
{
    public static Document readHtmlFileToDocument(ServletContext servletContext, String path)
    {
        String htmlStr = "";
        InputStream is = null;
        try
        {
            is = servletContext.getResourceAsStream(path);
            htmlStr = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return Jsoup.parse(htmlStr);
    }
}
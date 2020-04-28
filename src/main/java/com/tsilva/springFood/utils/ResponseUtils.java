package com.tsilva.springFood.utils;

import org.jsoup.nodes.Document;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Telmo Silva on 28.04.2020.
 */

public class ResponseUtils
{
    public static void streamHtml(HttpServletResponse response, String htmlStr)
    {
        if(response == null || htmlStr == null)
        {
            return;
        }

        OutputStream os = null;
        try
        {
            os = response.getOutputStream();
            response.setContentType(MediaType.TEXT_HTML_VALUE);
            StreamUtils.copy(htmlStr.getBytes(), os);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void streamHtml(HttpServletResponse response, InputStream is)
    {
        if(response == null || is == null)
        {
            return;
        }

        OutputStream os = null;
        try
        {
            os = response.getOutputStream();
            response.setContentType(MediaType.TEXT_HTML_VALUE);
            StreamUtils.copy(is, os);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void streamText(HttpServletResponse response, String text)
    {
        if(response == null || text == null)
        {
            return;
        }

        OutputStream os = null;
        try
        {
            os = response.getOutputStream();
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            StreamUtils.copy(text.getBytes(), os);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void streamIndex(
            ServletContext servletContext,
            HttpServletRequest request,
            HttpServletResponse response)
    {
        Document document = ResourceUtils.readHtmlFileToDocument(
                servletContext,
                "/angular/index.html");
        CsrfToken csrfToken = CSRFUtils.getCSRFToken(request);
        document.select("body").first().children().last().after(CSRFUtils.getCSRFTag(csrfToken));

        String htmlStr = document.html();
        ResponseUtils.streamHtml(response, htmlStr);
    }

    public static void streamCsrfToken(
            HttpServletRequest request,
            HttpServletResponse response)
    {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        streamText(response, csrfToken.getToken());
    }
}
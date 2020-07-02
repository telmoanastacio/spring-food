package com.tsilva.springFood.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Telmo Silva on 28.04.2020.
 */

public class ResponseUtils
{
    private static final Logger LOG = LoggerFactory.getLogger(ResponseUtils.class);
    private static Map<String, MediaType> supportedImageFileMediaTypeMap = null;

    static
    {
        supportedImageFileMediaTypeMap = new HashMap<>();
        supportedImageFileMediaTypeMap.put("png", MediaType.IMAGE_PNG);
        supportedImageFileMediaTypeMap.put("jpg", MediaType.IMAGE_JPEG);
        supportedImageFileMediaTypeMap.put("jpeg", MediaType.IMAGE_JPEG);
        supportedImageFileMediaTypeMap.put("jpe", MediaType.IMAGE_JPEG);
        supportedImageFileMediaTypeMap.put("jfif", MediaType.IMAGE_JPEG);
        supportedImageFileMediaTypeMap.put("gif", MediaType.IMAGE_GIF);
    }

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
            LOG.debug("streamHtml()", e);
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
                LOG.debug("streamHtml()", e);
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
            LOG.debug("streamHtml()", e);
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
                LOG.debug("streamHtml()", e);
            }

            try
            {
                is.close();
            }
            catch (IOException e)
            {
                LOG.debug("streamHtml()", e);
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
            LOG.debug("streamText()", e);
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
                LOG.debug("streamText()", e);
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

    public static void streamObjectAsJson(HttpServletResponse response, Object o)
    {
        if(response == null || o == null)
        {
            return;
        }

        OutputStream os = null;
        try
        {
            os = response.getOutputStream();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, o);
        }
        catch (IOException e)
        {
            LOG.debug("streamObjectAsJson()", e);
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
                LOG.debug("streamObjectAsJson()", e);
            }
        }
    }

    public static void streamImage(
            HttpServletResponse response,
            ServletContext servletContext,
            String resourceName,
            String fileExtension)
    {
        if(response == null || servletContext == null)
        {
            LOG.debug("streamImage(): Invalid HttpServletResponse or ServletContext");
            return;
        }

        if(resourceName == null || resourceName.isEmpty())
        {
            LOG.debug("streamImage(): Invalid resourceName");
            return;
        }

        if(fileExtension == null || fileExtension.isEmpty())
        {
            LOG.debug("streamImage(): Invalid fileExtension");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("/res/");
        sb.append(resourceName);
        sb.append(".");
        sb.append(fileExtension);

        InputStream is = null;
        OutputStream os = null;
        try
        {
            is = servletContext.getResourceAsStream(sb.toString());
            if(is == null)
            {
                LOG.debug("streamImage(): No file exists at the specified path -> " + sb.toString());
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            os = response.getOutputStream();
            MediaType mediaType = supportedImageFileMediaTypeMap.get(fileExtension);
            if(mediaType == null)
            {
                LOG.debug("streamImage(): Media type not supported -> ." + fileExtension);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            response.setContentType(mediaType.getType());
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            StreamUtils.copy(is, os);
        }
        catch(IOException e)
        {
            LOG.debug("streamImage()", e);
        }
        finally
        {
            if(is != null)
            {
                try
                {
                    is.close();
                }
                catch(IOException e)
                {
                    LOG.debug("streamImage()", e);
                }
            }

            if(os != null)
            {
                try
                {
                    os.close();
                }
                catch(IOException e)
                {
                    LOG.debug("streamImage()", e);
                }
            }
        }
    }
}
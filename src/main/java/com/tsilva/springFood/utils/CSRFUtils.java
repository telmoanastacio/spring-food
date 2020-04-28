package com.tsilva.springFood.utils;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public class CSRFUtils
{
    public static CsrfToken getCSRFToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    public static String getCSRFTag(CsrfToken csrfToken)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("<input type=\"hidden\"");
        sb.append("name=\"");
        sb.append(csrfToken.getParameterName());
        sb.append("\"");
        sb.append("value=\"");
        sb.append(csrfToken.getToken());
        sb.append("\" />");

        return sb.toString();
    }
}
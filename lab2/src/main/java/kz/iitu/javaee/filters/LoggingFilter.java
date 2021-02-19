package kz.iitu.javaee.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class LoggingFilter implements Filter {
    private List<String> registerRequestParameters;
    private List<String> registerCookieParameters;

    public void init(FilterConfig filterConfig) throws ServletException {
        registerRequestParameters = new ArrayList<String>();
        registerCookieParameters = new ArrayList<String>();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("1. LoggingFilter");
        Enumeration<String> requestParameterNames = servletRequest.getParameterNames();
        Cookie [] requestCookieNames = ((HttpServletRequest)servletRequest).getCookies();

        while (requestParameterNames.hasMoreElements()) {
            String key = requestParameterNames.nextElement();
            String str = key + "=" + servletRequest.getParameter(key) + " [" + new Date() + "]";
            registerRequestParameters.add(str);
            System.out.println("Add[registerRequestParameters]: " + str);
        }

        for (Cookie cookie : requestCookieNames) {
            String str = cookie.getName() + " = " + cookie.getValue() + " [" + new Date() + "]";
            registerCookieParameters.add(str);
            System.out.println("Add[registerCookieParameters]: " + str);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}

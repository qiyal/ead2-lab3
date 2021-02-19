package kz.iitu.javaee.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


public class SessionValidationFilter implements Filter {
    FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();

        if (session.getAttribute("AUTH_STATUS") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        String username = null;
        String password = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("AUTH_STATUS")) {
                session.setAttribute("AUTH_STATUS", "TRUE");
                filterChain.doFilter(servletRequest, servletResponse);
            }
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }

        if (username != null && password != null) {
            Enumeration<String> elements = config.getInitParameterNames();
            while (elements.hasMoreElements()) {
                String elemName = elements.nextElement();

                if (elemName.equals(username) && config.getInitParameter(elemName).equals(password)) {
                    session.setAttribute("AUTH_STATUS", "TRUE");
                    System.out.println("checks pass username");
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }


        ((HttpServletResponse)servletResponse).sendRedirect("login");
    }

    public void destroy() {

    }
}

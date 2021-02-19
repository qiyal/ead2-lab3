package kz.iitu.javaee.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResponseFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("3. ResponseFilter");

        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletResponse response = ((HttpServletResponse)servletResponse);


        Cookie [] cookies = ((HttpServletRequest)servletRequest).getCookies();

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(cookie.getMaxAge() + (30 * 60));
            response.addCookie(cookie);
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        session.setMaxInactiveInterval(session.getMaxInactiveInterval() + (30 * 60));
    }

    public void destroy() {

    }
}

package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/signin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (session.getAttribute("login") == null && !request.getRequestURI().contains("/register") && !request.getRequestURI().contains("/cities_by_country")) {
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {

    }
}

package ru.job4j.carsale.filter;

import ru.job4j.carsale.dao.AdDao;
import ru.job4j.models.Ad;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManageAdFilter implements Filter {
    private final AdDao adDao = new AdDao();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        int id = Integer.valueOf(request.getParameter("id"));
        Ad ad = adDao.getById(id);

        if (userId == ad.getUser().getId()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/list", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {
        adDao.close();
    }
}

package ru.job4j.carsale.controller;

import ru.job4j.carsale.dao.AdDao;
import ru.job4j.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class List extends HttpServlet {
    private final AdDao adDao = new AdDao();
    private String uploadPath;

    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getInitParameter("upload-directory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("path", req.getContextPath());
        req.setAttribute("uploadPath", req.getContextPath() + File.separator + uploadPath + File.separator);
        java.util.List<Ad> list = adDao.getAll();
        req.setAttribute("ads", list);
        req.getRequestDispatcher("WEB-INF/view/list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        adDao.close();
    }
}

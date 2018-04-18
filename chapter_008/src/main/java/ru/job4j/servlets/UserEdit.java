package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEdit extends HttpServlet {
    private final DbController db = DbController.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int roleId = db.getRoleId(req.getParameter("role"));
        int countryId = db.getCountryId(req.getParameter("country"));
        int cityId = db.getCityId(req.getParameter("city"));
        db.editUser(login, password, email, roleId, countryId, cityId);
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

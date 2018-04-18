package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends HttpServlet {
    private final DbController db = DbController.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", db.getRoles());
        req.setAttribute("countries", db.getCountries());
        req.setAttribute("cities", db.getCities());
        req.getRequestDispatcher("/WEB-INF/view/RegisterView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int countryId = db.getCountryId(req.getParameter("country"));
        int cityId = db.getCityId(req.getParameter("city"));
        int roleId = db.getRoleId(req.getParameter("role"));

        db.addUser(login, password, email, roleId, countryId, cityId);
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/register", req.getContextPath()));
    }
}

package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersController extends HttpServlet {
    private final DbController db = DbController.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int role = db.getUserRole((String) session.getAttribute("login"));
        if (role == 1) {
            req.setAttribute("users", db.getUsers());
            req.getRequestDispatcher("/WEB-INF/view/SuperUserView.jsp").forward(req, resp);
        } else {
            req.setAttribute("users", db.getOneUser((String) session.getAttribute("login")));
            req.getRequestDispatcher("/WEB-INF/view/UserView.jsp").forward(req, resp);
        }
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
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

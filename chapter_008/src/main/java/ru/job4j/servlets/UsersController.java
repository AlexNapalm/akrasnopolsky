package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersController extends HttpServlet {
    private final UserStore users = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
        } else {
            int role = users.getUserRole((String) session.getAttribute("login"));
            if (role == 1) {
                req.setAttribute("users", users.getUsers());
                req.getRequestDispatcher("/WEB-INF/view/SuperUserView.jsp").forward(req, resp);
            } else {
                req.setAttribute("users", users.getOneUser((String) session.getAttribute("login")));
                req.getRequestDispatcher("/WEB-INF/view/UserView.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
        users.addUser(login, password, email, role);
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

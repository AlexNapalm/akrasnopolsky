package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserEdit extends HttpServlet {
    private final UserStore users = UserStore.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
        users.editUser(login, password, email, role);
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}

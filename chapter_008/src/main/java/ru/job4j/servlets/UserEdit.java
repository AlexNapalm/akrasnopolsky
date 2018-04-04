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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String email = users.getEmailByLogin(login);
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Edit</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit' method='post'>"
                + "Name : <input type='text' name='login' value=" + login + ">"
                + "\tEmail:  <input type='text' name='email' value=" + email + ">"
                + "\t<input type='submit'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        users.editUser(login, email);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "User edited!"
                + "<br><form method='get' action='" + req.getContextPath() + "/list'>"
                + "<button type='submit'>Show all users</button>"
                + "</body>"
                + "</html>");
        writer.flush();
    }
}

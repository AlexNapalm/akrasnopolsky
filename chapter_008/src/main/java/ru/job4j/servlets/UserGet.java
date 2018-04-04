package ru.job4j.servlets;

import ru.job4j.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserGet extends HttpServlet {
    private final UserStore users = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> list = users.getAllUsers();
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>List</title>"
                + "</head>"
                + "<body>");
        for (User user : list) {
            writer.append("<form action='" + req.getContextPath() + "/edit' method='get'>"
                    + "<p>Login: <input type='text' name='login' value=" + user.getLogin() + ">"
                    + "<br>Email:  <input type='text' name='email' value=" + user.getEmail() + ">"
                    + "<input type='submit' value='Edit'>"
                    + "<button formaction='" + req.getContextPath() + "/delete'> Delete </button>"
                    + "</form>");
        }
        writer.append("<br><form method='get' action='" + req.getContextPath() + "/create'>"
                + "<button type='submit'>Create new user</button>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

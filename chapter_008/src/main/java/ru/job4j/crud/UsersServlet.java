package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private final UserStore store = UserStore.getInstance();

    /**
     * Get.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<b> Users: </b>");
        writer.println("<table border=1>");
        writer.println("<tr>");
        writer.println("<td>login</td>");
        writer.println("<td>email</td>");
        writer.println("<td>createDate</td>");
        writer.println("</tr>");
        List<User> users = store.getUsers();
        for (User user : users) {
            writer.println("<tr>");
            writer.println("<td>" + user.getLogin() + "</td>");
            writer.println("<td>" + user.getEmail() + "</td>");
            writer.println("<td>" + user.getCreateDate() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }

    /**
     * Add.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String createDate = req.getParameter("createDate");
        store.add(login, email, createDate);
    }

    /**
     * Update.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        store.update(login, email);
    }

    /**
     * Delete.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        store.delete(login);
    }
}

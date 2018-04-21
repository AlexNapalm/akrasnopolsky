package ru.job4j.musicbox.servlets;

import ru.job4j.musicbox.dao.UserDao;
import ru.job4j.musicbox.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/MusicboxAuthView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userDao.isRegistered(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", user.getLogin());
            String role = user.getRole().getRole();
            req.setAttribute("login", user.getLogin());
            req.setAttribute("role", role);
            resp.sendRedirect(String.format("%s/musicbox/dashboard?login=%s&role=%s", req.getContextPath(), login, role));
        } else {
            req.setAttribute("error", "Credentials are invalid");
            doGet(req, resp);
        }
    }
}

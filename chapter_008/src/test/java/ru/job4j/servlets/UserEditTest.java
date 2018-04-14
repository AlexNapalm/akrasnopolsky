package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.crud.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserEditTest {
    private final UserStore userStore = UserStore.INSTANCE;

    @Before
    public void setUp() {
        userStore.flushTable();
    }

    @Test
    public void whenEditUserThenTableHasUpdatedUser() throws ServletException, IOException {
        UsersController controller = new UsersController();
        UserEdit editServlet = new UserEdit();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root123");
        when(request.getParameter("email")).thenReturn("root@root.ru");
        when(request.getParameter("role")).thenReturn("1");
        controller.doPost(request, response);

        when(request.getParameter("login")).thenReturn("Alex");
        when(request.getParameter("password")).thenReturn("Krasnopolsky");
        when(request.getParameter("email")).thenReturn("alex@krasnopolsky.ru");
        when(request.getParameter("role")).thenReturn("2");
        controller.doPost(request, response);

        when(request.getParameter("login")).thenReturn("Alex");
        when(request.getParameter("password")).thenReturn("Krasnopolsky");
        when(request.getParameter("email")).thenReturn("alex@newemail.ru");
        when(request.getParameter("role")).thenReturn("1");
        editServlet.doPost(request, response);

        List<User> users = userStore.getUsers();

        assertThat(users.get(1).getLogin(), is("Alex"));
        assertThat(users.get(1).getPassword(), is("Krasnopolsky"));
        assertThat(users.get(1).getEmail(), is("alex@newemail.ru"));
        assertThat(users.get(1).getRole(), is(1));
    }
}
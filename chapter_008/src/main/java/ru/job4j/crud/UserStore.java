package ru.job4j.crud;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStore {
    Connection conn;
    private static final UserStore INSTANCE = new UserStore();
    private static final Logger LOGGER = Logger.getLogger(UserStore.class);

    private UserStore() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/java_a_to_z";
            String username = "postgres";
            String password = "postgres";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    /**
     * Adds user to database.
     * @param login login.
     * @param email email.
     * @param createDate createDate.
     */
    public void add(String login, String email, String createDate) {
        try (PreparedStatement pst = conn.prepareStatement("insert into usersserv(login, email, createDate) values (?, ?, ?)")) {
            pst.setString(1, login);
            pst.setString(2, email);
            pst.setString(3, createDate);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Updates email.
     * @param login login of user to update.
     * @param email new email.
     */
    public void update(String login, String email) {
        try (PreparedStatement pst = conn.prepareStatement("update usersserv set email = (?) where login = (?)")) {
            pst.setString(1, email);
            pst.setString(2, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes user from database.
     * @param login login of user to delete.
     */
    public void delete(String login) {
        try (PreparedStatement pst = conn.prepareStatement("delete from usersserv  where login = (?)")) {
            pst.setString(1, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Gets all users.
     * @return list of users.
     */
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement("select * from usersserv");) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getString("createDate"));
                result.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}

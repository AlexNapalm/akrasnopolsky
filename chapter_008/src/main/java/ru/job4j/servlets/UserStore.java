package ru.job4j.servlets;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ru.job4j.crud.User;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum UserStore {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(UserStore.class);
    private static Connection conn;
    private static ComboPooledDataSource cpds;

    /**
     * Initializing connection pool.
     */
    static  {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/java_a_to_z");
            cpds.setUser("postgres");
            cpds.setPassword("postgres");
            cpds.setMaxPoolSize(20);

            conn = cpds.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Adds user.
     * @param login login
     * @param email email
     */
    public void addUser(String login, String email) {
        try (PreparedStatement pst = conn.prepareStatement("INSERT INTO usersserv(login, email, createDate) values(?, ?, current_date)")) {
            pst.setString(1, login);
            pst.setString(2, email);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Updates user.
     * @param login login.
     * @param email email.
     */
    public void editUser(String login, String email) {
        try (PreparedStatement pst = conn.prepareStatement("update usersserv set email = (?) where login = (?)")) {
            pst.setString(1, email);
            pst.setString(2, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Gets list of users.
     * @return list of users.
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement("select * from usersserv");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getString("createDate"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets email by login.
     * @param login login.
     * @return email.
     */
    public String getEmailByLogin(String login) {
       String result = null;
        try (PreparedStatement pst = conn.prepareStatement("select email from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes user.
     * @param login login.
     */
    public void deleteUser(String login) {
        try (PreparedStatement pst = conn.prepareStatement("delete from usersserv where login = (?)")) {
            pst.setString(1, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

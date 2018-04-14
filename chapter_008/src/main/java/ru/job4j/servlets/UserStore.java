package ru.job4j.servlets;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ru.job4j.crud.Role;
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
        } catch (PropertyVetoException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Adds user.
     * @param login login
     * @param email email
     */
    public void addUser(String login, String password, String email, int roleId) {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO usersserv(login, password, email, role_id, createDate) values(?, ?, ?, ?, current_date)")) {
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setInt(4, roleId);
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
    public void editUser(String login, String password, String email, int roleId) {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("update usersserv set password = (?), email = (?), role_id = (?) where login = (?)")) {
            pst.setString(1, password);
            pst.setString(2, email);
            pst.setInt(3, roleId);
            pst.setString(4, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Gets list of users.
     * @return list of users.
     */
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select * from usersserv");
             ResultSet rs = pst.executeQuery()) {
            result = resultSetProcess(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets only user which has 'user' role.
     * @return list of users.
     */
    public List<User> getOneUser(String login) {
        List<User> result = new ArrayList<>();
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select * from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            result = resultSetProcess(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Gets user role by login.
     * @param login login.
     * @return User role.
     */
    public int getUserRole(String login) {
        int result = 0;
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select role_id from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getInt("role_id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    private List<User> resultSetProcess(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreateDate(rs.getString("createDate"));
            user.setRole(rs.getInt("role_id"));
            result.add(user);
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
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select email from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString("email");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Deletes user.
     * @param login login.
     */
    public void deleteUser(String login) {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("delete from usersserv where login = (?)")) {
            pst.setString(1, login);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Checks if user is registered.
     * @param login login.
     * @param password password.
     * @return User object, if registered and null if not.
     */
    public User isRegistered(String login, String password) {
        User user = null;
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select login, password, email, role_id from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String dbLogin = rs.getString("login");
                String dbPassword = rs.getString("password");
                if (dbLogin.equals(login) && dbPassword.equals(password)) {
                    user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getInt("role_id"));
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    public void flushTable() {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("delete from usersserv")) {
            pst.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

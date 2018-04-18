package ru.job4j.servlets;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ru.job4j.models.City;
import ru.job4j.models.Country;
import ru.job4j.models.Role;
import ru.job4j.models.User;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum DbController {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(DbController.class);
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
     * @param login login.
     * @param password password.
     * @param email email.
     * @param roleId roleId.
     * @param countryId countryId.
     * @param cityId cityId.
     */
    public void addUser(String login, String password, String email, int roleId, int countryId, int cityId) {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO usersserv (login, password, email, role_id, country_id, city_id, createdate) "
                     + "VALUES (?, ?, ?, ?, ?, ?, current_date)")) {
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setInt(4, roleId);
            pst.setInt(5, countryId);
            pst.setInt(6, cityId);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Adds user with default 'user role'
     * @param login login.
     * @param password password.
     * @param email email.
     * @param countryId countryId.
     * @param cityId cityId.
     */
    public void addUser(String login, String password, String email, int countryId, int cityId) {
        int defaultRole = 1;
        addUser(login, password, email, defaultRole, countryId, cityId);
    }

    /**
     * Updates user.
     * @param login login.
     * @param password password.
     * @param email email.
     * @param roleId roleId.
     * @param countryId countryId.
     * @param cityId cityId.
     */
    public void editUser(String login, String password, String email, int roleId, int countryId, int cityId) {
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("update usersserv set password = (?), email = (?), role_id = (?), country_id = (?), city_id = (?) where login = (?)")) {
            pst.setString(1, password);
            pst.setString(2, email);
            pst.setInt(3, roleId);
            pst.setInt(4, countryId);
            pst.setInt(5, cityId);
            pst.setString(6, login);
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
             PreparedStatement pst = conn.prepareStatement("SELECT u.login, u.password, u.email, r.title as role, co.name as country, ci.name as city, u.createdate FROM usersserv as u "
                                                             + "LEFT JOIN roles r ON u.role_id = r.id "
                                                             + "LEFT JOIN countries co ON u.country_id = co.id "
                                                             + "LEFT JOIN cities ci ON u.city_id = ci.id ");
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
             PreparedStatement pst = conn.prepareStatement("SELECT u.login, u.password, u.email, r.title as role, co.name as country, ci.name, u.createdate as city FROM usersserv as u "
                                                              + "LEFT JOIN roles r ON u.role_id = r.id "
                                                              + "LEFT JOIN countries co ON u.country_id = co.id "
                                                              + "LEFT JOIN cities ci ON u.city_id = ci.id "
                                                              + "WHERE u.login = ?")) {
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
             PreparedStatement pst = conn.prepareStatement("select role_id from usersserv where login = ?")) {
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

    public int getRoleId(String role) {
        int result = 0;
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select id from roles where lower(title) = lower(?)")) {
            pst.setString(1, role);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public int getCityId(String city) {
        int result = 0;
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select id from cities where lower(name) = lower(?)")) {
            pst.setString(1, city);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public int getCountryId(String country) {
        int result = 0;
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select id from countries where lower(name) = lower(?)")) {
            pst.setString(1, country);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getInt("id");
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
            user.setRole(rs.getString("role"));
            user.setCountry(rs.getString("country"));
            user.setCity(rs.getString("city"));
            user.setCreateDate(rs.getString("createdate"));
            result.add(user);
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
             PreparedStatement pst = conn.prepareStatement("select login, password from usersserv where login = (?)")) {
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String dbLogin = rs.getString("login");
                String dbPassword = rs.getString("password");
                if (dbLogin.equals(login) && dbPassword.equals(password)) {
                    user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
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

    /**
     * Gets list of countries.
     * @return list of countries.
     */
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();

        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select * from countries")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                countries.add(country);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return countries;
    }

    /**
     * Gets list of cities.
     * @return list of cities.
     */
    public List<City> getCities() {
        List<City> cities = new ArrayList();

        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("select * from cities")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setCountryId(rs.getInt("country_id"));
                cities.add(city);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cities;
    }

    /**
     * Gets cities by country.
     * @param country country.
     * @return list of cities.
     */
    public List<City> getCitiesByCountry(String country) {
        List<City> cities = new ArrayList();

        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM cities JOIN countries ON cities.country_id = countries.id WHERE countries.name = ?")) {
            pst.setString(1, country);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cities;
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList();
        try (Connection conn = cpds.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM roles")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("title"));
                roles.add(role);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return roles;
    }

}

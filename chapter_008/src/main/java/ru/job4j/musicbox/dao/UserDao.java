package ru.job4j.musicbox.dao;

import org.apache.log4j.Logger;
import ru.job4j.musicbox.models.Address;
import ru.job4j.musicbox.models.MusicType;
import ru.job4j.musicbox.models.Role;
import ru.job4j.musicbox.models.User;
import ru.job4j.musicbox.repository.IRepositoryUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IAbstractDao<User>, IRepositoryUser {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class);


    @Override
    public User getById(int id) {
        User user = null;

        try (Connection conn = DbConnection.INSTANCE.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM musicbox_users WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            user = this.gainUser(rs);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection conn = DbConnection.INSTANCE.getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM musicbox_users");
            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public void create(User user) {
        PreparedStatement pst = null;
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            Role role = user.getRole();
            Address address = user.getAddress();
            List<MusicType> music = user.getMusicTypes();

            conn.setAutoCommit(false);

            pst = conn.prepareStatement("INSERT INTO musicbox_addresses (address) VALUES (?)", new String[]{"id"});
            pst.setString(1, address.getAddress());
            pst.executeUpdate();
            ResultSet addressKey = pst.getGeneratedKeys();
            addressKey.next();
            int addressId = addressKey.getInt("id");

            pst = conn.prepareStatement("INSERT INTO musicbox_users VALUES "
                    + "(DEFAULT, ?, ?, ?, ?);", new String[] {"id"});
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setInt(3, role.getId());
            pst.setInt(4, addressId);
            pst.executeUpdate();

            ResultSet userKey = pst.getGeneratedKeys();
            userKey.next();
            int userId = userKey.getInt("id");

            this.addUserMusic(userId, music);

            conn.commit();
            conn.setAutoCommit(true);
            pst.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement pst = null;
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            int id = user.getId();
            String login = user.getLogin();
            String password = user.getPassword();
            Role role = user.getRole();
            Address address = user.getAddress();
            List<MusicType> music = user.getMusicTypes();

            conn.setAutoCommit(false);

            pst = conn.prepareStatement("UPDATE musicbox_addresses SET address = ? WHERE id = ?");
            pst.setString(1, address.getAddress());
            pst.setInt(2, address.getId());
            pst.executeUpdate();

            pst = conn.prepareStatement("UPDATE musicbox_users "
                    + "SET login = ?, password = ?, role_id = ? WHERE id = ?");
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setInt(3, role.getId());
            pst.setInt(4, id);
            pst.executeUpdate();

            pst = conn.prepareStatement("DELETE FROM musicbox_user_musictypes WHERE user_id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            this.addUserMusic(id, music);

            conn.commit();
            conn.setAutoCommit(true);
            pst.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(User user) {
        int id = user.getId();
        int addressId = user.getAddress().getId();
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM musicbox_user_musictypes WHERE user_id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            pst = conn.prepareStatement("DELETE FROM musicbox_users WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            pst = conn.prepareStatement("DELETE FROM musicbox_addresses WHERE id = ?");
            pst.setInt(1, addressId);
            pst.executeUpdate();

            pst.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getUsersByAddress(String address) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbConnection.INSTANCE.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id id, login, password, role_id, address_id FROM musicbox_users u JOIN musicbox_addresses a ON u.address_id=a.id WHERE address LIKE ?")) {
            pst.setString(1, "%" + address + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbConnection.INSTANCE.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM musicbox_users u JOIN musicbox_roles r ON u.role_id=r.id WHERE r.id = ?")) {
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = this.gainUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }


    /**
     * Creates user.
     * @param rs ResultSet.
     * @return User.
     */
    private User gainUser(ResultSet rs) {
        User user = new User();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                user.setId(id);
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(this.getUserRole(rs.getInt("role_id")));
                user.setAddress(this.getUserAddress(rs.getInt("address_id")));
                user.setMusicTypes(this.getUserMusic(id));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Gets user role.
     * @param roleId role id.
     * @return role.
     */
    private Role getUserRole(int roleId) {
        Role role = null;
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM musicbox_roles WHERE id = ?");
            pst.setInt(1, roleId);
            ResultSet rs = pst.executeQuery();
            rs.next();

            role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));

            pst.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }

    /**
     * Gets user address.
     * @param addressId address id.
     * @return address.
     */
    private Address getUserAddress(int addressId)  {
        Address address = null;
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM musicbox_addresses WHERE id = ?");
            pst.setInt(1, addressId);
            ResultSet rs = pst.executeQuery();
            rs.next();

            address = new Address();
            address.setId(rs.getInt("id"));
            address.setAddress(rs.getString("address"));

            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return address;
    }

    /**
     * Gets user music types.
     * @param userId user id.
     * @return music types list.
     */
    private List<MusicType> getUserMusic(int userId) {
        List<MusicType> list = new ArrayList<>();
        try {
            Connection conn = DbConnection.INSTANCE.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT id, musictype FROM musicbox_user_musictypes um JOIN musicbox_musictypes mt ON um.musictype_id = mt.id WHERE user_id = ?;");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(rs.getInt("id"));
                musicType.setMusicType(rs.getString("musicType"));
                list.add(musicType);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Adds music type for user.
     * @param id user id.
     * @param music list of music types.
     */
    private void addUserMusic(int id, List<MusicType> music) {
        for (MusicType m : music) {
            try {Connection conn = DbConnection.INSTANCE.getConnection();
                PreparedStatement pst = conn.prepareStatement("INSERT INTO user_music VALUES (?, ?)");
                pst.setInt(1, id);
                pst.setInt(2, m.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Checks if there is login-password combination.
     * @param login login.
     * @param password password.
     * @return user with login and password combination.
     */
    public User isRegistered(String login, String password) {
        User user = null;
        try {Connection conn = DbConnection.INSTANCE.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM musicbox_users WHERE login = (?) AND password = (?)");
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            user = gainUser(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Closes connections pool.
     */
    public void poolClose() {
        DbConnection.INSTANCE.close();
    }
}
package ru.job4j.musicbox.dao;

import ru.job4j.musicbox.models.Role;
import ru.job4j.musicbox.repository.IRepositoryRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD сущности роль.
 * БД PostgreSQL
 */
public class RoleDao implements IAbstractDao<Role>, IRepositoryRole {

    private Connection conn;

    public RoleDao() {
        this.conn = DbConnection.INSTANCE.getConnection();
    }

    @Override
    public Role getById(int id) {
        Role role = null;

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM musicbox_roles WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();

            role = new Role();
            role.setId(id);
            role.setRole(rs.getString("role"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();

        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM musicbox_roles");
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole(rs.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public void create(Role role) {
        try (PreparedStatement pst = this.conn.prepareStatement("INSERT INTO musicbox_roles (role) VALUES (?)")) {
            pst.setString(1, role.getRole());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try (PreparedStatement pst = this.conn.prepareStatement("UPDATE musicbox_roles SET role = ? WHERE id = ?")) {
            pst.setString(1, role.getRole());
            pst.setInt(2, role.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Role role) {
        int id = role.getId();
        if (!this.roleIsUsed(id)) {
            try (PreparedStatement pst = this.conn.prepareStatement("DELETE FROM musicbox_roles WHERE id = ?")) {
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //пример возможной реализации
    @Override
    public List<String[]> getRoleRelatedUsers(Role role) {
        List<String[]> list = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement("SELECT role, login, password, address FROM musicbox_roles "
                                                                    + "LEFT JOIN users ON roles.id=users.role_id "
                                                                    + "LEFT JOIN addresses ON users.address_id=addresses.id "
                                                                    + "WHERE roles.id = ?;")) {
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String[] result = new String[4];
                result[0] = rs.getString("role");
                result[1] = rs.getString("login");
                result[2] = rs.getString("password");
                result[3] = rs.getString("address");
                list.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Checks if role is used by users.
     * @param role role.
     * @return true, if used.
     */
    private boolean roleIsUsed(int role) {
        boolean result = false;

        try (PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM musicbox_users WHERE role_id = ?")) {
            pst.setInt(1, role);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

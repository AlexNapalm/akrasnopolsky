package ru.job4j.ioc.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Database communication class.
 */
public class JdbcStorage implements IStorageDao<User> {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Gets user by id.
     * @param id id.
     * @return user.
     */
    @Override
    public User getById(int id) {
        String sql = "select * from users_spr where id = ?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    /**
     * Gets all users.
     * @return list of users.
     */
    @Override
    public List<User> getAll() {
        String sql = "select * from users_spr";
        return jdbcTemplateObject.query(sql, new UserMapper());
    }

    /**
     * Adds user.
     * @param user user to add.
     */
    @Override
    public void add(User user) {
        String sql = "insert into users_spr (name, email) values (?, ?)";
        this.jdbcTemplateObject.update(sql, user.getName(), user.getEmail());
    }

    /**
     * Updates user.
     * @param model user to update.
     */
    @Override
    public void update(User model) {
        String sql = "update users_spr set name = ?, email = ? where id = ?";
        this.jdbcTemplateObject.update(sql, model.getName(), model.getEmail(), model.getId());
    }

    /**
     * Deletes user.
     * @param id id of user to delete.
     */
    @Override
    public void delete(int id) {
        String sql = "delete from users_spr where id = ?";
        this.jdbcTemplateObject.update(sql, id);
    }
}

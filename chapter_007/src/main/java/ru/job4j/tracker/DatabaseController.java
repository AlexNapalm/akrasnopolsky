package ru.job4j.tracker;

import java.sql.*;

public class DatabaseController {
    private String url = "jdbc:postgresql://localhost:5432/java_a_to_z";
    private String username = "postgres";
    private String password = "postgres";
    private Connection conn;

    /**
     * Creates tables if they don't exist.
     */
    public void initTables() {
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = this.conn.prepareStatement(
                        "CREATE TABLE if NOT EXISTS roles(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\ttitle CHARACTER VARYING (2000)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS rules(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\ttitle CHARACTER VARYING (2000)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS user_rights(\n"
                                + "\trole_id INTEGER REFERENCES roles(id),\n"
                                + "\trule_id INTEGER REFERENCES rules(id)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS users(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\trole_id INTEGER REFERENCES roles(id),\n"
                                + "\tlogin CHARACTER VARYING (2000),\n"
                                + "\tpassword CHARACTER VARYING (2000),\n"
                                + "\tcreate_date TIMESTAMP\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS categories(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\ttitle CHARACTER VARYING(2000)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if not EXISTS states(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\ttitle CHARACTER VARYING(2000)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS items(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\tstate_id INTEGER REFERENCES states(id),\n"
                                + "\tcategory_id INTEGER REFERENCES categories(id),\n"
                                + "\ttitle CHARACTER VARYING (2000),\n"
                                + "\tdescription TEXT,\n"
                                + "\tupdate_date TIMESTAMP,\n"
                                + "\tuser_id INTEGER REFERENCES users(id)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS attachs(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\tupload_date TIMESTAMP,\n"
                                + "\titem_id INTEGER REFERENCES items(id)\n"
                                + ");\n"
                                + "\n"
                                + "CREATE TABLE if NOT EXISTS comments(\n"
                                + "\tid SERIAL PRIMARY KEY,\n"
                                + "\titem_id INTEGER REFERENCES items(id),\n"
                                + "\tcreate_date TIMESTAMP\n"
                                + ");");
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fills the tables with default values.
     */
    public void fillTables() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = this.conn.prepareStatement("INSERT INTO roles(id, title) VALUES\n"
                                                                    + "(1, 'admin'),\n"
                                                                    + "(2, 'user'),\n"
                                                                    + "(3, 'analyst'),\n"
                                                                    + "(4, 'guest'),\n"
                                                                    + "(5, 'manager')\n"
                                                                    + "ON CONFLICT (id) DO NOTHING;\n"
                                                                    + "\n"
                                                                    + "INSERT INTO rules(id, title) VALUES\n"
                                                                    + "(1, 'read'),\n"
                                                                    + "(2, 'readwrite'),\n"
                                                                    + "(3, 'nodelete'),\n"
                                                                    + "(4, 'fullaccess')\n"
                                                                    + "ON CONFLICT (id) DO NOTHING;\n"
                                                                    + "\n"
                                                                    + "INSERT INTO states(id, title) VALUES\n"
                                                                    + "(1, 'New'),\n"
                                                                    + "(2, 'In work'),\n"
                                                                    + "(3, 'Paused'),\n"
                                                                    + "(4, 'Completed'),\n"
                                                                    + "(5, 'Closed')\n"
                                                                    + "ON CONFLICT (id) DO NOTHING;\n"
                                                                    + "\n"
                                                                    + "INSERT INTO categories(id, title) VALUES\n"
                                                                    + "(1, 'Desktop'),\n"
                                                                    + "(2, 'Printing'),\n"
                                                                    + "(3, 'Avaya'),\n"
                                                                    + "(4, 'Software'),\n"
                                                                    + "(5, 'LAN/WAN')\n"
                                                                    + "ON CONFLICT (id) DO NOTHING;\n"
                                                                    + "\n"
                                                                    + "INSERT INTO users(id, role_id, login, password, create_date) VALUES\n"
                                                                    + "(1, 1, 'akrasnopolsky', 'password', '1999-01-08 04:05:06'),  --админ\\n\" +\n"
                                                                    + "(2, 2, 'aivanov', 'password', '1999-01-08 04:05:06'), --юзер\\n\" +\n"
                                                                    + "(3, 2, 'spetrov', 'password', '1999-01-08 04:05:06'), --юзер\\n\" +\n"
                                                                    + "(4, 2, 'msidorov', 'password', '1999-01-08 04:05:06'), --юзер\\n\" +\n"
                                                                    + "(5, 5, 'dbuchenkov', 'password', '1999-01-08 04:05:06'), --менеджер\\n\" +\n"
                                                                    + "(6, 4, 'agorlov', 'password', '1999-01-08 04:05:06') -- аналитик\\\n"
                                                                    + "ON CONFLICT (id) DO NOTHING;");
           pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds item to database.
     * @param title title.
     * @param description description.
     * @return generated id.
     */
    public int addItem(String title, String description) {
        int id = 0;
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("INSERT into items(title, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, title);
            pst.setString(2, description);
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    /**
     * Updates item.
     * @param id id.
     * @param title new title.
     * @param description new description.
     */
    public void updateItem(int id, String title, String description) {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("update items set title = (?), description = (?) where id = (?)");
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setInt(3, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes item.
     * @param id id.
     */
    public void deleteItem(int id) {
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("delete from items where id = (?)");
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns item by id.
     * @param id id.
     * @return item.
     */
    public ResultSet findById(int id) {
        ResultSet result = null;
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("select * from items where id = (?)");
            pst.setInt(1, id);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Returns item by title.
     * @param key key for searching.
     * @return item.
     */
    public ResultSet findByTitle(String key) {
        ResultSet result = null;
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("select * from items where title = (?)");
            pst.setString(1, key);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Returns all items.
     * @return items.
     */
    public ResultSet findAllItems() {
        ResultSet result = null;
        try {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement pst = conn.prepareStatement("select * from items");
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

package ru.job4j.tracker;

import java.io.*;
import java.sql.*;

public class DatabaseController {
    private String url = "jdbc:postgresql://localhost:5432/java_a_to_z";
    private String username = "postgres";
    private String password = "postgres";

    /**
     * Executes SQL Script.
     */
    public void execSqlScript(String path) {
        try (Connection conn =
                     DriverManager.getConnection(url, username, password);
             FileReader fr =
                     new FileReader(new File(path));
             BufferedReader br =
                     new BufferedReader(fr)) {

            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            String[] inst = sb.toString().split(";");
            PreparedStatement pst;
            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    pst = conn.prepareStatement(inst[i]);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
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
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
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
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pst = conn.prepareStatement("update items set title = (?), description = (?) where id = (?)");
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setInt(3, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes item.
     * @param id id.
     */
    public void deleteItem(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pst = conn.prepareStatement("delete from items where id = (?)");
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns item by id.
     * @param id id.
     * @return item.
     */
    public ResultSet findById(int id) {
        ResultSet result = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pst = conn.prepareStatement("select * from items where id = (?)");
            pst.setInt(1, id);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pst = conn.prepareStatement("select * from items where title = (?)");
            pst.setString(1, key);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns all items.
     * @return items.
     */
    public ResultSet findAllItems() {
        ResultSet result = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement pst = conn.prepareStatement("select * from items");
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

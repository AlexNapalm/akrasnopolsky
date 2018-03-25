package ru.job4j.jdbc;

import java.sql.*;

public class SQLStorage {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/java_a_to_z";
        String username = "postgres";
        String password = "postgres";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(String.format("%s %s", rs.getString("login"), rs.getTimestamp("create_date")));
            } rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

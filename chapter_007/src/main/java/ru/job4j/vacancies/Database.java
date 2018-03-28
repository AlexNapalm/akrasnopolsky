package ru.job4j.vacancies;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class Database {

    private static final Logger LOGGER = Logger.getLogger(Database.class);

    Connection conn;

    Properties properties;

    Database(Properties p) {
        this.properties = p;
        this.makeConnection();
    }

    /**
     * Connects to database.
     */
    private void makeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("url");
            this.conn = DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * Adds vacancy to database. Updates vacancy date, if it exists.
     * @param text text
     * @param link link
     * @param date date
     */
    public void addVacancy(String text, String link, String date) {

        this.createTable();
        String sql = "UPDATE vacancies SET date = ? WHERE link = ?;"
                    + "INSERT INTO vacancies (text, link, date)"
                    + "       SELECT ?, ?, ?"
                    + "       WHERE NOT EXISTS (SELECT 1 FROM vacancies WHERE link = ?);";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, date);
            pst.setString(2, link);
            pst.setString(3, text);
            pst.setString(4, link);
            pst.setString(5, date);
            pst.setString(6, link);
            pst.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * Creates table.
     */
    private void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS vacancies ("
                + "id SERIAL PRIMARY KEY,"
                + "text varchar(255),"
                + "href varchar(255),"
                + "date varchar(20)"
                + ")";

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}

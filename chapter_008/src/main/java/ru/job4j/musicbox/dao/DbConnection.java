package ru.job4j.musicbox.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton.
 */
public enum DbConnection {

    INSTANCE;
    ComboPooledDataSource cpds;
    private static final Logger LOGGER = Logger.getLogger(DbConnection.class);

    DbConnection() {
        try {
            Properties properties = this.getProperties();
            String url = properties.getProperty("url");
            String username = properties.getProperty("user");
            String password = properties.getProperty("password");

            this.cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl(url);
            cpds.setUser(username);
            cpds.setPassword(password);
            cpds.setMaxPoolSize(20);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads properties for connection.
     * @return properties.
     */
    private Properties getProperties() {
        Properties properties = null;
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Reader reader = new InputStreamReader(is);
            properties = new Properties();
            properties.load(reader);

        }  catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return properties;
    }

    /**
     * Gets connection.
     * @return connection.
     */
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}

package ru.job4j.vacancies;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.*;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class);

    /**
     * Gets properties from the file.
     */
    public Properties getProperties() {
        Properties properties = null;
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream("parse.properties")) {
            Reader reader = new InputStreamReader(stream);
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return properties;
    }


    public static void main(String[] args) {
        Application app = new Application();
        Properties properties = app.getProperties();

        int launches = Integer.valueOf(properties.getProperty("launchesPerDay"));
        int period = (24 * 60 * 60 * 1000) / launches;

        TimerTask parser = new Parser(properties);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(parser, 0, period);
    }
}

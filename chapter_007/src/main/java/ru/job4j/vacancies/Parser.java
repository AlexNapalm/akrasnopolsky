package ru.job4j.vacancies;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.TimerTask;

public class Parser extends TimerTask {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);

    Properties properties;

    /**
     * Starting date.
     */
    String start;

    /**
     * Database to store data in.
     */
    Database db;

    Parser(Properties properties) {
        this.properties = properties;
        db = new Database(properties);
    }

    /**
     * Запуск парсинга вакансий.
     */
    public void run() {
        LOGGER.info("Start parsing");

        this.start = this.isLastParsingDate();
        this.parsePages();
        this.updateProperties();

        LOGGER.info("Parsing finished");
    }

    /**
     * Consistently parses each page.
     */
    private void parsePages() {
        int last = this.getLastPage();
        for (int i = 1; i <= last; i++) {
            if (!this.parseEachPage(i)) {
                break;
            }
        }
    }

    /**
     * Searches the last page of vacancies forum..
     * @return page number.
     */
    private Integer getLastPage() {
        Integer lastpage = 0;
        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers").get();
            Element table = document.select("table.sort_options").last();
            Element link = table.select("a").last();
            lastpage = Integer.valueOf(link.text());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return lastpage;
    }


    /**
     * Scans each page until found vacancy with publish date older than last launch.
     * @param number page number.
     * @return false if found vacancy older than compare date.
     */
    private boolean parseEachPage(int number) {
        Document document;
        try {
            document = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + number).get();
            Element table = document.selectFirst("table.forumTable");
            Elements els = table.select("tr");


            for (int i = 4; i < els.size(); i++) {

                String date = this.normalizeDate(els.get(i).select("td").last().text());
                if (this.isNewDate(date)) {
                    String text = els.get(i).select("td.postslisttopic").text();
                    if (this.isRelevantText(text)) {
                        String href = els.get(i).select("a").first().attr("href");
                        this.db.addVacancy(text, href, date);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Convert date to normal format if it is 'today' or 'yesterday'.
     * @param date vacancy date.
     * @return formatted date.
     */
    private String normalizeDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM YY", new Locale("ru"));
        if (date.contains("сегодня")) {
            String today = sdf.format(new Date());
            String time = date.substring(7, date.length());
            date = today + time;
        }

        if (date.contains("вчера")) {
            Date today = new Date();
            Long timing = today.getTime() - (24 * 60 * 60 * 1000);
            Date dayBefore = new Date(timing);
            String yesterday = sdf.format(dayBefore);
            String time = date.substring(5, date.length());
            date = yesterday + time;
        }
        return date;
    }


    /**
     * Checks if vacancy date is actual.
     * @param date vacancy date.
     * @return true if vacancy is new, false if vacancy hava been published before last launch.
     */
    private boolean isNewDate(String date) {
        boolean result = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru"));

            Date vacancyDate = format.parse(date);
            Date startDate = format.parse(this.start);

            if (startDate.after(vacancyDate)) {
                result = false;
            }

        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets last launch date.
     * @return last launch date or 01.01.18.
     */
    private String isLastParsingDate() {
        String date = properties.getProperty("lastDayLaunched");
        if (date.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            date = String.format("01 янв %s, 00:00", sdf.format(new Date()));
            System.out.println("startDate = " + date);
        }
        return date;
    }

    /**
     * Checks if title is not JavaScript or Java Script. If so - replaces it.
     * Then checks final string for 'java'
     * @param text
     * @return
     */
    private boolean isRelevantText(String text) {
        boolean relevant = false;

        String low = text.toLowerCase();
        String low1 = low.replace("javascript", "0");
        String low2 = low1.replace("java script", "0");

        if (low2.contains("java")) {
            relevant = true;
        }
        return relevant;
    }

    /**
     * Updates last launch date in properties file.
     */
    private void updateProperties() {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("chapter_007\\src\\main\\resources\\parse.properties"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru"));
            String today = sdf.format(new Date());

            properties.setProperty("lastDayLaunched", today);
            properties.store(writer, null);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}

package ru.job4j.sync;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

public class TextFinder implements Runnable {

    private String findText;
    private String filePath;
    private ParallelSearch ps;

    TextFinder(ParallelSearch ps, String text, String filePath) {
        this.ps = ps;
        this.findText = text;
        this.filePath = filePath;
    }

    public void run() {
        String string;
        try {
            LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(filePath)));
            while (true) {
                string = reader.readLine();
                if (string == null) {
                    break;
                }

                if (string.contains(findText)) {
                    ps.addResult(filePath);
                    break;
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

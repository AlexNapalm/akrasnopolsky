package ru.job4j.threads;

public class WordsCounter implements Runnable {
    /**
     * Text for word counter method.
     */
    private String text;

    public WordsCounter() {
    }

    public WordsCounter(String text) {
        this.text = text;
    }

    /**
     * Calculates number of words in the text.
     * @param text text.
     * @return number of words.
     */
    public int wordCounter(String text) {
        int result = 0;
        char[] chars = text.toCharArray();
        if (chars[0] != '\u0020') {
            result++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\u0020') {
                result++;
            }
        }
        return result;
    }

    @Override
    public void run() {
        System.out.println(wordCounter(this.text) + " words in the text");
    }

    public static void main(String[] args) {
        String source = "some text to demonstrate threads";

        System.out.println("start");
        new Thread(new SpaceCounter(source)).start();
        new Thread(new WordsCounter(source)).start();
        System.out.println("finish");

    }
}

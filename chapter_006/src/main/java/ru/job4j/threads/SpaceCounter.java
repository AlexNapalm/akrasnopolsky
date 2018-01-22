package ru.job4j.threads;

public class SpaceCounter  implements Runnable {
    /**
     * Text for word counter method.
     */
    private String text;

    public SpaceCounter() {
    }

    public SpaceCounter(String text) {
        this.text = text;
    }

    /**
     * Calculates number of spaces in the text.
     * @param text text.
     * @return number of spaces.
     */
    public int spaceCounter(String text) {
        int result = 0;
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\u0020') {
                result++;
            }
        }
        return result;
    }

    @Override
    public void run() {
        System.out.println(spaceCounter(this.text) + " spaces in the text");
    }
}

package ru.job4j.threads;

public class KillThread {

    static class Time implements Runnable {
        /**
         * Timeout millis.
         */
        private long timeOut;
        /**
         * Thread.
         */
        private Thread incomingThread;

        /**
         * Constructs Time class. instance
         * @param timeOut timeout millis.
         * @param thread thread.
         */
        Time(long timeOut, Thread thread) {
            this.timeOut = System.currentTimeMillis() + timeOut;
            this.incomingThread = thread;
        }

        @Override
        public void run() {
            incomingThread.start();
            System.out.println("timer started");
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!incomingThread.isAlive()) {
                    System.out.println("timer stopped");
                    break;
                }
                if (this.timeOut < System.currentTimeMillis()) {
                    System.out.println("timed out");
                    incomingThread.interrupt();
                    break;
                }
            }
        }
    }

    static class CountChar implements Runnable {
        /**
         * String to count chars in.
         */
        private String text;

        /**
         * Constructs CountChar class instance to count chars in the string.
         * @param text string.
         */
        public CountChar(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            int result = 0;
            char[] chars = this.text.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != ' ') {
                    result++;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("thread is interrupted");
                    return;
                }
            }
            System.out.println(String.format("Chars in the text: %s", result));
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        String string = "Test text to learn threads";
        new Thread(new Time(10, new Thread(new CountChar(string)))).start();
        System.out.println("end");
    }
}

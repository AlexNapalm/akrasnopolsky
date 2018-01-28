package ru.job4j.jmm;

public class MemoryModel {
    /**
     * Class field.
     */
    private int value = 0;

    /**
     * Constructs MemoryModel instance.
     * @param value value.
     */
    public MemoryModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        MemoryModel mm = new MemoryModel(10);
        // First thread raises value by 10.
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                int threadValue = mm.getValue();
                System.out.println(String.format("Thread 1 got value %s", threadValue));
                mm.setValue(threadValue + 10);
                System.out.println("Thread 1 raised MM value by 10");
            }
        };
        // Second thread decreases value by 10.
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int threadValue = mm.getValue();
                System.out.println(String.format("Thread 2 got value %s", threadValue));
                mm.setValue(threadValue - 10);
                System.out.println("Thread 2 decreased MM value by 10");
            }
        };
        // In the end value must remain 10 (10 + 10 - 10)
        System.out.println("Start program");
        System.out.println(String.format("MM value is %s", mm.getValue()));

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish program");
        System.out.println(String.format("MM value is %s", mm.getValue()));
    }
}

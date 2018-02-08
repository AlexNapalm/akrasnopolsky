package ru.job4j.waitnotify;

public class Work {

    public void fill() {
        try {
            Thread.sleep(2000);
            System.out.println("Work is completed by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(String.format("Work taken by %s has been interrupted", Thread.currentThread().getName()));
        }
    }
}

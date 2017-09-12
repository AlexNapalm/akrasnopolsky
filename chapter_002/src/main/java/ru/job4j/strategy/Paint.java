package ru.job4j.strategy;

/**
 * Класс для прорисовки фигур.
 */
public class Paint {
    /**
     * Прорисовка фигуры.
     * @param shape объект, реализующий интерфейс Shape.
     */
    public void draw(Shape shape) {
        System.out.println(shape.pic());
    }
}

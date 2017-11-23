package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования класса Triangle.
 */
public class TriangleTest {
    /**
     * Тест метода validate(), когда по заданным точкам возможно построить треугольник.
     */
    @Test
    public void whenGivenPointsPossibleConstructTriangle() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);

        boolean actual = triangle.validate();
        assertThat(actual, is(true));
    }

    /**
     * Тест метода area(), когда по заданным точкам можно построить треугольник и вычислить его площадь.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода area(), когда по заданным точкам невозможно построить треугольник и вычислить его площадь.
     */
    @Test
    public void whenGivenVertexesImpossibleConstructTriangleThenAreaZero() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        Point c = new Point(0, 2);
        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();
        assertThat(result, is(0D));
    }
}

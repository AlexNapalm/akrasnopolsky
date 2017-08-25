package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Substring.
 */
public class SubstringTest {
    /**
     * Тест для метода contains, когда строка содержит подстроку.
     */
    @Test
    public void whenOriginStringContainsSubstringThenTrue() {
        Substring substring = new Substring();
        String origin = "Привет";
        String sub = "иве";
        boolean result = substring.contains(origin, sub);

        assertThat(result, is(true));
    }
    /**
     * Тест для метода contains, когда строка не содержит подстроку.
     */
    @Test
    public void whenOriginStringNotContainSubstringThenFalse() {
        Substring checkString = new Substring();
        String origin = "Привет";
        String sub = "ложь";
        boolean result = checkString.contains(origin, sub);

        assertThat(result, is(false));
    }
    /**
     * Тест для метода contains, когда подстрока больше строки.
     */
    @Test
    public void whenSubstringBiggerThanOriginThenFalse() {
        Substring checkString = new Substring();
        String origin = "Привет";
        String sub = "faaaaaaaaaaaaaaaalse";
        boolean result = checkString.contains(origin, sub);

        assertThat(result, is(false));
    }
}

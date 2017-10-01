package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестов класса CoffeeMachine.
 */
public class CoffeeMachineTest {
    /**
     * Тест метода giveChange, цена 87, плата 100.
     */
    @Test
    public void whenPrice87Pay100ThenChangeTenTwoOne() {
        CoffeeMachine cf = new CoffeeMachine();
        int[] result = cf.giveChange(87, 100);
        int[] expected = {10, 2, 1};
        assertThat(result, is(expected));
    }
    /**
     * Тест метода giveChange, цена 1, плата 20.
     */
    @Test
    public void whenPriceOnePayTwentyThenChangeTenFiveTwoTwo() {
        CoffeeMachine cf = new CoffeeMachine();
        int[] result = cf.giveChange(1, 20);
        int[] expected = {10, 5, 2, 2};
        assertThat(result, is(expected));
    }
    /**
     * Тест метода giveChange, цена 32, плата 100.
     */
    @Test
    public void whenPriceThirtyTwoPayOneHundredThenChangeTenTenTenTenTenTenFiveTwoOne() {
        CoffeeMachine cf = new CoffeeMachine();
        int[] result = cf.giveChange(32, 100);
        int[] expected = {10, 10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
    }
}

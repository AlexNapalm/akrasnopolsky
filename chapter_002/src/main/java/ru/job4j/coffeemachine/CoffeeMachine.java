package ru.job4j.coffeemachine;

/**
 * Класс описывающий автомат по продаже кофе.
 */
public class CoffeeMachine {
    /**
     * Монета номиналом 1.
     */
    private static final int COIN_ONE_RUB = 1;
    /**
     * Монета номиналом 2.
     */
    private static final int COIN_TWO_RUB = 2;
    /**
     * Монета номиналом 5.
     */
    private static final int COIN_FIVE_RUB = 5;
    /**
     * Монета номиналом 10.
     */
    private static final int COIN_TEN_RUB = 10;
    /**
     * Массив-"стопка" монет, выдаваемая в качестве сдачи.
     */
    private int[] changeCoins = new int[50];
    /**
     * Счетчик заполненности массива.
     */
    private int position = 0;

    /**
     * Реализация выдачи сдачи.
     * @param price цена товара.
     * @param pay плата, внесенная покупателем.
     * @return массив монет, выдаваемый для сдачи.
     */
    public int[] giveChange(int price, int pay) {
        int change = pay - price; // разница между платой и ценой => сдача
        while (change >= COIN_TEN_RUB) {
            changeCoins[position++] = COIN_TEN_RUB;
            change -= COIN_TEN_RUB;
        }
        while (change >= COIN_FIVE_RUB) {
            changeCoins[position++] = COIN_FIVE_RUB;
            change -= COIN_FIVE_RUB;
        }
        while (change >= COIN_TWO_RUB) {
            changeCoins[position++] = COIN_TWO_RUB;
            change -= COIN_TWO_RUB;
        }
        while (change >= COIN_ONE_RUB) {
            changeCoins[position++] = COIN_ONE_RUB;
            change -= COIN_ONE_RUB;
        }
        int[] result = new int[position];
        System.arraycopy(changeCoins, 0, result, 0, position);
        return result;
    }
}

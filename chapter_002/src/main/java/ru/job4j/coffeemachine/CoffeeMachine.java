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
     * Массив номиналов монет, доступных для использования.
     */
    private static final int[] TYPES_OF_COINS = {COIN_TEN_RUB, COIN_FIVE_RUB, COIN_TWO_RUB, COIN_ONE_RUB};

    /**
     * Реализация выдачи сдачи.
     * @param price цена товара.
     * @param pay плата, внесенная покупателем.
     * @return массив монет, выдаваемый для сдачи.
     */
    public int[] giveChange(int price, int pay) {
        int change = pay - price; // разница между платой и ценой => сдача
        for (int i = 0; i < TYPES_OF_COINS.length; i++) {
            while (change >= TYPES_OF_COINS[i]) {
                changeCoins[position++] = TYPES_OF_COINS[i];
                change -= TYPES_OF_COINS[i];
            }
        }
        int[] result = new int[position];
        System.arraycopy(changeCoins, 0, result, 0, position);
        return result;
    }
}
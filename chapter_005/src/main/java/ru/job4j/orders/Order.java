package ru.job4j.orders;

public class Order implements Comparable {

    private static final String SELL = "SELL";
    /**
     * operation SELL or BUY.
     */
    private String operation;
    /**
     * price.
     */
    private double price;
    /**
     * volume.
     */
    private int volume;
    /**
     * order id.
     */
    private int id;

    /**
     * Constructs order.
     * @param operation operation.
     * @param price price.
     * @param volume volume.
     * @param id id.
     */
    public Order(String operation, double price, int volume, int id) {
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    public String getOperation() {
        return this.operation;
    }

    public double getPrice() {
        return this.price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getId() {
        return this.id;
    }

    public int getMinVolume(Order oppositeOrder) {
        return Math.min(this.volume, oppositeOrder.getVolume());
    }

    /**
     * Matches orders by price.
     * @param oppositeOrder the second order.
     * @return
     */
    public boolean match(Order oppositeOrder) {
        if (this.price == oppositeOrder.getPrice()) {
            return true;
        } else {
            return SELL.equals(this.operation) ^ this.price > oppositeOrder.getPrice();
        }
    }

    /**
     * Processes orders.
     * @param oppositeOrder the second order.
     * @return the resulting action needed.
     */
    public ExecutionResult process(Order oppositeOrder) {
        int minVolume = getMinVolume(oppositeOrder);
        int oppositeVolume = oppositeOrder.getVolume();
        if (this.volume > oppositeVolume) {
            setVolume(minVolume);
            return ExecutionResult.RemoveAdjustAdd;
        } else if (this.volume < oppositeVolume) {
            oppositeOrder.setVolume(minVolume);
            return ExecutionResult.Adjust;
        } else {
            return ExecutionResult.Remove;
        }
    }

    /**
     * prints the order information.
     */
    public void print() {
        System.out.print(this.volume + "@" + this.price + "   \t");
    }

    @Override
    public String toString() {
        return this.volume + "@" + this.price + " ";
    }

    /**
     * Sorts sell. orders in incremental order and buy orders in decremental.
     * @param o object to compare with.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Order order = (Order) o;
        if (this.id == order.getId()) {
            return 0;
        } else if (SELL.equals(this.operation)) {
            if (this.price > order.getPrice()) {
                return 1;
            } else if (order.getPrice() > this.price) {
                return -1;
            }
        } else {
            if (this.price > order.getPrice()) {
                return -1;
            } else if (order.getPrice() > this.price) {
                return 1;
            }
        }
        return -1;
    }
}

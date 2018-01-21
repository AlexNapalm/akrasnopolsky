package ru.job4j.orders;

import java.util.*;

enum ExecutionResult {
    Adjust, RemoveAdjustAdd, Remove, Add
}

public class OrderBook {
    /**
     * Unsorted BUY orders.
     */
    private Map<Integer, Order> buys = new HashMap<>();
    /**
     * Unsorted SELL orders.
     */
    private Map<Integer, Order> sells = new HashMap<>();

    private Set<Order> sortedBuys = new TreeSet<>();
    private Set<Order> sortedSells = new TreeSet<>();

    /**
     * Adds order.
     * @param id order id.
     * @param order order.
     */
    public void addOrder(int id, Order order) {
        if (order.getOperation().equals("BUY")) {
            execute(id, order, sortedSells, sortedBuys, buys, sells);
        } else {
            execute(id, order, sortedBuys, sortedSells, sells, buys);
        }
    }

    /**
     * Deletes order.
     * @param id order id.
     */
    public void deleteOrder(int id) {
        if (buys.containsKey(id)) {
            Order order = buys.remove(id);
            sortedBuys.remove(order);
        } else if (sells.containsKey(id)) {
            Order order = sells.remove(id);
            sortedSells.remove(order);
        }
    }

    /**
     *
     * @param orderId order id.
     * @param order order.
     * @param oppositeSet oppositeSet
     * @param setToAdd setToAdd
     * @param mapToAdd mapToAdd
     * @param mapToRemove mapToRemove
     */
    private void execute(int orderId, Order order,
                         Set<Order> oppositeSet,
                         Set<Order> setToAdd,
                         Map<Integer, Order> mapToAdd,
                         Map<Integer, Order> mapToRemove) {
        ExecutionResult result = match(order, oppositeSet, mapToRemove);
        if (result == ExecutionResult.RemoveAdjustAdd || result == ExecutionResult.Add) {
            mapToAdd.put(orderId, order);
            setToAdd.add(order);
        }
    }

    /**
     * Matches incoming order with orders with higher price from opposite collection
     * @param order order.
     * @param oppositeSet oppositeSet
     * @param oppositeMap oppositeMap
     * @return resulting action.
     */
    private ExecutionResult match(Order order, Set<Order> oppositeSet, Map<Integer, Order> oppositeMap) {
        for (Iterator<Order> iterator = oppositeSet.iterator(); iterator.hasNext();) {
            Order counterOrder = iterator.next();
            if (order.match(counterOrder)) {
                ExecutionResult result = order.process(counterOrder);
                switch (result) {
                    case RemoveAdjustAdd:
                    case Remove: iterator.remove();
                        oppositeMap.remove(counterOrder.getId());
                        return result;
                    case Adjust: return result;
                    default: return result;

                }
            } else {
                break;
            }
        }
        return ExecutionResult.Add;
    }

    /**
     * Prints order book by one element from both buy and ask collections.
     */
    public void print() {
        Iterator<Order> buyIterator = sortedBuys.iterator();
        Iterator<Order> sellIterator = sortedSells.iterator();
        boolean buyHasElements = true;
        boolean sellHasElements = true;
        boolean printBuy = true;
        double buyPrice = 0;
        double sellPrice = 0;

        while (buyHasElements || sellHasElements) {
            if (printBuy) {
                if (buyIterator.hasNext()) {
                    Order order = buyIterator.next();
                    double currentPrice = order.getPrice();
                    if (currentPrice != buyPrice) {
                        order.print();
                        buyPrice = currentPrice;
                        printBuy = false;
                    } else {
                        continue;
                    }
                } else {
                    printBuy = false;
                    buyHasElements = false;
                    System.out.print("-------\t\t");
                }
            } else {
                if (sellIterator.hasNext()) {
                    Order order = sellIterator.next();
                    double currentPrice = order.getPrice();
                    if (currentPrice != sellPrice) {
                        order.print();
                        System.out.println();
                        sellPrice = currentPrice;
                        printBuy = true;
                    }
                } else {
                    System.out.println("-------\t\t");
                    printBuy = true;
                    sellHasElements = false;
                }
            }
        }
    }
}

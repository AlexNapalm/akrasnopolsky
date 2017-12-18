package ru.job4j.list;

public class CycledLinkedList<T> {

    /**
     * Determines if linked list is cycled.
     * @param first first element of the list.
     * @return true, if list is cycled.
     */
    boolean hasCycle(Node first) {
        Node<T> slow = first;
        Node<T> fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Node.
 * @param <T> type.
 */
class Node<T> {
    private T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}

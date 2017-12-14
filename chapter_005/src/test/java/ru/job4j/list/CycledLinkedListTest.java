package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CycledLinkedListTest {

    @Test
    public void whenHasCycleReturnTrue() {
        CycledLinkedList<Integer> cll = new CycledLinkedList<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        Node<Integer> fifth = new Node<>(5);
        Node<Integer> sixth = new Node<>(6);
        Node<Integer> seventh = new Node<>(7);
        Node<Integer> eight = new Node<>(8);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = first;
        seventh.next = eight;
        boolean result = cll.hasCycle(first);

        assertThat(result, is(true));
    }

    @Test
    public void whenHasNoCycleReturnFalse() {
        CycledLinkedList<Integer> cll = new CycledLinkedList<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        boolean result = cll.hasCycle(first);

        assertThat(result, is(false));
    }

}
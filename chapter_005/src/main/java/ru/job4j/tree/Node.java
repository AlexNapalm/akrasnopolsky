package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

public class Node<E> {

    private List<Node<E>> children = new ArrayList<>();
    private E value;

    public Node(E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> children() {
        return this.children;
    }

    public boolean eqValue(E value) {
        return this.value.equals(value);
    }

    public E getValue() {
        return this.value;
    }
}

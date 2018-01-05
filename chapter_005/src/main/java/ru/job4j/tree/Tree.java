package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int size = 0;

    /**
     * Constructs tree.
     * @param value root of the tree.
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }
    /**
     * adds child to parent.
     * Parent can have list of children.
     * @param parent parent.
     * @param child child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.getValue().compareTo(parent) == 0) {
                for (Node<E> leaf : element.children()) {
                    if (leaf.eqValue(child)) {
                        return false;
                    }
                }
                element.add(new Node<>(child));
                size++;
                result = true;
                break;
            }
            for (Node<E> leaf : element.children()) {
                data.offer(leaf);
            }
        }
        return result;
    }
    /**
     * Finds Node by value.
     * @param value value.
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.children()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Converts tree to list.
     * @return list representation of tree.
     */
    private Queue<Node<E>> treeToList() {
        Queue<Node<E>> data = new LinkedList<>();
        Queue<Node<E>> result = new LinkedList<>();
        data.offer(this.root);
        result.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            for (Node<E> child : element.children()) {
                data.offer(child);
                result.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Queue<Node<E>> allElements = treeToList();
            int count = size;

            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    count--;
                    return allElements.poll().getValue();
                } else {
                    throw  new NoSuchElementException();
                }
            }
        };
    }
}

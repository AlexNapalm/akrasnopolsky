package ru.job4j.tree;

import java.util.*;

public class MySimpleTree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Root of the tree.
     */
    private Node<E> root;

    /**
     * Adds child element to parent.
     * Parent can have list of children.
     * @param parent parent.
     * @param child child.
     * @return true, if element added, and false if not
     */
    @Override
    public boolean add(E parent, E child) {
        try {
            Node<E> node = new Node<E>(parent);
            if (root == null) {
                root = node;
                root.childen.add(new Node<E>(child));
                return true;
            } else insertRec(root, node, new Node<E>(child));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private void insertRec(Node<E> latestRoot, Node<E> parent, Node<E> child) {
        if (latestRoot.value.compareTo(parent.value) > 0) {
            if (latestRoot.left == null) {
                latestRoot.left = parent;
                latestRoot.left.childen.add(child);
            } else {
                insertRec(latestRoot.left, parent, child);
            }
        } else if (latestRoot.value.compareTo(parent.value) < 0) {
            if (latestRoot.right == null) {
                latestRoot.right = parent;
                latestRoot.right.childen.add(child);
            } else {
                insertRec(latestRoot.right, parent, child);
            }
        } else if (latestRoot.value.compareTo(parent.value) == 0) {
            latestRoot.childen.add(child);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Stack<Node<E>> stack = new Stack<>();
            private Node<E> current = root;

            @Override
            public boolean hasNext() {
                return (!stack.isEmpty() || current != null);
            }

            @Override
            public E next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                current = stack.pop();
                Node<E> node = current;
                current = current.right;

                return node.value;
            }
        };
    }

    class Node<E> {
        E value;
        Node<E> left, right;
        List<Node<E>> childen;

        /**
         * Constructor.
         * @param value - new value.
         */
        Node(E value) {
            this.value = value;
            left = right = null;
            childen = new LinkedList<>();
        }

        /**
         * Get value.
         * @return - parent.
         */
        public E getValue() {
            return this.value;
        }
    }
}

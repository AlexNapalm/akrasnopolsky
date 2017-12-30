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
        Node<E> node = new Node<E>(parent);
        if (root == null) {
            root = node;
            root.children.add(new Node<E>(child));
            return true;
        } else {
            insertRec(root, node, new Node<E>(child));
        }
        return true;
    }

    private boolean insertRec(Node<E> latestRoot, Node<E> parent, Node<E> child) {
        if (latestRoot.getValue().compareTo(parent.getValue()) == 0) {
            latestRoot.getChildren().add(child);
            return true;
        } else {
            for (Node<E> node : root.getChildren()) {
                if (parent.getValue().compareTo(node.getValue()) == 0) {
                    node.getChildren().add(child);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Checks if tree is binary or not.
     * @return true, if tree is binary, and false if not.
     */
    public boolean isBinary() {
        boolean result = true;
        if (root != null) {
            Node<E> node = root;
            if (!node.getChildren().isEmpty()) {
                for (Node<E> aChildren : node.getChildren()) {
                    if (aChildren.getChildren().size() > 2) {
                        result = false;
                        return result;
                    }
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Stack<E> stack = new Stack<>();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return (!stack.isEmpty() || root != null);
            }

            private void popInStack(Node<E> node) {
                stack.add(node.getValue());
                if (!node.getChildren().isEmpty()) {
                    for (Node<E> aChilden : node.getChildren()) {
                        popInStack(aChilden);
                    }
                }
                root = null;
            }

            @Override
            public E next() {
                if (index == 0)  {
                    popInStack(root);
                }
                index++;
                return stack.pop();
            }
        };
    }

    class Node<E> {

        private E value;
        private List<Node<E>> children;

        /**
         * Constructs new node.
         * @param value value.
         */
        Node(E value) {
            this.value = value;
            children = new LinkedList<>();
        }

        /**
         * Gets value.
         * @return - parent.
         */
        public E getValue() {
            return this.value;
        }

        /**
         * Gets list of children.
         * @return list of children.
         */
        public List<Node<E>> getChildren() {
            return this.children;
        }
    }
}

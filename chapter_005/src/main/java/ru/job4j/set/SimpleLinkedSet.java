package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedSet<E> implements Iterable<E> {
    /**
     * Start of the list.
     */
    private Node<E> firstNode;
    /**
     * End of the list.
     */
    private Node<E> lastNode;
    /**
     * Size of the container.
     */
    private int size = 0;

    /**
     * Constructs LinkedContainer.
     */
    public SimpleLinkedSet() {
        lastNode = new Node<E>(null, firstNode, null);
        firstNode = new Node<E>(null, null, lastNode);
    }

    public E getElementByIndex(int index) {
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < index; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    /**
     * Adds element to the end of the list.
     * @param e element to add.
     */
    public void add(E e) {
        Node<E> element = firstNode.getNextElement();
        for (int i = 0; i < size; i++) {
            E result = element.currentElement;
            if (e.equals(result)) {
                return;
            }
            element = element.nextElement;
        }
        Node<E> prev = lastNode;
        prev.setCurrentElement(e);
        lastNode = new Node<E>(null, prev, null);
        prev.setNextElement(lastNode);
        size++;
    }

    /**
     * Return the actual size of the list.
     * @return size.
     */
    public int size() {
        return size;
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    /**
     * Iterator from the first to last.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> element = firstNode.getNextElement();

            @Override
            public boolean hasNext() {
                return element != lastNode;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E result = element.currentElement;
                    element = element.nextElement;
                    return result;
                } else {
                    throw  new NoSuchElementException();
                }
            }
        };
    }

    private class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        private Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }
}

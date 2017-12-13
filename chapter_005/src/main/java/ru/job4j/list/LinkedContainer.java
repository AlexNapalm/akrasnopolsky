package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainer<E> implements Iterable<E> {
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
    public LinkedContainer() {
        lastNode = new Node<E>(null, firstNode, null);
        firstNode = new Node<E>(null, null, lastNode);
    }
    /**
     * Adds element to the end of the list.
     * @param e element to add.
     */
    public void addLast(E e) {
        Node<E> prev = lastNode;
        prev.setCurrentElement(e);
        lastNode = new Node<E>(null, prev, null);
        prev.setNextElement(lastNode);
        size++;
    }
    /**
     * Adds element to the beginning of the list.
     * @param e
     */
    public void addFirst(E e) {
        Node<E> next = firstNode;
        next.setCurrentElement(e);
        firstNode = new Node<E>(null, null, next);
        next.setPrevElement(firstNode);
        size++;

    }

    /**
     * Removes last element from container.
     */
    public void removeLast() {
        Node<E> last = lastNode.getPrevElement();
        Node<E> newLast = last.getPrevElement();
        last.setCurrentElement(null);
        newLast.setNextElement(lastNode);
        lastNode.setPrevElement(newLast);
        size--;
    }

    /**
     * Removes the first element from container.
     */
    public void removeFirst() {
        Node<E> first = firstNode.getNextElement();
        Node<E> newFirst = first.getNextElement();
        first.setCurrentElement(null);
        newFirst.setPrevElement(firstNode);
        firstNode.setNextElement(newFirst);
        size--;
    }

    /**
     * Return the actual size of the list.
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * Return the element by the index.
     * @param index index.
     * @return element.
     */
    public E getElementByIndex(int index) {
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < index; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
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
            int position = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return getElementByIndex(position++);
                } else {
                    throw  new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Iterator from last to the first.
     * @return
     */
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int position = size - 1;

            @Override
            public boolean hasNext() {
                return position >= 0;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return getElementByIndex(position--);
                } else {
                    throw new NoSuchElementException();
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

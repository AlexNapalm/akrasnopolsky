package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
@ThreadSafe
public class LinkedContainer<E> implements Iterable<E> {
    /**
     * Lock object for sync.
     */
    private final Object lock;

    /**
     * Start of the list.
     */
    @GuardedBy("lock")
    private Node<E> firstNode = new Node<E>(null, null, null);
    /**
     * End of the list.
     */
    @GuardedBy("lock")
    private Node<E> lastNode = new Node<E>(null, null, null);
    /**
     * Size of the container.
     */
    @GuardedBy("lock")
    private int size = 0;

    /**
     * Constructs LinkedContainer.
     */
    public LinkedContainer() {
        firstNode.setNextElement(lastNode);
        lastNode.setPrevElement(firstNode);
        this.lock = new Object();
    }
    /**
     * Adds element to the end of the list.
     * @param e element to add.
     */
    @GuardedBy("lock")
    public void addLast(E e) {
        synchronized (this.lock) {
            Node<E> prev = lastNode;
            prev.setCurrentElement(e);
            lastNode = new Node<E>(null, prev, null);
            prev.setNextElement(lastNode);
            size++;
        }
    }
    /**
     * Adds element to the beginning of the list.
     * @param e
     */
    @GuardedBy("lock")
    public void addFirst(E e) {
        synchronized (this.lock) {
            Node<E> next = firstNode;
            next.setCurrentElement(e);
            firstNode = new Node<E>(null, null, next);
            next.setPrevElement(firstNode);
            size++;
        }
    }

    /**
     * Removes last element from container.
     */
    @GuardedBy("lock")
    public void removeLast() {
        synchronized (this.lock) {
            Node<E> last = lastNode.getPrevElement();
            Node<E> newLast = last.getPrevElement();
            last.setCurrentElement(null);
            newLast.setNextElement(lastNode);
            lastNode.setPrevElement(newLast);
            size--;
        }
    }

    /**
     * Removes the first element from container.
     */
    @GuardedBy("lock")
    public void removeFirst() {
        synchronized (this.lock) {
            Node<E> first = firstNode.getNextElement();
            Node<E> newFirst = first.getNextElement();
            first.setCurrentElement(null);
            newFirst.setPrevElement(firstNode);
            firstNode.setNextElement(newFirst);
            size--;
        }
    }

    /**
     * Return the actual size of the list.
     * @return size.
     */
    @GuardedBy("lock")
    public int size() {
        synchronized (this.lock) {
            return size;
        }
    }

    /**
     * Return the element by the index.
     * @param index index.
     * @return element.
     */
    @GuardedBy("lock")
    public E getElementByIndex(int index) {
        synchronized (this.lock) {
            Node<E> target = firstNode.getNextElement();
            for (int i = 0; i < index; i++) {
                target = getNextElement(target);
            }
            return target.getCurrentElement();
        }
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    /**
     * Iterator from the first to last.
     * @return
     */
    @GuardedBy("lock")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @GuardedBy("lock")
            Node<E> element = firstNode.getNextElement();

            @GuardedBy("lock")
            @Override
            public boolean hasNext() {
                synchronized (lock) {
                    return element != lastNode;
                }
            }

            @GuardedBy("lock")
            @Override
            public E next() {
                synchronized (lock) {
                    if (hasNext()) {
                        E result = element.currentElement;
                        element = element.nextElement;
                        return result;
                    } else {
                        throw  new NoSuchElementException();
                    }
                }
            }
        };
    }

    /**
     * Iterator from last to the first.
     * @return
     */
    @GuardedBy("lock")
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            @GuardedBy("lock")
            int position = size - 1;
            @GuardedBy("lock")
            Node<E> element = lastNode.getPrevElement();
            @GuardedBy("lock")
            Node<E> prev = lastNode.getPrevElement();

            @GuardedBy("lock")
            @Override
            public boolean hasNext() {
                synchronized (lock) {
                    return position >= 0;
                }
            }

            @GuardedBy("lock")
            @Override
            public E next() {
                synchronized (lock) {
                    if (hasNext()) {
                        element = prev;
                        prev = prev.getPrevElement();
                        position--;
                        return element.getCurrentElement();
                    } else {
                        throw new NoSuchElementException();
                    }
                }
            }
        };
    }
    @ThreadSafe
    private class Node<E> {
        private final Object lock;
        @GuardedBy("lock")
        private E currentElement;
        @GuardedBy("lock")
        private Node<E> nextElement;
        @GuardedBy("lock")
        private Node<E> prevElement;

        private Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
            this.lock = new Object();
        }
        @GuardedBy("lock")
        public E getCurrentElement() {
            synchronized (this.lock) {
                return currentElement;
            }
        }
        @GuardedBy("lock")
        public void setCurrentElement(E currentElement) {
            synchronized (this.lock) {
                this.currentElement = currentElement;
            }
        }
        @GuardedBy("lock")
        public Node<E> getNextElement() {
            synchronized (this.lock) {
                return nextElement;
            }
        }
        @GuardedBy("lock")
        public void setNextElement(Node<E> nextElement) {
            synchronized (this.lock) {
                this.nextElement = nextElement;
            }
        }
        @GuardedBy("lock")
        public Node<E> getPrevElement() {
            synchronized (this.lock) {
                return prevElement;
            }
        }
        @GuardedBy("lock")
        public void setPrevElement(Node<E> prevElement) {
            synchronized (this.lock) {
                this.prevElement = prevElement;
            }
        }
    }
}

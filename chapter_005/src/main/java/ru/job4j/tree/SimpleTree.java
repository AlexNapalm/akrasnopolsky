package ru.job4j.tree;

import java.util.Optional;

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Adds child element to parent.
     * Parent can have list of children.
     * @param parent parent.
     * @param child child.
     * @return true, if element added, and false if not
     */
    boolean add(E parent, E child);
}

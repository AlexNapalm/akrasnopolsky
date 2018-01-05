package ru.job4j.tree;

import java.util.Optional;

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * adds child to parent.
     * Parent can have list of children.
     * @param parent parent.
     * @param child child.
     * @return
     */
    boolean add(E parent, E child);

    /**
     * Finds Node by value.
     * @param value value.
     * @return
     */
    Optional<Node<E>> findBy(E value);
}

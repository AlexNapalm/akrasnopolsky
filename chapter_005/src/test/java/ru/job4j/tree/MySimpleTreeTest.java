package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MySimpleTreeTest {

    private MySimpleTree<String> tree = new MySimpleTree<>();

    @Test
    public void whenAddParentAndHisChild() {
        tree.add("B", "b");
        tree.add("A", "a");
        tree.add("C", "c1");
        boolean result = tree.add("C", "c2");
        assertThat(result, is(true));
    }

    @Test
    public void whenTreeIsBinaryThenReturnTrue() {
        tree.add("A", "a");
        tree.add("A", "b");
        boolean result = tree.isBinary();
        assertThat(result, is(true));
    }

    @Test
    public void whenTreeIsNotBinaryThenReturnFalse() {
        tree.add("A", "B");
        tree.add("A", "C");
        tree.add("B", "D");
        tree.add("B", "E");
        tree.add("B", "F");
        tree.add("B", "G");
        boolean result = tree.isBinary();
        assertThat(result, is(false));
    }
}
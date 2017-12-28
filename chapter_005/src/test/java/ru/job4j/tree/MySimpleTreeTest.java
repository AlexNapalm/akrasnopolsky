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
        assertTrue(result);
        assertThat(tree.add("C", "c2"), is(true));
    }
}
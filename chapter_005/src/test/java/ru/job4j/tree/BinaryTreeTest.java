package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    private int root = 3;
    private int child = 1;
    private int child2 = 2;
    private int child3 = 4;
    private int child4 = 5;

    @Test
    public void whenAddOneElementThenExpectedResult() {
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        int expected = 1;

        assertThat(tree.getSize(), is(expected));
        assertThat(tree.contains(root), is(true));
    }

    @Test
    public void whenAddDuplicatesThenExpectedResult() {
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        int expected = 1;

        assertThat(tree.add(root), is(false));
        assertThat(tree.getSize(), is(expected));
        assertThat(tree.contains(root), is(true));
    }

    @Test
    public void whenAddElementsThenExpectedResult() {
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        int expected = 5;

        assertThat(tree.add(child), is(true));
        assertThat(tree.add(child2), is(true));
        assertThat(tree.add(child3), is(true));
        assertThat(tree.add(child4), is(true));
        assertThat(tree.add(child), is(false));
        assertThat(tree.add(child2), is(false));
        assertThat(tree.add(child3), is(false));
        assertThat(tree.add(child4), is(false));
        assertThat(tree.contains(root), is(true));
        assertThat(tree.contains(child), is(true));
        assertThat(tree.contains(child2), is(true));
        assertThat(tree.contains(child3), is(true));
        assertThat(tree.contains(child4), is(true));
        assertThat(tree.getSize(), is(expected));
    }
}
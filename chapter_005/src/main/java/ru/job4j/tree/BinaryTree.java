package ru.job4j.tree;

public class BinaryTree<E extends Comparable<E>> {
    /**
     * Root of the tree.
     */
    private BinaryTreeNode<E> root;
    /**
     * Number of elements.
     */
    private int size = 0;
    /**
     * Constructs tree.
     * @param value value of the root.
     */
    public BinaryTree(E value) {
        this.root = new BinaryTreeNode<E>(value);
        this.size++;
    }
    /**
     * Adds element.
     * @param e element to add.
     * @return
     */
    public boolean add(E e) {
        boolean result = false;
        if (!contains(e)) {
            searchNodeForAdd(this.root, e);
            this.size++;
            result = true;
        }
        return result;
    }
    /**
     * Searches value in the tree.
     * @param startSearch node as start of search.
     * @param searchValue value to search
     * @return value or null.
     */
    private BinaryTreeNode<E> searchValue(BinaryTreeNode<E> startSearch, E searchValue) {
        BinaryTreeNode<E> result = null;
        int compareResult = searchValue.compareTo(startSearch.getValue());
        if (compareResult == 0) {
            result = startSearch;
        } else if (compareResult < 0 && startSearch.getLeft() != null) {
            result = searchValue(startSearch.getLeft(), searchValue);
        } else if (compareResult > 0 && startSearch.getRight() != null) {
            result = searchValue(startSearch.getRight(), searchValue);
        }
        return result;
    }
    /**
     * Searches node to add value and adds it..
     * @param startSearch node as start of search.
     * @param value value to add.
     */
    private void searchNodeForAdd(BinaryTreeNode<E> startSearch, E value) {
        BinaryTreeNode<E> node = new BinaryTreeNode<>(value);
        int compareResult = value.compareTo(startSearch.getValue());
        if (compareResult < 0) {
            if (startSearch.getLeft() == null) {
                startSearch.setLeft(node);
            } else {
                searchNodeForAdd(startSearch.getLeft(), value);
            }
        }
        if (compareResult > 0) {
            if (startSearch.getRight() == null) {
                startSearch.setRight(node);
            } else {
                searchNodeForAdd(startSearch.getRight(), value);
            }
        }
    }
    /**
     * Checks if tree contains value.
     * @param value value to check.
     * @return true if contains and false if not.
     */
    public boolean contains(E value) {
        return searchValue(this.root, value) != null;
    }
    /**
     * Gets size of the tree.
     * @return size.
     */
    public int getSize() {
        return this.size;
    }

    public class BinaryTreeNode<E extends Comparable<E>> {
        private E value;
        private BinaryTreeNode<E> left;
        private BinaryTreeNode<E> right;

        public BinaryTreeNode(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        public BinaryTreeNode<E> getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }
    }
}

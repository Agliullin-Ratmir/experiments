package org.example.structures;

public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public void display(String message, BinaryTreeNode node) {
        if (node != null) {
            System.out.println(message + node.getKey());
            display("Left child key: ", node.getLeftChild());
            display("Right child key: ", node.getRightChild());
        }
    }

    public void insert(int key, int value) {
        BinaryTreeNode node = new BinaryTreeNode(key, value);
        if (this.root == null) {
            this.root = node;
        } else {
            BinaryTreeNode current = this.root;
            BinaryTreeNode parent;
            while (true) {
                parent = current;
                if (key < current.getKey()) {
                    current = parent.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(node);
                        node.setParent(parent);
                        return;
                    }
                } else {
                    current = parent.getRightChild();
                    if (current == null) {
                        parent.setRightChild(node);
                        node.setParent(parent);
                        return;
                    }
                }
            }
        }
    }

    public BinaryTreeNode find(int key) {
        BinaryTreeNode current = this.root;
        if (current == null) {
            return null;
        }
        while (current != null && current.getKey() != key) {
            if (current.getKey() < key) {
                current = current.getRightChild();
            } else {
                current = current.getLeftChild();
            }
        }
        return current;
    }

    public void delete(int key) {
        BinaryTreeNode node = find(key);
        if (node == null) {
            return;
        }
        // when deleted node doesn't have children. Not better algorithm, but it's mine
        if (node.getLeftChild() == null &&
                node.getRightChild() == null) {
            if (node == this.root) {
                this.root = null;
            } else {
                BinaryTreeNode parent = node.getParent();
                if (parent.getKey() > node.getKey()) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
            }
        }
        // add removing with cne child p. 370
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50, 50);

        tree.insert(40, 40);
        tree.insert(30, 30);
        tree.insert(45, 45);

        tree.insert(60, 60);
        tree.insert(55, 55);
        tree.insert(65, 65);

        tree.display("Root: ", tree.getRoot());

        // delete node without children
        tree.delete(55);
        tree.display("Root, without leaf 55 without children: ", tree.getRoot());
    }
}

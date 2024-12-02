import java.util.ArrayList;
import java.util.Collections;

public class BTree {

    private static final int T = 3;  // Minimum degree (defines the range for number of keys in a node)
    private Node root;

    // Constructor for B-tree
    public BTree() {
        this.root = new Node(true);
    }

    // Node class
    private class Node {
        boolean isLeaf;
        ArrayList<Integer> keys;
        ArrayList<Node> children;

        Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }

    // Insert a key into the B-tree
    public void insert(int key) {
        Node root = this.root;
        if (root.keys.size() == 2 * T - 1) {
            Node newRoot = new Node(false);
            newRoot.children.add(this.root);
            splitChild(newRoot, 0);
            this.root = newRoot;
        }
        insertNonFull(this.root, key);
    }

    // Split a full child node
    private void splitChild(Node parent, int index) {
        Node fullChild = parent.children.get(index);
        Node newNode = new Node(fullChild.isLeaf);
        parent.keys.add(index, fullChild.keys.get(T - 1));
        parent.children.add(index + 1, newNode);

        newNode.keys.addAll(fullChild.keys.subList(T, 2 * T - 1));
        fullChild.keys.subList(T - 1, fullChild.keys.size()).clear();

        if (!fullChild.isLeaf) {
            newNode.children.addAll(fullChild.children.subList(T, fullChild.children.size()));
            fullChild.children.subList(T, fullChild.children.size()).clear();
        }
    }

    // Insert a key into a node that is not full
    private void insertNonFull(Node node, int key) {
        int i = node.keys.size() - 1;
        if (node.isLeaf) {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            node.keys.add(i + 1, key);
        } else {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            i++;
            Node childNode = node.children.get(i);
            if (childNode.keys.size() == 2 * T - 1) {
                splitChild(node, i);
                if (key > node.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    // Search for a key in the B-tree
    public boolean search(int key) {
        return search(root, key);
    }

    // Helper function to search within a node
    private boolean search(Node node, int key) {
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }
        if (i < node.keys.size() && key == node.keys.get(i)) {
            return true;
        } else if (node.isLeaf) {
            return false;
        } else {
            return search(node.children.get(i), key);
        }
    }

    // Print the B-tree structure
    public void printTree() {
        printNode(root, "", true);
    }

    private void printNode(Node node, String indent, boolean isLast) {
        System.out.println(indent + (isLast ? "+-- " : "|-- ") + node.keys);
        indent += isLast ? "    " : "|   ";
        for (int i = 0; i < node.children.size(); i++) {
            printNode(node.children.get(i), indent, i == node.children.size() - 1);
        }
    }

    // Main method to test the B-tree
    public static void main(String[] args) {
        BTree bTree = new BTree();

        // Insert keys into the B-tree
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        // Print the B-tree structure
        bTree.printTree();

        // Search for keys
        System.out.println("Search 10: " + bTree.search(10)); // True
        System.out.println("Search 15: " + bTree.search(15)); // False
    }
}

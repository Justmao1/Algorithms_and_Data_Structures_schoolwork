class RedBlackTree {
    // Node colors
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Node class representing each node in the tree
    private class Node {
        int data;
        Node parent;
        Node left, right;
        boolean color;

        // Constructor to create a new node
        Node(int data) {
            this.data = data;
            this.left = this.right = null;
            this.color = RED;  // New nodes are always red
        }
    }

    private Node root;

    public RedBlackTree() {
        root = null;
    }

    // Search for a key in the Red-Black Tree
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == null) return false;
        if (key == node.data) return true;
        if (key < node.data) return search(node.left, key);
        else return search(node.right, key);
    }

    // Rotate the tree left around a node
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    // Rotate the tree right around a node
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;

        y.right = x;
        x.parent = y;
    }

    // Fix the Red-Black Tree properties after insertion
    private void fixInsert(Node k) {
        Node u;
        while (k.parent != null && k.parent.color == RED) {
            if (k.parent == k.parent.parent.left) {
                u = k.parent.parent.right;
                if (u != null && u.color == RED) {
                    // Case 1: Uncle is red
                    k.parent.color = BLACK;
                    u.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    // Case 2: Uncle is black, and k is the right child
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    // Case 3: Uncle is black, and k is the left child
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.left;
                if (u != null && u.color == RED) {
                    // Case 1: Uncle is red
                    k.parent.color = BLACK;
                    u.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    // Case 2: Uncle is black, and k is the left child
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    // Case 3: Uncle is black, and k is the right child
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    // Insert a new key into the Red-Black Tree
    public void insert(int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            node.color = BLACK;
        } else {
            Node temp = root;
            Node parent = null;

            while (temp != null) {
                parent = temp;
                if (key < temp.data) temp = temp.left;
                else temp = temp.right;
            }

            node.parent = parent;
            if (key < parent.data) parent.left = node;
            else parent.right = node;

            fixInsert(node);
        }
    }

    // In-order traversal of the Red-Black Tree (to print the tree)
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " (" + (node.color == RED ? "R" : "B") + ") ");
            inorder(node.right);
        }
    }

    // Main method for testing the Red-Black Tree
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // Insert elements
        tree.insert(10);
        tree.insert(20);
        tree.insert(15);
        tree.insert(30);
        tree.insert(25);
        tree.insert(5);
        tree.insert(35);

        // In-order traversal to see the tree structure
        System.out.println("In-order traversal of the Red-Black Tree:");
        tree.inorder();

        // Search for a key
        System.out.println("\nSearch for 15: " + tree.search(15)); // Should return true
        System.out.println("Search for 100: " + tree.search(100)); // Should return false
    }
}

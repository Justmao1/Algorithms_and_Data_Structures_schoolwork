public class SplayTree {

    // Node structure
    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    private Node root;

    public SplayTree() {
        root = null;
    }

    // Right rotation
    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    // Left rotation
    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Splay operation to bring the node with the given key to the root
    private Node splay(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        // Key lies in left subtree
        if (key < root.key) {
            if (root.left == null) {
                return root;
            }

            // Zig-Zig (Left Left)
            if (key < root.left.key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            }
            // Zig-Zag (Left Right)
            else if (key > root.left.key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = leftRotate(root.left);
                }
            }

            return (root.left == null) ? root : rightRotate(root);
        }
        // Key lies in right subtree
        else {
            if (root.right == null) {
                return root;
            }

            // Zag-Zig (Right Left)
            if (key < root.right.key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rightRotate(root.right);
                }
            }
            // Zag-Zag (Right Right)
            else if (key > root.right.key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // Search the key in the splay tree and return true if found, false otherwise
    public boolean search(int key) {
        root = splay(root, key);
        return root != null && root.key == key;
    }

    // Insert key into the splay tree
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }

        // Splay the tree to the node where the key should be inserted
        root = splay(root, key);

        if (root.key == key) {
            return; // Key is already in the tree
        }

        Node newNode = new Node(key);

        // If key is smaller than root's key, insert the new node to the left
        if (key < root.key) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        }
        // If key is greater than root's key, insert the new node to the right
        else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
    }

    // Delete a key from the splay tree
    public void delete(int key) {
        if (root == null) {
            return;
        }

        // Splay the tree to the key
        root = splay(root, key);

        // If the key is not present, do nothing
        if (root.key != key) {
            return;
        }

        // If the key is present, remove it
        if (root.left == null) {
            root = root.right;
        } else {
            Node temp = root.right;
            root = root.left;
            splay(root, key);
            root.right = temp;
        }
    }

    // In-order traversal of the tree
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }

    public void inorder() {
        inorder(root);
    }
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        System.out.println("In-order traversal of the Splay Tree:");
        tree.inorder();  // Output: 10 20 30 40 50

        boolean found = tree.search(30);
        System.out.println("\nSearch result for 30: " + found); // Output: true

        found = tree.search(100);
        System.out.println("Search result for 100: " + found); // Output: false

        tree.delete(20);
        System.out.println("\nIn-order traversal after deleting 20:");
        tree.inorder();  // Output: 10 30 40 50
    }
}

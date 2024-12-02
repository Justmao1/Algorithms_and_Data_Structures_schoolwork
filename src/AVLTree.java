import java.util.List;

// AVL树的实现
class AVLTree {
    // 定义树的节点
    class Node {
        int key;
        Node left, right;
        int height;

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    // 获取节点的高度
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    // 获取平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    // 右旋转
    private Node rightRotate(Node y) {
//        System.out.println("右旋: " + y.key);
        Node x = y.left;
        Node T2 = x.right;

        // 旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // 返回新的根节点
        return x;
    }

    // 左旋转
    private Node leftRotate(Node x) {
//        System.out.println("左旋: " + x.key);
        Node y = x.right;
        Node T2 = y.left;

        // 旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // 返回新的根节点
        return y;
    }

    // 插入节点
    public void insert(int key) {
//        System.out.println("插入: " + key);
        root = insert(root, key);
//        System.out.println("插入后的树结构:");
//        printTree(root, "", true);
//        System.out.println();
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        // 递归插入
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // 不插入重复元素
        }

        // 更新当前节点的高度
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // 获取当前节点的平衡因子
        int balance = getBalanceFactor(node);

        // 左左情况
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // 右右情况
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // 左右情况
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左情况
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 打印树的结构
    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(node.key);
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
    // 计算树的深度总和
    private int calculateTotalDepth(Node node, int depth) {
        if (node == null) {
            return 0;
        }
        // 累加当前节点的深度
        int totalDepth = depth;

        // 递归计算左右子树的深度
        totalDepth += calculateTotalDepth(node.left, depth + 1);
        totalDepth += calculateTotalDepth(node.right, depth + 1);

        return totalDepth;
    }

    // 计算树的ASL
    public double calculateASL() {
        int totalDepth = calculateTotalDepth(root, 0);
        int nodeCount = countNodes(root);

        // 计算并返回平均查找长度
        if (nodeCount == 0) return 0;
        return (double) totalDepth / nodeCount;
    }

    // 计算节点的总数
    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 查找节点
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        // 如果节点为空，表示没有找到目标节点
        if (node == null) {
            return false;
        }

        // 如果找到了目标节点
        if (node.key == key) {
            return true;
        }

        // 递归查找
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        List<Integer> keys = List.of(4371, 1223, 6173, 4199, 4344, 9679, 1989);

        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("最终AVL树的结构:");
        tree.printTree(tree.root, "", true);
        System.out.println("AVL树的ASL: " + tree.calculateASL());
    }
}

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class timer {
    long startTime;
    long endTime;
    public timer() {
        startTime = System.nanoTime();
    }
    public long getTime() {
        endTime = System.nanoTime();
        return endTime - startTime;
    }
}
class PerformanceTest {

    public static void main(String[] args) {
        List<Integer> list = CreateRandomList.generateRandomIntList(10000000);
//        System.out.println(list);

        List<Integer> targets = new ArrayList<>();
        // 合并多个 subList
//        targets.addAll(list.subList(10, 11));
//        targets.addAll(list.subList(100, 101));
//        targets.addAll(list.subList(1000, 1001));
//        targets.addAll(list.subList(1300,1301));
//        targets.addAll(list.subList(3300,3301));
        targets.addAll(list.subList(5000, 5001));
//        targets.addAll(list.subList(20000, 20001));
        targets.addAll(list.subList(40000, 40001));
        targets.addAll(list.subList(80000, 80001));
        targets.addAll(list.subList(380000, 380001));
//        targets.addAll(list.subList(500000, 500001));
        targets.addAll(list.subList(780000, 780001));
        targets.addAll(list.subList(1500000, 1500001));
        targets.addAll(list.subList(3000000, 3000001));
        targets.addAll(list.subList(4000000, 4000001));
        targets.addAll(list.subList(7000000, 7000001));
        targets.addAll(list.subList(9000000, 9000001));
        Collections.shuffle(targets);
        //Sequential search
        timer t1 = new timer();
        for (int target : targets) {
            int index = SequentialSearch.sequentialSearch(list, target);
            if (index != -1) {
                System.out.println("元素 " + target + " 找到，索引位置是: " + index);
            } else {
                System.out.println("元素 " + target + " 未找到。");
            }
        }
        System.out.println("SequentialSearch time: " + t1.getTime() + " nanoSeconds");

        //Binary search
        List<Integer> listCopy = new java.util.ArrayList<>(list);
        Collections.sort(listCopy);
        timer t2 = new timer();
        for (int target : targets) {
            int index = BinarySearch.binarySearch(listCopy, target);
            if (index != -1) {
                System.out.println("元素 " + target + " 找到，索引位置是: " + index);
            } else {
                System.out.println("元素 " + target + " 未找到。");
            }
        }
        System.out.println("BinarySearch time: " + t2.getTime() + " nanoSeconds");


        //AVL tree
        AVLTree avl = new AVLTree();
        for (int i = 0; i < list.size(); i++) {
            avl.insert(list.get(i));
        }
        timer t3 = new timer();
        for (int target : targets) {
            if (avl.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("AVLTree time: " + t3.getTime() + " nanoSeconds");


        //splay tree
        SplayTree splay = new SplayTree();
        for (int i = 0; i < list.size(); i++) {
            splay.insert(list.get(i));
        }
        timer t4 = new timer();
        for (int target : targets) {
            if (splay.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("SplayTree time: " + t4.getTime() + " nanoSeconds");


        //B tree
        BTree bTree = new BTree();
        for (int i = 0; i < list.size(); i++) {
            bTree.insert(list.get(i));
        }
        timer t5 = new timer();
        for (int target : targets) {
            if (bTree.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("BTree time: " + t5.getTime() + " nanoSeconds");

        //red black tree
        RedBlackTree rbTree = new RedBlackTree();
        for (int i = 0; i < list.size(); i++) {
            rbTree.insert(list.get(i));
        }
        timer t6 = new timer();
        for (int target : targets) {
            if (rbTree.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("RedBlackTree time: " + t6.getTime() + " nanoSeconds");

        //separate chaining hash tables
        SeparateChainingHashTable<Integer> hashTable = new SeparateChainingHashTable<>(10);
        for (int i = 0; i < list.size(); i++) {
            hashTable.insert(list.get(i));
        }
        timer t7 = new timer();
        for (int target : targets) {
            if (hashTable.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("SeparateChainingHashTable time: " + t7.getTime() + " nanoSeconds");
        System.out.println("Table size: " + hashTable.getSize());
        System.out.println("Load factor: " + hashTable.loadFactor());

        //linear probing hash tables
        LinearProbingHashTable<Integer> hashTable2 = new LinearProbingHashTable<>(10);
        for (int i = 0; i < list.size(); i++) {
            hashTable2.insert(list.get(i));
        }
        timer t8 = new timer();
        for (int target : targets) {
            if (hashTable2.search(target)) {
                System.out.println("元素 " + target + " 找到");
            } else {
                System.out.println("元素 " + target + " 未找到");
            }
        }
        System.out.println("LinearProbingHashTable time: " + t8.getTime() + " nanoSeconds");
        System.out.println("Table size: " + hashTable2.getSize());
        System.out.println("Load factor: " + hashTable2.loadFactor());
    }
}


import java.util.ArrayList;
import java.util.LinkedList;

class SeparateChainingHashTable<T> {
    private LinkedList<T>[] table;
    private int size;
    private int numElements;
    private ArrayList<T> keys;


    public SeparateChainingHashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
        numElements = 0;
    }

    private int hash(T key) {
        return key.hashCode() % size;
    }

    public void insert(T key) {
        if (numElements >= size) {
            rehash();
        }
        int index = hash(key);
        table[index].add(key);
        numElements++;
    }

    public boolean search(T key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    private void rehash() {
        size *= 2;
        LinkedList<T>[] oldTable = table;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
        numElements = 0;
        for (LinkedList<T> list : oldTable) {
            for (T key : list) {
                insert(key);
            }
        }
    }

    public double loadFactor() {
        return (double) numElements / size;
    }

    public int getSize() {
        return size;
    }

    // 计算平均查找长度（ASL）
    public double averageSearchLength() {
        double totalSearchLength = 0.0;
        for (LinkedList<T> bucket : table) {
            int bucketSize = bucket.size();
            totalSearchLength += (bucketSize + 1) / 2.0 * bucketSize;  // 每个链表的查找长度大约是 (n_i + 1) / 2
        }
        // 返回平均查找长度
        return totalSearchLength / numElements;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i).append(": ").append(table[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<Integer> hashTable = new SeparateChainingHashTable<>(11);
        Integer[] keys = {4371, 1223, 6173, 4199, 4344, 9679, 1989};
        for (Integer key : keys) {
            hashTable.insert(key);
        }
        System.out.println("Separate Chaining Hash Table:");
        System.out.println(hashTable);
        System.out.println("Load Factor: " + hashTable.loadFactor());
        System.out.println("Average Search Length: " + hashTable.averageSearchLength());
    }
}

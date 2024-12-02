class LinearProbingHashTable<T> {
    private T[] table;
    private int size;
    private int numElements;

    public LinearProbingHashTable(int size) {
        this.size = size;
        table = (T[]) new Object[size];
        numElements = 0;
    }

    private int hash(T key) {
        return key.hashCode() % size;
    }

    public void insert(T key) {
        if (numElements >= size * 0.5) {
            rehash();
        }
        int index = hash(key);
        while (table[index] != null) {
            index = (index + 1) % size;  // Linear probing
        }
        table[index] = key;
        numElements++;
    }

    public boolean search(T key) {
        int index = hash(key);
        int probes = 0;
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % size;
            probes++;
        }
        return false;
    }

    private void rehash() {
        size *= 2;
        T[] oldTable = table;
        table = (T[]) new Object[size];
        numElements = 0;
        for (T key : oldTable) {
            if (key != null) {
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

        int totalProbes = 0;
        int successfulSearches = 0;
        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                continue;
            }
            int index = table[i].hashCode() % size;
            while(table[index]!=table[i]) {
                totalProbes++;
                index = (index + 1) % size;
            }
            totalProbes++;
            successfulSearches++;
        }

        return (double) totalProbes/successfulSearches ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (T value : table) {
            sb.append(i).append(": [").append(value == null ? "null" : value).append("]").append("\n");
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinearProbingHashTable<Integer> hashTable = new LinearProbingHashTable<>(11);
        Integer[] keys = {4371, 1223, 6173, 4199, 4344, 9679, 1989};
        for (Integer key : keys) {
            hashTable.insert(key);
        }
        System.out.println("LinearProbingHashTable with rehashing:");
        System.out.println(hashTable);
        System.out.println("Load Factor: " + hashTable.loadFactor());
        System.out.println("Average Search Length: " + hashTable.averageSearchLength());
    }
}

class QuadraticProbingHashTable<T> {
    private T[] table;
    private int size;
    private int numElements;

    public QuadraticProbingHashTable(int size) {
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
        int i = 1;
        while (table[index] != null) {
            index = (index + i * i) % size;  // Quadratic probing
            i++;
        }
        table[index] = key;
        numElements++;
    }

    public boolean search(T key) {
        int index = hash(key);
        int i = 1;
        while (table[index] != null) {
            if (table[index] == key) {
                return true;
            }
            index = (index + i * i) % size;
            i++;
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

    public double averageSearchLength() {
        int totalProbes = 0;
        int successfulSearches = 0;

        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                continue;
            }

            int index = hash(table[i]);
            int i_probe = 1;
            while (table[index] != null && table[index] != table[i]) {
                totalProbes++;
                index = (index + i_probe * i_probe) % size;  // Quadratic probing
                i_probe++;
            }

            if (table[index] != null) {
                totalProbes++;
                successfulSearches++;
            }
        }

        return successfulSearches == 0 ? 0 : (double) totalProbes / successfulSearches;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (T value : table) {
            sb.append(i).append(": [").append(value == null ? "null" : value).append("]").append("\n");
            i=i+1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        QuadraticProbingHashTable<Integer> hashTable = new QuadraticProbingHashTable<>(11);
        int[] keys = {4371, 1223, 6173, 4199, 4344, 9679, 1989};
        for (int key : keys) {
            hashTable.insert(key);
        }
        System.out.println("QuadraticProbingHashTable without rehashing:");
        System.out.println(hashTable);
        System.out.println("Load Factor: " + hashTable.loadFactor());
        System.out.println("Average Search Length: " + hashTable.averageSearchLength());
    }
}

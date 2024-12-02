class DoubleHashingHashTableWithRehashing<T> {
    private T[] table;
    private int size;
    private int numElements;

    // Constructor: Initialize table with given size
    public DoubleHashingHashTableWithRehashing(int size) {
        this.size = size;
        table = (T[]) new Object[size];
        numElements = 0;
    }

    // Primary hash function
    private int h1(T key) {
        return key.hashCode() % size;
    }

    // Secondary hash function (h2(x) = 7 - (x % 7))
    private int h2(T key) {
        return 7 - (key.hashCode() % 7);
    }
    // Insert method with rehashing
    public boolean insert(T key) {
        if (numElements >= size * 0.5) {
            rehash();  // Resize the table if load factor exceeds threshold
        }

        int index = h1(key);
        int i = 0;

        while (table[(index + i * h2(key)) % size] != null) {
            i++;
            if (i == size) {
                return false;  // Table is full (can't find an empty slot)
            }
        }
        table[(index + i * h2(key)) % size] = key;
        numElements++;
        return true;
    }

    // Search method: Search for a key in the table
    public boolean search(T key) {
        int index = h1(key);
        int i = 0;

        while (table[(index + i * h2(key)) % size] != null) {
            if (table[(index + i * h2(key)) % size] == key) {
                return true;  // Found the key
            }
            i++;
            if (i == size) {
                break;
            }
        }
        return false;  // Key not found
    }


    // Resize the hash table (rehashing)
    private void rehash() {
        size = size * 2;
        T[] oldTable = table;
        table = (T[]) new Object[size];
        numElements = 0;
        // Rehash all existing elements into the new table
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
        int foundCount = 0;

        // For each element in the table, attempt to find it and count probes
        for (T key : table) {
            if (key != null) {  // Only consider non-null entries
                int index = h1(key);
                int i = 0;
                int probes = 0;

                // Search for the key and count probes
                while (table[(index + i * h2(key)) % size] != null) {
                    probes++;
                    if (table[(index + i * h2(key)) % size] == key) {
                        foundCount++;
                        totalProbes += probes;
                        break;
                    }
                    i++;
                    if (i == size) {
                        break;  // Key not found after probing
                    }
                }
            }
        }

        // If no elements are found, return 0 (avoid division by zero)
        if (foundCount == 0) return 0;
        return (double) totalProbes / foundCount;
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
        DoubleHashingHashTableWithRehashing<Integer> hashTable = new DoubleHashingHashTableWithRehashing<>(11);
        Integer[] keys = {4371, 1223, 6173, 4199, 4344, 9679, 1989};
        for (Integer key : keys) {
            hashTable.insert(key);
        }
        System.out.println("QuadraticProbingHashTable without rehashing:");
        System.out.println(hashTable);
        System.out.println("Load Factor: " + hashTable.loadFactor());
        System.out.println("Average Search Length: " + hashTable.averageSearchLength());
    }
}

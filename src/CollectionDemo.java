import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        // Create and populate the collections
        HashMap<Integer, String> hashMap = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        TreeSet<String> treeSet = new TreeSet<>();

        hashMap.put(1, "Apple");
        hashMap.put(2, "Banana");
        hashMap.put(3, "Cherry");

        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");

        treeMap.put(1, "Apple");
        treeMap.put(2, "Banana");
        treeMap.put(3, "Cherry");

        treeSet.add("Apple");
        treeSet.add("Banana");
        treeSet.add("Cherry");

        // Print initial data
        System.out.println("Initial Data:");
        System.out.println("HashMap: " + hashMap);
        System.out.println("HashSet: " + hashSet);
        System.out.println("TreeMap: " + treeMap);
        System.out.println("TreeSet: " + treeSet);

        hashMap.remove(2);  // Removing key-value pair with key 2
        hashSet.remove("Banana");  // Removing element "Banana"
        treeMap.remove(3);  // Removing key-value pair with key 3
        treeSet.remove("Cherry");  // Removing element "Cherry"

        System.out.println("\nData after deletion:");
        System.out.println("HashMap: " + hashMap);
        System.out.println("HashSet: " + hashSet);
        System.out.println("TreeMap: " + treeMap);
        System.out.println("TreeSet: " + treeSet);
    }
}

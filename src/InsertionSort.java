import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

    // 使用 Insertion Sort 对 List<Integer> 进行排序
    public static void insertionSort(List<Integer> list) {
        int n = list.size();

        // 从第二个元素开始，逐个将元素插入到前面已经排好序的部分
        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j = i - 1;

            // 将大于 key 的元素移到右边
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j)); // 移动元素
                j--;
            }

            // 插入 key 到正确的位置
            list.set(j + 1, key);
        }
    }

    // 打印列表
    public static void printList(List<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> list = CreateRandomList.generateRandomIntList(1000);

        System.out.println("Original array:");
        printList(list);

        insertionSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

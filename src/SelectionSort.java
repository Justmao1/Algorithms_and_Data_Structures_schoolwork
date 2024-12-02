import java.util.ArrayList;
import java.util.List;

public class SelectionSort {

    // 使用 Selection Sort 对 List<Integer> 进行排序
    public static void selectionSort(List<Integer> list) {
        int n = list.size();

        // 遍历整个列表
        for (int i = 0; i < n - 1; i++) {
            // 找到未排序部分中的最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }

            // 如果找到的最小值索引不等于 i，则交换
            if (minIndex != i) {
                int temp = list.get(minIndex);
                list.set(minIndex, list.get(i));
                list.set(i, temp);
            }
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

        selectionSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    // 使用 Quick Sort 对 List<Integer> 进行排序
    public static void quickSort(List<Integer> list) {
        if (list.size() <= 1) {
            return; // 基本情况，单个元素的列表是有序的
        }

        // 调用递归方法进行排序
        quickSortRecursive(list, 0, list.size() - 1);
    }

    // 递归实现 Quick Sort
    private static void quickSortRecursive(List<Integer> list, int low, int high) {
        if (low < high) {
            // 选择 pivot（这里选择列表的最后一个元素作为 pivot）
            int pivotIndex = partition(list, low, high);

            // 对左右两部分分别进行排序
            quickSortRecursive(list, low, pivotIndex - 1);
            quickSortRecursive(list, pivotIndex + 1, high);
        }
    }

    // 分区操作：选择一个 pivot 并将小于 pivot 的元素放到左边，大于 pivot 的元素放到右边
    private static int partition(List<Integer> list, int low, int high) {
        // 选择列表的最后一个元素作为 pivot
        int pivot = list.get(high);
        int i = low - 1; // i 是较小元素的索引

        // 遍历列表并进行分区
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                // 如果当前元素小于等于 pivot，交换 list[i] 和 list[j]
                i++;
                swap(list, i, j);
            }
        }

        // 最后将 pivot 放到正确的位置
        swap(list, i + 1, high);
        return i + 1; // 返回 pivot 的最终位置
    }

    // 交换两个元素
    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
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

        quickSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

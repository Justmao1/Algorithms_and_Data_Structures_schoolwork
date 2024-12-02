import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    // 使用 Merge Sort 对 List<Integer> 进行排序
    public static void mergeSort(List<Integer> list) {
        if (list.size() <= 1) {
            return; // 基本情况，单个元素的列表是有序的
        }

        int mid = list.size() / 2;  // 找到中间位置
        List<Integer> left = new ArrayList<>(list.subList(0, mid));   // 左半部分
        List<Integer> right = new ArrayList<>(list.subList(mid, list.size())); // 右半部分

        mergeSort(left);  // 递归排序左半部分
        mergeSort(right); // 递归排序右半部分

        // 合并两个有序子列表
        merge(list, left, right);
    }

    // 合并两个有序列表
    private static void merge(List<Integer> list, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;

        // 合并左半部分和右半部分，直到其中一个列表遍历完
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        // 如果左半部分有剩余，直接加入结果列表
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        // 如果右半部分有剩余，直接加入结果列表
        while (j < right.size()) {
            list.set(k++, right.get(j++));
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

        mergeSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

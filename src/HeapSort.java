/**
 * @author JackMao
 * @date 2024/12/2
 */

import java.util.ArrayList;
import java.util.List;

public class HeapSort {

    // 使用最大堆对 List<Integer> 进行排序
    public static void heapSort(List<Integer> list) {
        int n = list.size();

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        // 一个一个地从堆中取出最大元素并交换到列表的末尾
        for (int i = n - 1; i > 0; i--) {
            // 将根节点（最大值）与最后一个元素交换
            swap(list, 0, i);

            // 对根节点重新进行堆化
            heapify(list, i, 0);
        }
    }

    // 堆化：保证以 i 为根的子树满足最大堆的性质
    private static void heapify(List<Integer> list, int n, int i) {
        int largest = i;          // 根节点
        int left = 2 * i + 1;     // 左子节点
        int right = 2 * i + 2;    // 右子节点

        // 如果左子节点比根节点大
        if (left < n && list.get(left) > list.get(largest)) {
            largest = left;
        }

        // 如果右子节点比根节点大
        if (right < n && list.get(right) > list.get(largest)) {
            largest = right;
        }

        // 如果根节点不是最大的，交换并递归堆化
        if (largest != i) {
            swap(list, i, largest);
            heapify(list, n, largest);
        }
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

        heapSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

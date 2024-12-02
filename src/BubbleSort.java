import java.util.ArrayList;
import java.util.List;

public class BubbleSort {

    // 使用 Bubble Sort 对 List<Integer> 进行排序
    public static void bubbleSort(List<Integer> list) {
        int n = list.size();

        // 外层循环用于控制每次冒泡
        for (int i = 0; i < n - 1; i++) {
            // 内层循环用于每一轮冒泡操作
            for (int j = 0; j < n - i - 1; j++) {
                // 如果相邻的元素顺序错误，交换它们
                if (list.get(j) > list.get(j + 1)) {
                    // 交换
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
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

        bubbleSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

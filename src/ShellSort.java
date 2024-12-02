import java.util.ArrayList;
import java.util.List;

public class ShellSort {

    // 使用 Shell Sort 对 List<Integer> 进行排序
    public static void shellSort(List<Integer> list) {
        int n = list.size();

        // 设置初始增量，通常从 n/2 开始，逐步减小
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 使用插入排序对每个增量子序列进行排序
            for (int i = gap; i < n; i++) {
                int key = list.get(i);
                int j = i;

                // 对每个增量子序列进行插入排序
                while (j >= gap && list.get(j - gap) > key) {
                    list.set(j, list.get(j - gap));
                    j -= gap;
                }

                // 插入 key 到正确位置
                list.set(j, key);
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

        shellSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

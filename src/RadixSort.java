import java.util.ArrayList;
import java.util.List;

public class RadixSort {

    // 使用 Radix Sort 对 List<Integer> 进行排序
    public static void radixSort(List<Integer> list) {
        if (list.size() <= 1) {
            return; // 如果列表只有一个元素或为空，直接返回
        }

        // 找到列表中最大值，用于确定需要排序的位数
        int maxValue = getMax(list);

        // 从最小位到最大位进行排序
        for (int exp = 1; maxValue / exp > 0; exp *= 10) {
            countSort(list, exp);
        }
    }

    // 计数排序（辅助排序）基于当前位的数字
    private static void countSort(List<Integer> list, int exp) {
        int n = list.size();
        List<Integer> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            output.add(0);  // 初始化输出列表
        }

        int[] count = new int[10];  // 计数器，0-9，每个数字对应一个计数

        // 将数字的当前位计数
        for (int i = 0; i < n; i++) {
            int digit = (list.get(i) / exp) % 10;
            count[digit]++;
        }

        // 修改计数器，count[i] 存储的是当前位小于等于 i 的数字的个数
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 构建输出列表
        for (int i = n - 1; i >= 0; i--) {
            int digit = (list.get(i) / exp) % 10;
            output.set(count[digit] - 1, list.get(i));
            count[digit]--;
        }

        // 将排序后的结果放回原列表
        for (int i = 0; i < n; i++) {
            list.set(i, output.get(i));
        }
    }

    // 获取列表中的最大值
    private static int getMax(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
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

        radixSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

import java.util.List;
import java.util.Collections;

public class BinarySearch {

    public static int binarySearch(List<Integer> list, int target) {

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = list.get(mid);

            if (midValue == target) {
                return mid;  // 找到目标，返回索引
            } else if (midValue < target) {
                left = mid + 1;  // 目标在右边
            } else {
                right = mid - 1;  // 目标在左边
            }
        }
        return -1;  // 如果没找到目标元素，返回-1
    }

    public static void main(String[] args) {
        // 示例列表，假设已按升序排列
        List<Integer> list = List.of(1, 3, 5, 7, 9, 11, 13, 15);
        // 示例目标元素
        List<Integer> targets = List.of(3, 7, 10, 15);

        // 对每个目标元素进行二分查找
        for (int target : targets) {
            int index = binarySearch(list, target);
            if (index != -1) {
                System.out.println("元素 " + target + " 找到，索引位置是: " + index);
            } else {
                System.out.println("元素 " + target + " 未找到。");
            }
        }
    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SequentialSearch {

    // 顺序查找方法
    public static int sequentialSearch(List<Integer> list, int target) {
        // 遍历列表中的每个元素
        for (int i = 0; i < list.size(); i++) {
            // 如果找到了目标元素，返回其索引
            if (list.get(i) == target) {
                return i;
            }
        }
        // 如果遍历完所有元素后仍然没有找到，返回-1
        return -1;
    }

    public static void main(String[] args) {
        // 创建一个较大的 List（模拟 1000000 个整数）
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeList.add(i);  // 填充数据：0到999999
        }

        // 模拟多个待查找的目标值
        List<Integer> targets = Arrays.asList(500, 200000, 999999, 1000001);

        // 对每个目标进行顺序查找
        for (int target : targets) {
            int index = sequentialSearch(largeList, target);
            if (index != -1) {
                System.out.println("元素 " + target + " 找到，索引位置是: " + index);
            } else {
                System.out.println("元素 " + target + " 未找到。");
            }
        }
    }
}

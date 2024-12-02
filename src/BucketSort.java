import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    // 使用 Bucket Sort 对 List<Float> 进行排序
    public static void bucketSort(List<Integer> list) {
        if (list.size() <= 1) {
            return; // 如果列表只有一个元素或为空，直接返回
        }

        // 获取最大值和最小值
        float minValue = Collections.min(list);
        float maxValue = Collections.max(list);

        // 计算桶的数量
        int bucketCount = (int) Math.sqrt(list.size()); // 使用平方根作为桶的数量

        // 创建桶
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将元素分配到不同的桶中
        for (Integer value : list) {
            int bucketIndex = (int) ((value - minValue) / (maxValue - minValue) * (bucketCount - 1));
            buckets.get(bucketIndex).add(value);
        }

        // 对每个桶进行排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket); // 使用 Collections.sort 对每个桶内部进行排序
        }

        // 将桶中的元素合并成最终排序后的列表
        list.clear();
        for (List<Integer> bucket : buckets) {
            list.addAll(bucket);
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

        bucketSort(list);

        System.out.println("Sorted array:");
        printList(list);
    }
}

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class PerformanceTestSorting {

    public static void main(String[] args) {
        List<Integer> list = CreateRandomList.generateRandomIntList(1000000);

        //Selection sort
        List<Integer> listCopy = new ArrayList<>(list);
        timer t1 = new timer();
        SelectionSort.selectionSort(listCopy);
        System.out.println("SelectionSort time: " + t1.getTime() + " nanoSeconds");

        //Bubble sort
        listCopy = new ArrayList<>(list);
        timer t2 = new timer();
        BubbleSort.bubbleSort(listCopy);
        System.out.println("BubbleSort time: " + t2.getTime() + " nanoSeconds");

        //Insertion sort
        listCopy = new ArrayList<>(list);
        timer t3 = new timer();
        InsertionSort.insertionSort(listCopy);
        System.out.println("InsertionSort time: " + t3.getTime() + " nanoSeconds");

        //Shell sort
        listCopy = new ArrayList<>(list);
        timer t4 = new timer();
        ShellSort.shellSort(listCopy);
        System.out.println("ShellSort time: " + t4.getTime() + " nanoSeconds");

        //Heap sort
        listCopy = new ArrayList<>(list);
        timer t5 = new timer();
        HeapSort.heapSort(listCopy);
        System.out.println("HeapSort time: " + t5.getTime() + " nanoSeconds");

        //Merge sort
        listCopy = new ArrayList<>(list);
        timer t6 = new timer();
        MergeSort.mergeSort(listCopy);
        System.out.println("MergeSort time: " + t6.getTime() + " nanoSeconds");

        //Quick sort
        listCopy = new ArrayList<>(list);
        timer t7 = new timer();
        QuickSort.quickSort(listCopy);
        System.out.println("QuickSort time: " + t7.getTime() + " nanoSeconds");

        //Bucket sort
        listCopy = new ArrayList<>(list);
        timer t8 = new timer();
        BucketSort.bucketSort(listCopy);
        System.out.println("BucketSort time: " + t8.getTime() + " nanoSeconds");

        //Radix sort
        listCopy = new ArrayList<>(list);
        timer t9 = new timer();
        RadixSort.radixSort(listCopy);
        System.out.println("RadixSort time: " + t9.getTime() + " nanoSeconds");
//4. Whether do the actual running times consistent with the time complexities, if not, analyze the reason.

    }
}


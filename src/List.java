import java.util.ArrayList;

public class List {
    public static void main(String[] args) {
        // 创建一个ArrayList来存储数字数组
        ArrayList<Integer> numbers = new ArrayList<>();

        // 添加数组元素
        numbers.add(4371);
        numbers.add(1223);
        numbers.add(6173);
        numbers.add(4199);
        numbers.add(4344);
        numbers.add(9679);
        numbers.add(1989);


        double asl = (double) (1+numbers.size())/2;

        // 输出ASL
        System.out.println("The List: ");
        System.out.println(numbers);
        System.out.println("The ASL (Average Sentence Length) is: " + asl);
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CreateRandomList {

    public static List<Integer> generateRandomIntList(int size) {
        List<Integer> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            // Use nextInt with an upper bound of Integer.MAX_VALUE to generate non-negative numbers
            list.add(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));  // Ensures non-negative numbers
        }

        return list;
    }
}
//generator
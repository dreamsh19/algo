import java.util.Arrays;

public class SolutionTest {

    static void print(Object o) {
        if (o instanceof int[]) {
            System.out.println(Arrays.toString((int[]) o));
        } else {
            System.out.println(o);
        }
    }

    public static void main(String[] args){
        int T;

        T = 2;
        int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}};
        String[] hand = {"right", "left"};

        Solution1 s = new Solution1();
        for (int i = 0; i < T; i++) {
            print(s.solution(numbers[i], hand[i]));
        }

    }
}

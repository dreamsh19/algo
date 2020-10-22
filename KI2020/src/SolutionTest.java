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

//        T = 2;
//        int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
//                {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}};
//        String[] hand = {"right", "left"};
//
//        Solution1 s = new Solution1();
//        for (int i = 0; i < T; i++) {
//            print(s.solution(numbers[i], hand[i]));
//        }


//        T = 2;
//        String[] expression = {"100-200*300-500+20", "50*6-3*2"};
//
//        Solution2 s = new Solution2();
//        for (int i = 0; i < T; i++) {
//            print(s.solution(expression[i]));
//        }


        T = 1;
        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}};

        Solution3 s = new Solution3();
        for (int i = 0; i < T; i++) {
            print(s.solution(gems[i]));
        }
    }
}

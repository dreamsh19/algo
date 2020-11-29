import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 3;

    static void print(Object o) {
        if (o instanceof int[]) {
            System.out.println(Arrays.toString((int[]) o));
        } else {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        int T;

        if (SOLUTION_NUMBER == 1) {
            T = 2;
            int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                    {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}};
            String[] hand = {"right", "left"};

            Solution1 s = new Solution1();
            for (int i = 0; i < T; i++) {
                print(s.solution(numbers[i], hand[i]));
            }
        } else if (SOLUTION_NUMBER == 2) {

            T = 2;
            String[] expression = {"100-200*300-500+20", "50*6-3*2"};

            Solution2 s = new Solution2();
            for (int i = 0; i < T; i++) {
                print(s.solution(expression[i]));
            }
        } else if (SOLUTION_NUMBER == 3) {

            T = 1;
            String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}};

            Solution3 s = new Solution3();
            for (int i = 0; i < T; i++) {
                print(s.solution(gems[i]));
            }
        } else if (SOLUTION_NUMBER == 4) {
            T = 2;
            int[][][] board = {{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                    {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}}};

            Solution4 s = new Solution4();
            for (int i = 1; i < T; i++) {
                print(s.solution(board[i]));
            }
        } else if (SOLUTION_NUMBER == 5) {

            T = 3;
            int[] n = {9};
            int[][][] path = {{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}};
            int[][][] order = {{{8, 5}, {6, 7}, {4, 1}}, {{4, 1}, {5, 2}}, {{4, 1}, {8, 7}, {6, 5}}};
            Solution5 s = new Solution5();
            for (int i = 0; i < T; i++) {
                print(s.solution(n[0], path[0], order[i]));
            }
        }
    }
}

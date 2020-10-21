import java.util.Arrays;

public class SolutionTest {

    static void print(Object o) {
        if (o instanceof int[]) {
            System.out.println(Arrays.toString((int[]) o));
        } else {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        int T;

//        //Solution1
//        T = 5;
//        String[] ss = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
//        Solution1 s = new Solution1();
//        for (int i = 0; i < T; i++) {
//            print(s.solution(ss[i]));
//        }

//        //Solution2
//        T = 3;
//        String[] p = {"(()())()", ")(", "()))((()"};
//        Solution2 s = new Solution2();
//        for (int i = 0; i < T; i++) print(s.solution(p[i]));

//        //Solution3
//        T = 1;
//        int[][][] key = {{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}};
//        int[][][] lock = {{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}};
//        Solution3 s = new Solution3();
//        for (int i = 0; i < T; i++) print(s.solution(key[i], lock[i]));
//

        //Solution4
        T = 2;
        int[] n = {5, 5};
        int[][][] build_frame = {
                {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}},
                {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}
        };
        Solution4 s = new Solution4();
        for (int i = 0; i < T; i++) {
            int[][] answer = s.solution(n[i], build_frame[i]);
            for (int[] a : answer) print(a);
        }


    }
}

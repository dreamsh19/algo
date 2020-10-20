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

        //Solution2
        T = 3;
        String[] p = {"(()())()", ")(", "()))((()"};
        Solution2 s = new Solution2();
        for (int i = 0; i < T; i++) print(s.solution(p[i]));

    }
}

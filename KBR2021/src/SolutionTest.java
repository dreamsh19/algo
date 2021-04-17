import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 2;

    static void print(Object o) {
        if (o instanceof int[]) {
            System.out.println(Arrays.toString((int[]) o));
        } else if (o instanceof String[]) {
            System.out.println(Arrays.toString((String[]) o));
        } else {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        int T;

        if (SOLUTION_NUMBER == 1) {
            T = 5;
            String[] new_ids = {"...!@BaT#*..y.abcdefghijklm", "z-+.^.", "=.=", "123_.def", "abcdefghijklmn.p"};

            Solution1 s = new Solution1();
            for (int i = 0; i < T; i++) {
                print(s.solution(new_ids[i]));
            }
        } else if (SOLUTION_NUMBER == 2) {
            T = 3;
            String[][] orders = {{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                    {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                    {"XYZ", "XWY", "WXA"}};
            int[][] courses = {{2, 3, 4},
                    {2, 3, 5},
                    {2, 3, 4}};
            Solution2 s = new Solution2();
            for (int i = 0; i < T; i++) {
                print(s.solution(orders[i], courses[i]));

            }
        }
    }
}

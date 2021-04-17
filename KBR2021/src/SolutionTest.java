import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 3;

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
        } else if (SOLUTION_NUMBER == 3) {
            T = 1;

            String[][] infos = {{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}};
            String[][] queries = {{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}};

            Solution3 s = new Solution3();
            for (int i = 0; i < T; i++) {
                print(s.solution(infos[i], queries[i]));
            }
        }
    }
}

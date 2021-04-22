import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 5;

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
        } else if (SOLUTION_NUMBER == 4) {
            T = 3;

            int[] ns = {6, 7, 6};
            int[] ss = {4, 3, 4};
            int[] as = {6, 4, 5};
            int[] bs = {2, 1, 6};
            int[][][] faress = {{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
                    {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
                    {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}
            };
            Solution4 s = new Solution4();
            for (int i = 0; i < T; i++) {
                print(s.solution__(ns[i], ss[i], as[i], bs[i], faress[i]));
            }
        } else if (SOLUTION_NUMBER == 5) {

            T = 3;
            String[] play_times = {"02:03:55", "99:59:59", "50:00:00"};
            String[] adv_times = {"00:14:15", "25:00:00", "50:00:00"};
            String[][] logss = {{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
                    {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
                    {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}

            };
            Solution5 s = new Solution5();
            for (int i = 0; i < T; i++) {
                print(s.solution(play_times[i], adv_times[i], logss[i]));
            }
            
        } else if (SOLUTION_NUMBER == 6) {

            T = 2;
            int[][][] boards = {{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}},
                    {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}
            };
            int[] rs = {1, 0};
            int[] cs = {0, 1};
            Solution6 s = new Solution6();
            for (int i = 0; i < T; i++) {
                print(s.solution(boards[i], rs[i], cs[i]));
            }
        }
    }
}

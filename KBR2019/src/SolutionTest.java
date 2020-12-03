import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 5;

    static void print(Object o) {

        String s;
        if (o.getClass().isArray()) {
            if (o instanceof int[]) s = Arrays.toString((int[]) o);
            else s = Arrays.deepToString((Object[]) o);
        } else {
            s = o.toString();
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        switch (SOLUTION_NUMBER) {
            case 1: {
                String[][] records = {{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"}};
                Solution1 s = new Solution1();
                for (int t = 0; t < records.length; t++) print(s.solution(records[t]));
            }
            break;
            case 2: {
                int[] N = {5};
                Solution2 s = new Solution2();
                int[][] stages = {{2, 1, 2, 6, 2, 4, 3, 3}};
                for (int t = 0; t < N.length; t++) print(s.solution(N[t], stages[t]));
            }
            case 3: {
                String[][][] relation = {{
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}}
                };
                Solution3 s = new Solution3();
                for (int t = 0; t < relation.length; t++) print(s.solution(relation[t]));

            }
            break;
            case 4: {
                int[][] food_times = {{3, 1, 2}};
                long[] k = {5};
                Solution4 s = new Solution4();
                for (int t = 0; t < food_times.length; t++) print(s.solution(food_times[t], k[t]));
            }
            break;
            case 5: {
                int[][][] nodeinfo = {{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}}};
                Solution5 s = new Solution5();
                for (int t = 0; t < nodeinfo.length; t++) {
                    print(s.solution(nodeinfo[t]));
                }
            }
            break;

            default:
                throw new IllegalArgumentException("No such solution number " + SOLUTION_NUMBER);
        }
    }
}

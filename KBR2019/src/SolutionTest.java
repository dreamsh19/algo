import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 2;

    static void print(Object o) {

        String s;
        if (o.getClass().isArray()) {
            if (o instanceof int[]) s = Arrays.toString((int[]) o);
            else s = Arrays.toString((Object[]) o);
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
            break;
            default:
                throw new IllegalArgumentException("No such solution number " + SOLUTION_NUMBER);
        }
    }
}

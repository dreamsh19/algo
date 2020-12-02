import java.util.Arrays;

public class SolutionTest {

    static final int SOLUTION_NUMBER = 1;

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
            case 1:
                String[][] records = {{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"}};
                Solution1 s = new Solution1();
                for (int t = 0; t < records.length; t++) print(s.solution(records[t]));
                break;

            default:
                throw new IllegalArgumentException("No such solution number " + SOLUTION_NUMBER);
        }
    }
}

import java.util.Arrays;

public class SolutionTest {

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
}

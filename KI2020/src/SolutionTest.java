import java.util.Arrays;

public class SolutionTest {
    
    static void print(Object o) {
        if (o instanceof int[]) {
            System.out.println(Arrays.toString((int[]) o));
        } else {
            System.out.println(o);
        }
    }

}

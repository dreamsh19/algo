import java.util.ArrayList;

public class Solution1 {

    public int[] solution(int brown, int yellow) {

        int area = brown + yellow;

        ArrayList<Integer> divisors = new ArrayList();
        for (int i = 2; i <= area / 2; i++) {
            if (area % i == 0) divisors.add(i);
        }
        for (int div : divisors) {
            int a = div;
            int b = area / div;
            if (a + b - 2 == brown / 2) return new int[]{b, a};
        }
        return null;
    }
}


import java.util.Arrays;

public class Solution10 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int ans = 0;
        int end = Integer.MIN_VALUE;

        for (int[] route:routes) {
            int from = route[0];
            int to = route[1];
            if (from > end) {
                ans++;
                end = to;
            }
        }
        return ans;
    }
}

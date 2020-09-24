import java.util.Arrays;

public class Solution10 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);
        int ans = 0;
        int end = Integer.MIN_VALUE;

        for (int i = 0; i < routes.length; i++) {
            int from = routes[i][0];
            int to = routes[i][1];
            if (from > end) {
                ans++;
                end = to;
            } else if (to < end) {
                end = to;
            }
        }
        return ans;
    }
}

import java.util.Arrays;

public class Solution30 {

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int l = rocks.length;
        int[] dist = new int[l + 1];
        dist[0] = rocks[0];
        for (int i = 1; i < l; i++) {
            dist[i] = rocks[i] - rocks[i - 1];
        }
        dist[l] = distance - rocks[l - 1];
        int left = 0, right = distance;
        int answer = distance;

        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            int sum = 0;
            for (int d : dist) {
                sum += d;
                if (sum < mid) {
                    count++;
                } else {
                    sum = 0;
                }
            }
            if (count <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }


        return answer;
    }
}

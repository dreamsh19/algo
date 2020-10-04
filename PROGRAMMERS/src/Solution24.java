public class Solution24 {

    int[] times;

    long getNum(long time) {
        long num = 0;
        for (int t : times) {
            num += time / t;
        }
        return num;
    }

    public long solution(int n, int[] times) {
        this.times = times;
        int minTime = Integer.MAX_VALUE;
        for (int t : times) {
            minTime = Math.min(minTime, t);
        }

        long max = (long) minTime * n;
        long min = minTime;
        long answer = max;
        long mid;

        while (min <= max) {
            mid = (max + min) / 2;
            long num = getNum(mid);
            if (num < n) {
                min = mid + 1;
            } else if (num >= n) {
                answer = mid;
                max = mid - 1;
            }
        }
        return answer;
    }
}

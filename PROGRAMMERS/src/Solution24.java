import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution24 {
    public long solution(int n, int[] times) {
        int l = times.length;
        long[] time = new long[l];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.comparingLong(o -> time[o]));

        for (int i = 0; i < l; i++) {
            time[i] = times[i];
            pq.add(i);
        }
        while (n > 1) {
            int i = pq.remove();
            time[i] += times[i];
            pq.add(i);
            n--;
        }
        return time[pq.remove()];
    }
}

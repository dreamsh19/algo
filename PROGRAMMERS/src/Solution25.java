import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution25 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        int n = jobs.length;

        int avg = 0;
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            q.offer(i);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> jobs[o1][1] - jobs[o2][1]);

        int time = jobs[0][0];
        while (!q.isEmpty()) {
            time = jobs[q.peek()][0];
            while(!q.isEmpty() && jobs[q.peek()][0] <= time){
                pq.add(q.poll());
            }
            while (!pq.isEmpty()) {
                int i = pq.remove();
                int finish = time + jobs[i][1];
                int start = jobs[i][0];
                avg += finish - start;
                time = finish;
//                System.out.println(finish-start);
                while(!q.isEmpty() && jobs[q.peek()][0] <= time){
                    pq.add(q.poll());
                }
            }
        }
        return avg / n;

    }
}

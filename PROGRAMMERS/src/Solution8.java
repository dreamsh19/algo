import java.util.PriorityQueue;

public class Solution8 {
    PriorityQueue<Integer> pq;

    public int solution(int[] scoville, int K) {
        pq = new PriorityQueue<>();
        for (int scov : scoville) pq.offer(scov);
        int answer = 0;
        while (pq.peek() < K) {
            if (pq.size() == 1) return -1;
            int first = pq.poll();
            int second = pq.poll();
            int toAdd = first + second * 2;
            pq.offer(toAdd);
            answer++;
        }
        return answer;
    }
}
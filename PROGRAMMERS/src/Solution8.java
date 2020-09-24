import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Solution8 {
    PriorityQueue<Integer> pq;

    public int solution(int[] scoville, int K) {
        pq = new PriorityQueue<>();
        for (int scov : scoville) pq.add(scov);
        int answer = 0;
        int first, second, toAdd;
        while (pq.peek() < K) {
            try {
                first = pq.remove();
                second = pq.remove();
            } catch (NoSuchElementException e) {
                return -1;
            }
            toAdd = first + second * 2;
            pq.add(toAdd);
            answer++;
        }
        return answer;
    }
}
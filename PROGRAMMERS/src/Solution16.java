import java.util.LinkedList;
import java.util.Queue;

public class Solution16 {

    boolean isPrintable(Queue<Integer> q) {
        int max = 0;
        for (int e : q) {
            max = Math.max(max, e);
        }
        return q.peek() == max;
    }

    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Integer> q = new LinkedList<>();
        for (int p : priorities) q.offer(p);

        while (true) {
            if (location == 0) {
                if (isPrintable(q)) {
                    q.poll();
                    return answer;
                } else {
                    q.offer(q.poll());
                    location = q.size() - 1;
                }
            } else {
                if (isPrintable(q)) {
                    q.poll();
                    answer++;
                } else {
                    q.offer(q.poll());
                }
                location--;

            }
        }

    }

}

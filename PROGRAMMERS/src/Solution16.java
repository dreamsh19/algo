import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution16 {

    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Integer> q = new LinkedList<>();
        for (int p : priorities) q.offer(p);
        Arrays.sort(priorities);
        int maxIdx = priorities.length - 1;

        while (true) {
            int poll = q.poll();
            if (poll == priorities[maxIdx]) {
                if (location == 0) {
                    return answer;
                } else {
                    maxIdx--;
                    answer++;
                    location--;
                }
            } else {
                q.offer(poll);
                location = location == 0 ? q.size() - 1 : location - 1;
            }
        }

    }

}

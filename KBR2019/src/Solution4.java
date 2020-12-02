import java.util.PriorityQueue;

public class Solution4 {

    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        PriorityQueue<Integer> foodLeft = new PriorityQueue<>();
        for (int t : food_times) foodLeft.add(t);
        long time = 0, elapsedTime = 0;
        long foodEaten = 0;
        int food;
        while (true) {
            food = foodLeft.peek();
            elapsedTime = ((food - foodEaten) * foodLeft.size());
            if (time + elapsedTime < k) {
                time += elapsedTime;
                foodEaten = food;
                foodLeft.remove();
            } else {
                break;
            }
        }
        k -= time;
        foodEaten += (k / foodLeft.size());
        k %= foodLeft.size();
        for (int i = 0; i < n; i++) {
            if (food_times[i] > foodEaten) {
                if (k-- == 0) return i + 1;
            }
        }
        return -1;

    }
}

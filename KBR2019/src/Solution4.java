import java.util.PriorityQueue;

public class Solution4 {

    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        PriorityQueue<Integer> foodLeft = new PriorityQueue<>();
        for (int t : food_times) foodLeft.add(t);
        int foodEaten = 0;
        while (!foodLeft.isEmpty()) {
            int food = foodLeft.peek();
            long timeNeedToEat = ((long) (food - foodEaten) * foodLeft.size());
            if (timeNeedToEat <= k) {
                k -= timeNeedToEat;
                foodEaten = food;
                foodLeft.remove();
            } else {
//                foodEaten += (k / foodLeft.size());
                k %= foodLeft.size();
                for (int i = 0; i < n; i++) {
                    if (food_times[i] > foodEaten) {
                        if (k-- == 0) return i + 1;
                    }
                }
            }
        }
        return -1;

    }
}
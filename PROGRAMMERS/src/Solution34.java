import java.util.ArrayList;

public class Solution34 {

    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int headProgress = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (headProgress < n) {
            for (int i = headProgress; i < n; i++) {
                progresses[i] += speeds[i];
            }
            int count = 0;
            while (headProgress < n && progresses[headProgress] >= 100) {
                headProgress++;
                count++;
            }
            if (count > 0) ans.add(count);

        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}

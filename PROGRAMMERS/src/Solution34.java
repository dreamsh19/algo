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

    public int[] solution_(int[] progresses, int[] speeds) {
        int n = progresses.length;
        ArrayList<Integer> ans = new ArrayList<>();

        int localMax = (99 - progresses[0]) / speeds[0] + 1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int remainDays = (99 - progresses[i]) / speeds[i] + 1;
            if (remainDays > localMax) {
                localMax = remainDays;
                ans.add(count);
                count = 1;
            } else {
                count++;
            }
        }
        ans.add(count);

        int[] answer = new int[ans.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;

    }
}

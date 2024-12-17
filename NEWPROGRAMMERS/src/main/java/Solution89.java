import java.util.Arrays;
import java.util.Comparator;

// https://school.programmers.co.kr/learn/courses/30/lessons/181188

public class Solution89 {
    public int solution(int[][] targets) {

        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

        int last = -1;
        int answer = 0;
        for (int[] target : targets) {
            if (target[0] >= last) {
                answer++;
                last = target[1];
            }
        }

        return answer;
    }

}

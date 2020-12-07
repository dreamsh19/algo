import java.util.Arrays;

public class Solution9 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int ans_idx = 0;
        for (int[] command : commands) {
            int k = command[2];
            int[] tmp = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(tmp);
            answer[ans_idx++] = tmp[k - 1];

        }
        return answer;
    }

}

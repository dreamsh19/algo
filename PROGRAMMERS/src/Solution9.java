import java.util.ArrayList;
import java.util.Collections;

public class Solution9 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int ans_idx = 0;
        for (int[] command : commands) {
            ArrayList<Integer> tmp = new ArrayList<>();
            int from = command[0]-1;
            int to = command[1]-1;
            int k = command[2];
            for(int i=from;i<=to;i++) tmp.add(array[i]);
            Collections.sort(tmp);
            answer[ans_idx++]=tmp.get(k-1);

        }
        return answer;
    }

}

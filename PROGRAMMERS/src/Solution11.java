import java.util.ArrayList;

public class Solution11 {

    static final int[] first = {1, 2, 3, 4, 5};
    static final int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
    static final int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public int[] solution(int[] answers) {
        int[] answer = {};
        int count1 = 0, count2 = 0, count3 = 0;
        int i = 0, j = 0, k = 0;
        for (int ans : answers) {
            if (ans == first[i]) count1++;
            if (ans == second[j]) count2++;
            if (ans == third[k]) count3++;
            i = (i + 1) % first.length;
            j = (j + 1) % second.length;
            k = (k + 1) % third.length;
        }
        int max = Math.max(count1, Math.max(count2, count3));
        ArrayList<Integer> ans = new ArrayList<>();
        if (max == count1) ans.add(1);
        if (max == count2) ans.add(2);
        if (max == count3) ans.add(3);

        answer = new int[ans.size()];
        int idx = 0;
        for (int a : ans) {
            answer[idx++] = a;
        }

        return answer;
    }
}

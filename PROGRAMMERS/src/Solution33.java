import java.util.ArrayList;
import java.util.Collections;

public class Solution33 {
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] haveUniform = new boolean[n + 2];
        for (int r : reserve) haveUniform[r] = true;

        ArrayList<Integer> losts = new ArrayList<>();
        for (int l : lost) {
            if (haveUniform[l]) {
                haveUniform[l] = false;
            } else {
                losts.add(l);
            }
        }
        Collections.sort(losts);

        int count = n - losts.size();
        for (int l : losts) {
            if (haveUniform[l - 1]) {
                count++;
            } else if (haveUniform[l + 1]) {
                count++;
                haveUniform[l + 1] = false;
            }
        }

        return count;
    }
}

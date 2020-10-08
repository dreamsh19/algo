import java.util.ArrayList;
import java.util.Collections;

public class Solution33 {
    public int solution(int n, int[] lost, int[] reserve) {
        ArrayList<Integer> losts = new ArrayList<>();
        ArrayList<Integer> reserves = new ArrayList<>();
        for (int l : lost) losts.add(l);
        for (int r : reserve) reserves.add(r);
        ArrayList<Integer> intersect = (ArrayList<Integer>) losts.clone();
        intersect.retainAll(reserves);
        losts.removeAll(intersect);
        reserves.removeAll(intersect);
        Collections.sort(losts);
        Collections.sort(reserves);
        boolean[] haveUniform = new boolean[n + 2];
        for (int r : reserves) {
            haveUniform[r] = true;
        }
        int count = 0;
        for (int l : losts) {
            if (haveUniform[l - 1]) {
                count++;
            } else if (haveUniform[l + 1]) {
                count++;
                haveUniform[l + 1] = false;
            }
        }

        return n - losts.size() + count;
    }
}

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution3 {
    public int[] solution(String[] gems) {
        HashSet<String> gemSet = new HashSet<>();
        for (String gem : gems) gemSet.add(gem);
        int numGem = gemSet.size();
        HashMap<String, Integer> lastIdx = new HashMap<>();
        int n = gems.length;
        int i;
        for (i = 0; i < n; i++) {
            lastIdx.put(gems[i], i);
            if (lastIdx.size() == numGem) break;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = 0, min = n;
        for (int idx : lastIdx.values()) {
            pq.add(idx);
            max = Math.max(max, idx);
            min = Math.min(min, idx);
        }
        int len = max - min;
        for (++i; i < n; i++) {
            int idx = lastIdx.get(gems[i]);
            pq.remove(idx);
            pq.add(i);
            lastIdx.put(gems[i], i);
            if (i - pq.peek() < len) {
                max = i;
                min = pq.peek();
                len = max - min;
            }
        }

        return new int[]{min + 1, max + 1};
    }
}


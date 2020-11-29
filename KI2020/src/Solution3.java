import java.util.HashMap;
import java.util.HashSet;

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
        int max = 0, min = n;
        for (int idx : lastIdx.values()) {
            max = Math.max(max, idx);
            min = Math.min(min, idx);
        }
        int len = max - min;
        int values_min = min;
        for (++i; i < n; i++) {
            int preIdx = lastIdx.get(gems[i]);
            lastIdx.put(gems[i], i);
            if (values_min == preIdx) {
                values_min = n;
                for (int v : lastIdx.values()) values_min = Math.min(values_min, v);
                if (i - values_min < len) {
                    max = i;
                    min = values_min;
                    len = max - min;
                }
            }
        }

        return new int[]{min + 1, max + 1};
    }
}


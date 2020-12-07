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

    public int[] solution_(String[] gems) {
        HashSet<String> gemSet = new HashSet<>();
        for (String gem : gems) gemSet.add(gem);
        int numGem = gemSet.size();
        int n = gems.length;
        HashMap<String, Integer> gemCount = new HashMap<>();
        int left = 0, right = 0;
        int ans_left = left, ans_right = right;
        int len = n;
        for (; right < n; right++) {
            String gem = gems[right];
            int cnt = gemCount.getOrDefault(gem, 0);
            gemCount.put(gem, cnt + 1);

            while (gemCount.size() == numGem) {
                String gemLeft = gems[left];
                cnt = gemCount.get(gemLeft);
                gemCount.put(gemLeft, --cnt);
                if (cnt == 0) {
                    gemCount.remove(gemLeft);
                    if (right - left < len) {
                        len = right - left;
                        ans_left = left;
                        ans_right = right;
                    }
                }
                left++;
            }
        }
        return new int[]{ans_left + 1, ans_right + 1};
    }
}


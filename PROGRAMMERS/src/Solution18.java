import java.util.HashMap;

public class Solution18 {

    HashMap<String, Boolean> visited;

    public int getDiff(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public int solution(String begin, String target, String[] words) {
        if (visited == null) {
            visited = new HashMap<>();
            for (String w : words) {
                visited.put(w, false);
            }
            boolean isPossible = false;
            for (String w : words) {
                if (w.equals(target)) {
                    isPossible = true;
                    break;
                }
            }
            if (!isPossible) return 0;

        }

        if (begin.equals(target)) return 0;

        int answer = Integer.MAX_VALUE;
        visited.put(begin, true);
        for (String w : words) {
            if (!visited.get(w) && getDiff(w, begin) == 1) {
                int s = solution(w, target, words);
                if (s >= 0) answer = Math.min(answer, 1 + s);
            }
        }

        if (answer == Integer.MAX_VALUE) return -1;

        return answer;
    }
}

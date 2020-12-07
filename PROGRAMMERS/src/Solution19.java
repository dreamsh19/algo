import java.util.HashMap;
import java.util.Map;

public class Solution19 {
    HashMap<String, Integer> dict;

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        dict = new HashMap<>();
        for (String p : participant) {
            int count = dict.getOrDefault(p, 0);
            dict.put(p, count + 1);
        }
        for (String c : completion) {
            int count = dict.get(c);
            dict.put(c, count - 1);
        }

        for (Map.Entry<String, Integer> e : dict.entrySet()) {
            if (e.getValue() == 1) return e.getKey();
        }
        return answer;
    }
}

import java.util.ArrayList;
import java.util.HashMap;

public class Solution3 {


    private static final String DELIMITER = " ";

    public int[] solution(String[] info, String[] query) {


        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (String lang : new String[]{"java", "cpp", "python", "-"}) {
            for (String jobType : new String[]{"backend", "frontend", "-"}) {
                for (String career : new String[]{"senior", "junior", "-"}) {
                    for (String soulFood : new String[]{"pizza", "chicken", "-"}) {
                        StringBuilder sb = new StringBuilder()
                                .append(lang).append(DELIMITER)
                                .append(jobType).append(DELIMITER)
                                .append(career).append(DELIMITER)
                                .append(soulFood);
                        map.put(sb.toString(), new ArrayList<>());
                    }
                }
            }
        }

        for (String infoUnit : info) {
            int idx = infoUnit.lastIndexOf(" ");
            String key = infoUnit.substring(0, idx);
            int score = Integer.parseInt(infoUnit.substring(idx + 1));
            for (String mapKey : map.keySet()) {
                if (contains(key, mapKey)) {
                    ArrayList<Integer> arr = map.get(mapKey);
                    arr.add(score);
                    map.put(mapKey, arr);
                }
            }
        }




        int len = query.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            String q = query[i].replaceAll(" and ", " ");
            int idx = q.lastIndexOf(" ");
            String key = q.substring(0, idx);
            int score = Integer.parseInt(q.substring(idx + 1));
            ArrayList<Integer> arr = map.get(key);

            int num = 0;
            for (int a : arr) {
                if (a >= score) num++;
            }
            answer[i] = num;
        }


        return answer;
    }

    public boolean contains(String s1, String s2) {
        String[] tokens1 = s1.split(DELIMITER);
        String[] tokens2 = s2.split(DELIMITER);
        for (int i = 0; i < 4; i++) {
            if (!tokens2[i].equals("-")) {
                if (!tokens2[i].equals(tokens1[i])) return false;
            }
        }
        return true;
    }
    
}

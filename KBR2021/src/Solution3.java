import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution3 {


    private static final String DELIMITER = " ";

    public int[] solution(String[] info, String[] query) {


        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (String lang : new String[]{"java", "cpp", "python", "-"}) {
            for (String jobType : new String[]{"backend", "frontend", "-"}) {
                for (String career : new String[]{"senior", "junior", "-"}) {
                    for (String soulFood : new String[]{"pizza", "chicken", "-"}) {
                        StringBuilder sb = new StringBuilder()
                                .append(lang.charAt(0))
                                .append(jobType.charAt(0))
                                .append(career.charAt(0))
                                .append(soulFood.charAt(0));
                        map.put(sb.toString(), new ArrayList<>());
                    }
                }
            }
        }

        for (String infoUnit : info) {
            int idx = infoUnit.lastIndexOf(" ");
            String key = infoUnit.substring(0, idx);
            String keyNew = "";
            for (String token : key.split(" ")) {
                keyNew += token.charAt(0);
            }
            int score = Integer.parseInt(infoUnit.substring(idx + 1));
            for (char c0 : new char[]{keyNew.charAt(0), '-'}) {
                for (char c1 : new char[]{keyNew.charAt(1), '-'}) {
                    for (char c2 : new char[]{keyNew.charAt(2), '-'}) {
                        for (char c3 : new char[]{keyNew.charAt(3), '-'}) {
                            String s = new StringBuilder()
                                    .append(c0)
                                    .append(c1)
                                    .append(c2)
                                    .append(c3)
                                    .toString();
                            ArrayList<Integer> arr = map.get(s);
                            arr.add(score);
                            map.put(s, arr);
                        }
                    }
                }
            }
        }


        HashMap<String, int[]> mapNew = new HashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            int[] newArr = new int[entry.getValue().size()];
            int i = 0;
            for (int a : entry.getValue()) {
                newArr[i++] = a;
            }
            Arrays.sort(newArr);
            mapNew.put(entry.getKey(), newArr);
        }

        int len = query.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            String q = query[i].replaceAll(" and ", " ");
            int idx = q.lastIndexOf(" ");
            String key = q.substring(0, idx);
            String keyNew = "";
            for (String token : key.split(" ")) {
                keyNew += token.charAt(0);
            }
            int score = Integer.parseInt(q.substring(idx + 1));
            int[] arr = mapNew.get(keyNew);

            int ret = Arrays.binarySearch(arr, score);

            if (ret >= 0) {
                while (ret >= 0 && arr[ret] == score) {
                    ret--;
                }
                ret = arr.length - 1 - ret;
            } else {
                ret = arr.length + ret + 1;
            }

            answer[i] = ret;
        }


        return answer;
    }

}

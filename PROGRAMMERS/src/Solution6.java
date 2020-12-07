import java.util.HashMap;

public class Solution6 {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> clothesCount = new HashMap<>();

        for (String[] clothes_token : clothes) {
            String clothesType = clothes_token[1];
            int cnt = clothesCount.getOrDefault(clothesType, 0);
            clothesCount.put(clothesType, ++cnt);
        }
        for (int v : clothesCount.values()) {
            answer *= v + 1;
        }
        return answer - 1;
    }
}

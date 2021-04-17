import java.util.*;

public class Solution2 {


    HashMap<Integer, HashMap<String, Integer>> map;

    void addCountToMap(String comb) {
        HashMap<String, Integer> countMap = map.getOrDefault(comb.length(), new HashMap<>());
        int count = countMap.getOrDefault(comb, 0);
        countMap.put(comb, ++count);
        map.put(comb.length(), countMap);
    }

    void combination(char[] original, char[] result, int startIdx, int resultIdx) {

        if (original.length - startIdx == result.length - resultIdx) {
            System.arraycopy(original, startIdx, result, resultIdx, result.length - resultIdx);
            resultIdx = result.length;
        }

        if (resultIdx == result.length) {
            addCountToMap(new String(result));
            return;
        }

        result[resultIdx] = original[startIdx];
        combination(original, result, startIdx + 1, resultIdx + 1);
        combination(original, result, startIdx + 1, resultIdx);
    }

    public String[] solution(String[] orders, int[] course) {

        map = new HashMap<>();
        for (int i = 0; i < orders.length; i++) {
            char[] order = orders[i].toCharArray();
            Arrays.sort(order);
            orders[i] = new String(order);
        }

        for (String order : orders) {
            char[] orderArray = order.toCharArray();
            for (int courseNum : course) {
                if (order.length() < courseNum) break;
                combination(orderArray, new char[courseNum], 0, 0);
            }
        }


        ArrayList<String> result = new ArrayList<>();
        for (HashMap<String, Integer> countMap : map.values()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
            entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

            int maxCount = entryList.get(0).getValue();
            for (Map.Entry<String, Integer> entry : entryList) {
                String menus = entry.getKey();
                int count = entry.getValue();
                if (count < 2) break;
                if (count < maxCount) break;
                result.add(menus);
            }
        }
        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }

}

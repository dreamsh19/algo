import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution5 {

    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> genreCount = new HashMap<>();
        HashMap<String, ArrayList<Integer>> genreTop = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String genre = genres[i];
            int play = plays[i];
            int cnt = genreCount.getOrDefault(genre, 0);
            cnt += play;
            genreCount.put(genre, cnt);

            ArrayList<Integer> topList = genreTop.getOrDefault(genre, new ArrayList<Integer>());
            topList.add(i);
            genreTop.put(genre, topList);
        }
        ArrayList<String> keySet = new ArrayList<>(genreCount.keySet());
        Collections.sort(keySet, (o1, o2) -> genreCount.get(o2) - genreCount.get(o1));
        for (String genre : keySet) {
            ArrayList<Integer> topList = genreTop.get(genre);
            Collections.sort(topList, (o1, o2) -> plays[o2] == plays[o1] ? o1 - o2 : plays[o2] - plays[o1]);
            for (int i = 0; i < Math.min(2, topList.size()); i++) {
                ans.add(topList.get(i));
            }
        }

        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
import java.util.ArrayList;

public class Solution23 {
    public boolean solution(String[] phone_book) {
        ArrayList<String>[] map = new ArrayList[21];
        for (int i = 1; i <= 20; i++) {
            map[i] = new ArrayList<>();
        }
        for (String p : phone_book) {
            map[p.length()].add(p);
        }

        for (int start_len = 2; start_len <= 20; start_len++) {
            for (String target : map[start_len]) {
                for (int below_len = 1; below_len < start_len; below_len++) {
                    for(String prefix : map[below_len]){
                        if(target.startsWith(prefix)) return false;
                    }
                }
            }
        }

        return true;
    }
}

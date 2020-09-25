
import java.util.Arrays;

public class Solution12 {
    public int solution(int[] citations) {
        int answer = 0;
        int l = citations.length;
        Arrays.sort(citations);
        for (int i = 1; i <= l; i++) {
            int min = citations[l - i];
            if (min < i) return i - 1;
        }
        return l;
    }
}

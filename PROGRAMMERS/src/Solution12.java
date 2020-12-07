
import java.util.Arrays;

public class Solution12 {
    public int solution(int[] citations) {

        int l = citations.length;
        Arrays.sort(citations);
        for (int i = l; i > 0; i--) {
            int min = citations[l - i];
            if (min >= i) return i;
        }
        return 0;
    }
}

import java.util.Arrays;
import java.util.Comparator;

public class Solution35 {
    public String solution(int[] numbers) {
        int n = numbers.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        String answer = "";
        for (String s : arr) {
            answer += s;
        }
        return answer.startsWith("0") ? "0" : answer;
    }

}

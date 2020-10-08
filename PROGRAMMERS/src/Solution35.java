import java.util.Arrays;
import java.util.Comparator;

public class Solution35 {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int n1 = Integer.parseInt(o1 + o2);
                int n2 = Integer.parseInt(o2 + o1);
                return n2 - n1;

            }
        });
        String answer = "";
        for (String s : arr) {
            answer += s;
        }

        return answer;
    }

}

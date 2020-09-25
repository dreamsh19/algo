import java.util.Stack;

public class Solution15 {

    public String solution(String number, int k) {
        Stack<Character> st = new Stack<>();

        int l = number.length();
        int ans_len = l - k;

        for (char c : number.toCharArray()) {
            while (!st.isEmpty() && c > st.peek() && k > 0) {
                st.pop();
                k--;
            }
            st.push(c);
        }

        char[] answer = new char[ans_len];
        int idx = ans_len - 1;
        while (st.size() > ans_len) {
            st.pop();
        }
        while (!st.isEmpty()) {
            answer[idx--] = st.pop();
        }

        return new String(answer);
    }
}

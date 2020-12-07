import java.util.EmptyStackException;
import java.util.Stack;

public class Solution2 {
    public String solution(String p) {
        if (p.isEmpty()) return p;

        int count = 0;
        int i;
        for (i = 0; i < p.length(); i++) {
            if ((count += p.charAt(i) == '(' ? 1 : -1) == 0) break;
        }
        String u = p.substring(0, i + 1);
        String v = p.substring(i + 1);

        if (isCorrect(u)) {
            return u + solution(v);
        } else {
            String answer = "(" + solution(v) + ")";
            u = u.substring(1, u.length() - 1);
            for (char c : u.toCharArray()) answer += c == '(' ? ')' : '(';
            return answer;
        }
    }

    public boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<>();

        for (char c : p.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else {
                try {
                    stack.pop();
                } catch (EmptyStackException e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

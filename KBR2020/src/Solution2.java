import java.util.EmptyStackException;
import java.util.Stack;

public class Solution2 {
    public String solution(String p) {
        String answer = "";
        if (p.isEmpty()) return p;

        int count = 0;
        int i;
        for (i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count == 0) break;
        }
        String u = p.substring(0, i + 1);
        String v = p.substring(i + 1);

        if (isCorrect(u)) {
            return u + solution(v);
        } else {
            answer = "(" + solution(v) + ")";
            for (int idx = 1; idx < u.length() - 1; idx++) {
                if (u.charAt(idx) == '(') {
                    answer += ')';
                } else {
                    answer += '(';
                }
            }
            return answer;
        }


    }

    public static boolean isCorrect(String p) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
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

    public static void main(String[] args) {
        String p = "()(())()";
        Solution2 sol = new Solution2();
        String ans = sol.solution(p);
        System.out.println(ans);
    }
}

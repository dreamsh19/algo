import java.util.ArrayList;
import java.util.Stack;

public class Solution2 {

    String expression;

    void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    long getResult(char[] operators) {
        int n = operators.length;
        String next = "";
        for (char c : expression.toCharArray()) {
            if (!isNumber(c)) {
                next += " " + c + " ";
            } else {
                next += c;
            }
        }
        Stack<String> st = new Stack<>();
        for (char op : operators) {
            String[] items = next.split(" ");
            for (int i = 0; i < items.length; i++) {
                if (items[i].endsWith("" + op)) {
                    String ll = st.pop();
                    String rr = items[i + 1];
                    st.push(calculate(ll, rr, op));
                    i++;
                } else {
                    if (items[i].length() == 1 && !isNumber(items[i].charAt(0))) {
                        items[i] = " " + items[i] + " ";
                    }
                    st.push(items[i]);
                }
            }
            next = "";
            while (!st.isEmpty()) {
                next = st.pop() + next;
            }
        }
        return Math.abs(Long.parseLong(next));
    }

    String calculate(String l, String r, char operator) {
        return Long.toString(calculate(Long.parseLong(l), Long.parseLong(r), operator));
    }

    long cal(String s, char operator) {
        String[] items = s.split("" + operator);
        long result = Long.parseLong(items[0]);
        for (int i = 1; i < items.length; i++) {
            long j = Long.parseLong(items[i]);
            result = calculate(result, j, operator);
        }
        return result;
    }

    boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    long calculate(long l, long r, char c) {
        if (c == '+') return l + r;
        if (c == '-') return l - r;
        if (c == '*') return l * r;
        return -1;
    }


    long permute(char[] arr, int l) {
        int n = arr.length;
        if (l == n) {
            return getResult(arr);
        }
        long ans = 0;
        for (int i = l; i < n; i++) {
            swap(arr, l, i);
            ans = Math.max(ans, permute(arr, l + 1));
            swap(arr, l, i);
        }
        return ans;

    }

    public long solution(String expression) {
        this.expression = expression;
        ArrayList<Character> opers = new ArrayList<>();
        for (char c : new char[]{'+', '-', '*'}) {
            if (expression.contains("" + c)) opers.add(c);
        }
        int numOperators = opers.size();
        char[] operators = new char[numOperators];
        for (int i = 0; i < numOperators; i++) operators[i] = opers.get(i);


        return permute(operators, 0);
    }

}

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Solution2 {

    void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    LinkedList<Long> operation(LinkedList<Long> arr, char operator) {
        if (!arr.contains((long) operator)) return arr;

        LinkedList<Long> result = new LinkedList<>();
        Iterator<Long> it = arr.iterator();
        boolean isOperator = false;
        while (it.hasNext()) {
            long item = it.next();
            if (isOperator && (char) item == operator) {
                result.add(calculate(result.removeLast(), it.next(), operator));
            } else {
                result.add(item);
                isOperator ^= true;
            }
        }
        return result;
    }


    long permute(LinkedList<Long> arr, char[] operators, int l) {
        int n = operators.length;
        if (l == n) {
            return Math.abs(arr.getFirst());
        }
        long ans = 0;
        for (int i = l; i < n; i++) {
            swap(operators, l, i);
            LinkedList<Long> next = operation(arr, operators[l]);
            ans = Math.max(ans, permute(next, operators, l + 1));
            swap(operators, l, i);
        }
        return ans;

    }

    long calculate(long a, long b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public long solution(String expression) {

        LinkedList<Long> list = new LinkedList<>();
        HashSet<Long> opSet = new HashSet<>();
        for (String exp : expression.split("\\b")) {
            long l;
            try {
                l = Long.parseLong(exp);
            } catch (NumberFormatException e) {
                l = exp.charAt(0);
                opSet.add(l);
            }
            list.add(l);
        }
        char[] operators = new char[opSet.size()];
        int idx = 0;
        for (long op : opSet) operators[idx++] = (char) op;
        return permute(list, operators, 0);
    }

}

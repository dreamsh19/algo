import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Solution2 {

    static final long PLUS = '+', MINUS = '-', MULTIPLY = '*';

    void swap(long[] arr, int i, int j) {
        long tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    LinkedList<Long> operation(LinkedList<Long> arr, long operator) {
        if (!arr.contains(operator)) return arr;

        LinkedList<Long> result = new LinkedList<>();
        Iterator<Long> it = arr.iterator();
        boolean isOperator = false;
        while (it.hasNext()) {
            long item = it.next();
            if (isOperator && item == operator) {
                result.add(calculate(result.removeLast(), it.next(), operator));
            } else {
                result.add(item);
                isOperator ^= true;
            }
        }
        return result;
    }


    long permute(LinkedList<Long> arr, long[] operators, int l) {
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

    long calculate(long a, long b, long operator) {
        if (operator == PLUS) return a + b;
        if (operator == MINUS) return a - b;
        if (operator == MULTIPLY) return a * b;
        throw new IllegalArgumentException("Invalid operator");
    }

    public long solution(String expression) {

        LinkedList<Long> list = new LinkedList<>();
        for (String exp : expression.split("\\b")) {
            long l;
            try {
                l = Long.parseLong(exp);
            } catch (NumberFormatException e) {
                l = exp.charAt(0);
            }
            list.add(l);
        }
        HashSet<Long> opSet = new HashSet<>();
        for (long operator : new long[]{PLUS, MINUS, MULTIPLY}) {
            if (list.contains(operator)) opSet.add(operator);
        }
        long[] operators = new long[opSet.size()];
        int idx = 0;
        for (long op : opSet) operators[idx++] = op;

        return permute(list, operators, 0);
    }

}

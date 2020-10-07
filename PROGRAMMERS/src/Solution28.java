import java.util.HashSet;

public class Solution28 {

    HashSet<Integer> numSet;

    boolean isPrime(int n) {

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return n > 1;
    }

    String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return new String(arr);
    }

    void permute(String s, int l, int r) {
        if (l == r) {
            int i = Integer.parseInt(s);
            if (isPrime(i)) numSet.add(i);
        } else {
            for (int i = l; i <= r; i++) {
                permute(swap(s, l, i), l + 1, r);
            }
        }
    }

    public int solution(String numbers) {
        int len = numbers.length();

        char[] cards = numbers.toCharArray();
        HashSet<String> combSet = new HashSet<>();
        numSet = new HashSet<>();

        int top = 1 << len;
        for (int mask = 1; mask < top; mask++) {
            String s = "";
            int bit = 1;
            for (int digit = 0; digit < len; digit++) {
                if ((mask & bit) > 0) {
                    s += cards[digit];
                }
                bit <<= 1;
            }
            combSet.add(s);
        }
        for (String s : combSet) {
            permute(s, 0, s.length() - 1);
        }
        return numSet.size();
    }
}

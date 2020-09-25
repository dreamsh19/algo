public class Solution15 {
    public String solution(String number, int k) {
//        System.out.printf("NUMBER[%s]k[%d]\n", number, k);
        if (k == 0) return number;
        if (number.length() <= k) return "";

        char[] numArr = number.toCharArray();
        int maxIdx = 0;
        char max = '0';
        for (int i = 0; i <= k; i++) {
            if (numArr[i] > max) {
                max = numArr[i];
                maxIdx = i;
            }
        }
        if (maxIdx == 0) {
            return numArr[0] + solution(number.substring(1), k);
        } else {
            return solution(number.substring(maxIdx), k - maxIdx);
        }
    }
}

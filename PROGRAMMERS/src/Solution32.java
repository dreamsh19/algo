public class Solution32 {

    public int solution(String name) {
        int len = name.length();

        int changeCount = 0;
        char[] target = name.toCharArray();
        for (char c : target) {
            int diff = c - 'A';
            changeCount += Math.min(diff, 26 - diff);
        }
        if (changeCount == 0) return 0;

        int moveCount = len - 1;
        for (int start = 0; start < len; start++) {
            if (target[start] == 'A') continue;
            int j = start + 1;
            while (j < len && target[j] == 'A') j++;
            int backCount = len - j;
            moveCount = Math.min(moveCount, start + backCount + Math.min(start, backCount));
        }

        return changeCount + moveCount;
    }
}

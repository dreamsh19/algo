public class Solution32 {

    public int solution(String name) {
        int len = name.length();

        String start = "";
        for (int i = 0; i < len; i++) {
            start += 'A';
        }
        int answer = Integer.MAX_VALUE;
        for (int startPos = 0; startPos < len; startPos++) {

            for (int k = 0; k < 2; k++) {
                char[] arr = start.toCharArray();
                int dist = startPos;
                int i = startPos;
                while (!new String(arr).equals(name)) {
                    int diff = name.charAt(i) - arr[i];
                    diff = Math.min(diff, 26 - diff);
                    dist += diff + 1;
                    arr[i] = name.charAt(i);
                    if (k == 0) {
                        i = (i + 1) % len;
                    } else {
                        if (--i < 0) i = len - 1;
                    }
                }
                answer = Math.min(answer, dist);
            }
        }
        return answer - 1;
    }
}

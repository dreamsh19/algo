// https://school.programmers.co.kr/learn/courses/30/lessons/181187?language=java

public class Solution90 {

    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1; x <= r2; x++) {
            int y1 = getSmallY(r1, x);
            int y2 = getLargeY(r2, x);
            answer += ((y2 - y1) + 1);
        }

        return answer * 4;
    }

    private int getLargeY(int r, int x) {
        return (int) Math.sqrt((long) r * r - (long) x * x);
    }

    private int getSmallY(int r, int x) {
        if (x >= r) return 0;
        return (int) Math.ceil(Math.sqrt((long) r * r - (long) x * x));

    }
}

public class Solution1 {

    int left_x, left_y, right_x, right_y;
    boolean isLeftHanded;
    int[] x, y;

    char moveHandTo(int number) {
        char hand;
        int nx = x[number];
        int ny = y[number];
        if (ny == 0) hand = 'L';
        else if (ny == 2) hand = 'R';
        else {
            int distL = Math.abs(nx - left_x) + Math.abs(ny - left_y);
            int distR = Math.abs(nx - right_x) + Math.abs(ny - right_y);
            if (distL > distR) hand = 'R';
            else if (distL < distR) hand = 'L';
            else hand = isLeftHanded ? 'L' : 'R';
        }
        if (hand == 'L') {
            left_x = nx;
            left_y = ny;
        } else {
            right_x = nx;
            right_y = ny;
        }
        return hand;
    }

    void getXY() {
        x = new int[10];
        y = new int[10];
        x[0] = 3;
        y[0] = 1;
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                x[num] = i;
                y[num] = j;
                num++;
            }
        }
    }

    public String solution(int[] numbers, String hand) {
        String answer = "";
        getXY();
        left_x = 3;
        left_y = 0;
        right_x = 3;
        right_y = 2;
        isLeftHanded = hand.equals("left");
        for (int n : numbers) answer += moveHandTo(n);

        return answer;
    }

}


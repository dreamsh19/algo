public class Solution1 {

    static final int[][] phone = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 0, 11}};
    int left_x, left_y, right_x, right_y;
    boolean isLeftHanded;


    int getX(int number) {
        return number == 0 ? 3 : (number - 1) / 3;
    }

    int getY(int number) {
        return number == 0 ? 1 : (number - 1) % 3;
    }

    char operation(int number) {
        char hand;
        int nx = getX(number);
        int ny = getY(number);
        if (ny == 0) {
            hand = 'L';
        } else if (ny == 2) {
            hand = 'R';
        } else {
            int distL = Math.abs(nx - left_x) + Math.abs(ny - left_y);
            int distR = Math.abs(nx - right_x) + Math.abs(ny - right_y);
            if (distL > distR) {
                hand = 'R';
            } else if (distL < distR) {
                hand = 'L';
            } else {
                hand = isLeftHanded ? 'L' : 'R';
            }
        }
        if (hand == 'L') {
            left_x = nx;
            left_y = ny;
        } else {
            right_x = nx;
            right_y = ny;
        }
//        System.out.println(phone[left_x][left_y]+" "+phone[right_x][right_y]);
        return hand;
    }

    public String solution(int[] numbers, String hand) {
        String answer = "";
        isLeftHanded = hand.equals("left");
        left_x = 3;
        left_y = 0;
        right_x = 3;
        right_y = 2;
        for (int n : numbers) {
            answer += operation(n);
        }
        return answer;
    }

}


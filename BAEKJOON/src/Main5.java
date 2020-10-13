import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main5 {

    char[][] cube;
    HashMap<Character, Integer> letterToIdx;

    static final int[][] aroundFace = {
            {2, 1, 5, 4},
            {2, 3, 5, 0},
            {3, 1, 0, 4},
            {2, 4, 5, 1},
            {2, 0, 5, 3},
            {0, 1, 3, 4}
    };
    static final int[][][] aroundIdx = {
            {{6, 5, 4}, {0, 7, 6}, {2, 1, 0}, {4, 3, 2}},
            {{4, 3, 2}, {0, 7, 6}, {4, 3, 2}, {4, 3, 2}},
            {{2, 1, 0}, {2, 1, 0}, {2, 1, 0}, {2, 1, 0}},
            {{2, 1, 0}, {0, 7, 6}, {6, 5, 4}, {4, 3, 2}},
            {{0, 7, 6}, {0, 7, 6}, {0, 7, 6}, {4, 3, 2}},
            {{6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}}
    };

    Main5() {
        cube = new char[6][8];
        letterToIdx = new HashMap<>();
        letterToIdx.put('U', 2);
        letterToIdx.put('D', 5);
        letterToIdx.put('F', 0);
        letterToIdx.put('B', 3);
        letterToIdx.put('L', 4);
        letterToIdx.put('R', 1);
        for (int i = 0; i < 8; i++) {
            cube[0][i] = 'r';
            cube[1][i] = 'b';
            cube[2][i] = 'w';
            cube[3][i] = 'o';
            cube[4][i] = 'g';
            cube[5][i] = 'y';
        }

    }

    void printFace(char[][] front) {
        String s = "";
        for (char[] row : front) {
            for (char c : row) {
                s += c;
            }
            s += '\n';
        }
        System.out.println(s);
    }

    void rotateFront(char letter, boolean clockwise) {
        int frontIdx = letterToIdx.get(letter);
        char[] front = cube[frontIdx];
        char[] tmp = new char[8];
        if (clockwise) {
            System.arraycopy(front, 6, tmp, 0, 2);
            System.arraycopy(front, 0, tmp, 2, 6);
        } else {
            System.arraycopy(front, 2, tmp, 0, 6);
            System.arraycopy(front, 0, tmp, 6, 2);
        }
        cube[frontIdx] = tmp;

    }

    void rotateAround(char letter, boolean clockwise) {
        int frontIdx = letterToIdx.get(letter);
        char[] front = cube[frontIdx];
        char[] around = new char[12];
        int ii = 0;
        for (int i = 0; i < 4; i++) {
            int faceIdx = aroundFace[frontIdx][i];
            for (int j = 0; j < 3; j++) {
                int inFaceIdx = aroundIdx[frontIdx][i][j];
                around[ii++] = cube[faceIdx][inFaceIdx];
            }
        }
        char[] tmp = new char[12];
        if (clockwise) {
            System.arraycopy(around, 9, tmp, 0, 3);
            System.arraycopy(around, 0, tmp, 3, 9);
        } else {
            System.arraycopy(around, 0, tmp, 9, 3);
            System.arraycopy(around, 3, tmp, 0, 9);
        }
        for (int i = 0; i < 12; i++) {
            int aroundFaceIdx = i / 3;
            int aroundIdxIdx = i % 3;
            int faceIdx = aroundFace[frontIdx][aroundFaceIdx];
            int inFaceIdx = aroundIdx[frontIdx][aroundFaceIdx][aroundIdxIdx];
            cube[faceIdx][inFaceIdx] = tmp[i];
        }


    }

    void operation(String op) {
        char letter = op.charAt(0);
        boolean isClockwise = op.charAt(1) == '+';
        rotateFront(letter, isClockwise);
        rotateAround(letter, isClockwise);
    }

    String getResult() {
        String s = "";
        char[] up = cube[2];
        for (int i = 0; i < 3; i++) {
            s += up[i];
        }
        s += '\n';
        s += up[7] + "w" + up[3];
        s += '\n';
        for (int i = 6; i > 3; i--) {
            s += up[i];
        }
        return s;
    }

    String solution(int n, String operations) {
        String[] ops = operations.split(" ");
        for (String op : ops) {
            operation(op);
        }
        return getResult();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            Main5 m = new Main5();
            int n = Integer.parseInt(br.readLine());
            String line = br.readLine();
            System.out.println(m.solution(n, line));
        }
    }
}

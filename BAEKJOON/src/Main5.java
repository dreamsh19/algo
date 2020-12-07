import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5 {

    Face[] cube;
    static final char[] colors = {'r', 'b', 'w', 'o', 'g', 'y'};
    static final int F = 0, R = 1, U = 2, B = 3, L = 4, D = 5;

    Main5() {
        cube = new Face[6];
        for (int i = 0; i < 6; i++) {
            cube[i] = new Face(colors[i]);
        }
        cube[F].addAround(cube[U].getDown(), cube[R].getLeft(), cube[D].getUp(), cube[L].getRight());
        cube[R].addAround(cube[U].getRight(), cube[B].getLeft(), cube[D].getRight(), cube[F].getRight());
        cube[U].addAround(cube[B].getUp(), cube[R].getUp(), cube[F].getUp(), cube[L].getUp());
        cube[B].addAround(cube[U].getUp(), cube[L].getLeft(), cube[D].getDown(), cube[R].getRight());
        cube[L].addAround(cube[U].getLeft(), cube[F].getLeft(), cube[D].getLeft(), cube[B].getRight());
        cube[D].addAround(cube[F].getDown(), cube[R].getDown(), cube[B].getDown(), cube[L].getDown());
        initCube();
    }

    void initCube() {
        for (int i = 0; i < 6; i++) {
            Face f = cube[i];
            f.center.color = colors[i];
            for (int j = 0; j < 8; j++) {
                f.face[j].color = colors[i];
            }
        }
    }

    String solution(int n, String operations) {
        initCube();
        for (String op : operations.split(" ")) {
            char face = op.charAt(0);
            boolean clockwise = op.charAt(1) == '+';
            if (face == 'F') cube[F].rotate(clockwise);
            else if (face == 'R') cube[R].rotate(clockwise);
            else if (face == 'U') cube[U].rotate(clockwise);
            else if (face == 'B') cube[B].rotate(clockwise);
            else if (face == 'L') cube[L].rotate(clockwise);
            else if (face == 'D') cube[D].rotate(clockwise);
        }
        return cube[U].toString();
    }


    class Face {
        CubeEntry[] face;
        CubeEntry[] around;
        CubeEntry center;


        Face(char initColor) {
            center = new CubeEntry(initColor);
            face = new CubeEntry[8];
            around = new CubeEntry[12];
            for (int i = 0; i < 8; i++) {
                face[i] = new CubeEntry(initColor);
            }
        }

        void addAround(CubeEntry[] up, CubeEntry[] right, CubeEntry[] down, CubeEntry[] left) {
            System.arraycopy(up, 0, around, 0, 3);
            System.arraycopy(right, 0, around, 3, 3);
            System.arraycopy(down, 0, around, 6, 3);
            System.arraycopy(left, 0, around, 9, 3);
        }

        CubeEntry[] getUp() {
            return new CubeEntry[]{face[2], face[1], face[0]};
        }

        CubeEntry[] getRight() {
            return new CubeEntry[]{face[4], face[3], face[2]};
        }

        CubeEntry[] getDown() {
            return new CubeEntry[]{face[6], face[5], face[4]};
        }

        CubeEntry[] getLeft() {
            return new CubeEntry[]{face[0], face[7], face[6]};
        }


        void arrayShiftRight(CubeEntry[] arr, int n) {
            int len = arr.length;
            CubeEntry[] tmp = new CubeEntry[n];
            int backIdx = len - n;
            for (int i = 0; i < n; i++) {
                char color = arr[backIdx + i].color;
                tmp[i] = new CubeEntry(color);
            }
            for (int i = n; i < len; i++) {
                arr[i].color = arr[i - n].color;
            }
            for (int i = 0; i < n; i++) {
                arr[i].color = tmp[i].color;
            }
        }


        void rotate(boolean clockwise) {
            int shift_face = clockwise ? 3 : 6;
            arrayShiftRight(face, shift_face);
            int shift_around = clockwise ? 3 : 9;
            arrayShiftRight(around, shift_around);
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < 3; i++) s += face[i].color;
            s += '\n';
            s += "" + face[7].color + center.color + face[3].color + "\n";
            for (int i = 6; i > 3; i--) s += face[i].color;
            return s;
        }

    }


    class CubeEntry {
        char color;

        CubeEntry(char color) {
            this.color = color;
        }
    }


//    char[][] cube;
//    HashMap<Character, Integer> letterToIdx;
//
//    static final int[][] aroundFace = {
//            {2, 1, 5, 4},
//            {2, 3, 5, 0},
//            {3, 1, 0, 4},
//            {2, 4, 5, 1},
//            {2, 0, 5, 3},
//            {0, 1, 3, 4}
//    };
//    static final int[][][] aroundIdx = {
//            {{6, 5, 4}, {0, 7, 6}, {2, 1, 0}, {4, 3, 2}},
//            {{4, 3, 2}, {0, 7, 6}, {4, 3, 2}, {4, 3, 2}},
//            {{2, 1, 0}, {2, 1, 0}, {2, 1, 0}, {2, 1, 0}},
//            {{2, 1, 0}, {0, 7, 6}, {6, 5, 4}, {4, 3, 2}},
//            {{0, 7, 6}, {0, 7, 6}, {0, 7, 6}, {4, 3, 2}},
//            {{6, 5, 4}, {6, 5, 4}, {6, 5, 4}, {6, 5, 4}}
//    };

//    Main5() {
//        cube = new char[6][8];
//        letterToIdx = new HashMap<>();
//        letterToIdx.put('U', 2);
//        letterToIdx.put('D', 5);
//        letterToIdx.put('F', 0);
//        letterToIdx.put('B', 3);
//        letterToIdx.put('L', 4);
//        letterToIdx.put('R', 1);
//        for (int i = 0; i < 8; i++) {
//            cube[0][i] = 'r';
//            cube[1][i] = 'b';
//            cube[2][i] = 'w';
//            cube[3][i] = 'o';
//            cube[4][i] = 'g';
//            cube[5][i] = 'y';
//        }
//
//}

//    void printFace(char[][] front) {
//        String s = "";
//        for (char[] row : front) {
//            for (char c : row) {
//                s += c;
//            }
//            s += '\n';
//        }
//        System.out.println(s);
//    }

//    void rotateFront(char letter, boolean clockwise) {
//        int frontIdx = letterToIdx.get(letter);
//        char[] front = cube[frontIdx];
//        char[] tmp = new char[8];
//        if (clockwise) {
//            System.arraycopy(front, 6, tmp, 0, 2);
//            System.arraycopy(front, 0, tmp, 2, 6);
//        } else {
//            System.arraycopy(front, 2, tmp, 0, 6);
//            System.arraycopy(front, 0, tmp, 6, 2);
//        }
//        cube[frontIdx] = tmp;
//
//    }

//    void rotateAround(char letter, boolean clockwise) {
//        int frontIdx = letterToIdx.get(letter);
//        char[] front = cube[frontIdx];
//        char[] around = new char[12];
//        int ii = 0;
//        for (int i = 0; i < 4; i++) {
//            int faceIdx = aroundFace[frontIdx][i];
//            for (int j = 0; j < 3; j++) {
//                int inFaceIdx = aroundIdx[frontIdx][i][j];
//                around[ii++] = cube[faceIdx][inFaceIdx];
//            }
//        }
//        char[] tmp = new char[12];
//        if (clockwise) {
//            System.arraycopy(around, 9, tmp, 0, 3);
//            System.arraycopy(around, 0, tmp, 3, 9);
//        } else {
//            System.arraycopy(around, 0, tmp, 9, 3);
//            System.arraycopy(around, 3, tmp, 0, 9);
//        }
//        for (int i = 0; i < 12; i++) {
//            int aroundFaceIdx = i / 3;
//            int aroundIdxIdx = i % 3;
//            int faceIdx = aroundFace[frontIdx][aroundFaceIdx];
//            int inFaceIdx = aroundIdx[frontIdx][aroundFaceIdx][aroundIdxIdx];
//            cube[faceIdx][inFaceIdx] = tmp[i];
//        }
//
//
//    }

//    void operation(String op) {
//        char letter = op.charAt(0);
//        boolean isClockwise = op.charAt(1) == '+';
//        rotateFront(letter, isClockwise);
//        rotateAround(letter, isClockwise);
//    }

//    String getResult() {
//        String s = "";
//        char[] up = cube[2];
//        for (int i = 0; i < 3; i++) {
//            s += up[i];
//        }
//        s += '\n';
//        s += up[7] + "w" + up[3];
//        s += '\n';
//        for (int i = 6; i > 3; i--) {
//            s += up[i];
//        }
//        return s;
//    }

//    String solution(int n, String operations) {
//        String[] ops = operations.split(" ");
//        for (String op : ops) {
//            operation(op);
//        }
//        return getResult();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Main5 m = new Main5();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            String line = br.readLine();
            System.out.println(m.solution(n, line));
        }
    }
}

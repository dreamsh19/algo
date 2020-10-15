import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main8 {

    int N, M;
    static final int RED = -2, BLUE = -3, HOLE = 1;
    static final int HAS_NONE = 0, HAS_R = 1, HAS_B = 2, HAS_RB = 3;
    static final int MAX_DEPTH = 10;
    int[][] matrix;
    int ans;

    void rotate() {
        transpose();
        flip();
    }

    void transpose() {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] tmp = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[j][i] = matrix[i][j];
            }
        }
        matrix = tmp;
    }

    void flip() {
        int m = matrix[0].length;
        for (int[] row : matrix) {
            for (int j = 0; j < m / 2; j++) {
                int tmp = row[j];
                row[j] = row[m - 1 - j];
                row[m - 1 - j] = tmp;
            }
        }
    }

    void goToBottom(int x, int y) {
        int num = matrix[x][y];
        int n = matrix.length;
        int next_x = x;
        while (next_x + 1 < n && matrix[next_x + 1][y] >= 0) {
            next_x++;
            if (matrix[next_x][y] == HOLE) {
                matrix[x][y] = 0;
                return;
            }
        }
        matrix[x][y] = 0;
        matrix[next_x][y] = num;

    }

    int check() {
        boolean hasRed = false, hasBlue = false;
        for (int[] row : matrix) {
            for (int i : row) {
                if (i == RED) hasRed = true;
                if (i == BLUE) hasBlue = true;
            }
        }
        return hasRed ? (hasBlue ? HAS_RB : HAS_R) : (hasBlue ? HAS_B : HAS_NONE);
    }

    void pushDown() {
        for (int k = 0; k < 2; k++) {
            int rx = -1, ry = -1, bx = -1, by = -1;
            int n = matrix.length;
            int m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == RED) {
                        rx = i;
                        ry = j;
                    }
                    if (matrix[i][j] == BLUE) {
                        bx = i;
                        by = j;
                    }
                }
            }
            if (rx != -1) goToBottom(rx, ry);
            if (bx != -1) goToBottom(bx, by);
        }
    }

    void copyMatrix(int[][] from, int[][] to) {
        int n = from.length;
        int m = from[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    void operation(int depth) {
        if (depth + 1 >= ans || depth == MAX_DEPTH) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int n = matrix.length;
            int m = matrix[0].length;
            int[][] tmp = new int[n][m];
            copyMatrix(matrix, tmp);
            pushDown();
            int result = check();
            if (result == HAS_B) {
                ans = Math.min(ans, depth + 1);
            } else if (result == HAS_RB) {
                operation(depth + 1);
            }
            matrix = tmp;
            rotate();
        }
    }

    void printMatrix() {
        String s = "";
        for (int[] row : matrix) s += Arrays.toString(row) + '\n';
        System.out.println(s);
    }

    int solution() {
        ans = Integer.MAX_VALUE;
        operation(0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        N = Integer.parseInt(token[0]);
        M = Integer.parseInt(token[1]);
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = line[j];
                int tmp;
                if (c == '#') {
                    tmp = -1;
                } else if (c == 'R') {
                    tmp = RED;
                } else if (c == 'B') {
                    tmp = BLUE;
                } else if (c == 'O') {
                    tmp = HOLE;
                } else {
                    tmp = 0;
                }
                matrix[i][j] = tmp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main8 m = new Main8();
        m.getInput();
        int answer = m.solution();
        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main1 {

    static ArrayList<Integer> cleaner_x;
    static int r, c;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static boolean isBlock(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c || (y == 0 && cleaner_x.contains(x));
    }

    static void printMatrix(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int solution(int[][] matrix, int T) {
        int time = 0;
        int answer = 0;
        while (time++ < T) {
            int[][] matrix_diff = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (matrix[i][j] < 5) continue;
                    int out = matrix[i][j] / 5;
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + dx[dir];
                        int y = j + dy[dir];
                        if (!isBlock(x, y)) {
                            matrix_diff[x][y] += out;
                            matrix[i][j] -= out;
                        }
                    }
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    matrix[i][j] += matrix_diff[i][j];
                }
            }
            int x1 = cleaner_x.get(0);
            for (int i = x1 - 1; i > 0; i--) {
                matrix[i][0] = matrix[i - 1][0];
            }
            for (int j = 0; j < c - 1; j++) {
                matrix[0][j] = matrix[0][j + 1];
            }
            for (int i = 0; i < x1; i++) {
                matrix[i][c - 1] = matrix[i + 1][c - 1];
            }
            for (int j = c - 1; j > 0; j--) {
                matrix[x1][j] = matrix[x1][j - 1];
            }
            int x2 = cleaner_x.get(1);
            for (int i = x2 + 1; i < r - 1; i++) {
                matrix[i][0] = matrix[i + 1][0];
            }
            for (int j = 0; j < c - 1; j++) {
                matrix[r - 1][j] = matrix[r - 1][j + 1];
            }
            for (int i = r - 1; i > x2; i--) {
                matrix[i][c - 1] = matrix[i - 1][c - 1];
            }
            for (int j = c - 1; j > 0; j--) {
                matrix[x2][j] = matrix[x2][j - 1];
            }

        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer += matrix[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        r = Integer.parseInt(line[0]);
        c = Integer.parseInt(line[1]);
        int T = Integer.parseInt(line[2]);
        int[][] matrix = new int[r][c];

        cleaner_x = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                int tmp = Integer.parseInt(line[j]);
                if (tmp == -1) {
                    cleaner_x.add(i);
                    tmp = 0;
                }
                matrix[i][j] = tmp;

            }
        }
        System.out.println(solution(matrix, T));
    }
}

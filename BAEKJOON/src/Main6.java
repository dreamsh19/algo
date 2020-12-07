import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main6 {

    int N;
    int[][] matrix;
    boolean[][] visited;
    static final int EMPTY = 0;
    static final int MAX_DEPTH = 5;
    int ans;

    void printMatrix() {
        String s = "";
        for (int[] row : matrix) {
            s += Arrays.toString(row) + '\n';
        }
        System.out.println(s);
    }

    void operation(int depth) {
//        System.out.println(depth);
//        printMatrix();
        if (depth == MAX_DEPTH) {
            ans = Math.max(ans, getMax());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmp = save();
            pushDown();
            operation(depth + 1);

            restore(tmp);
            rotate();

        }
    }

    int[][] save() {
        int[][] tmp = new int[N][N];
        for(int i=0;i<N;i++){
            tmp[i]=Arrays.copyOfRange(matrix[i],0,N);
        }
        return tmp;
    }

    void restore(int[][] tmp) {
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.copyOfRange(tmp[i],0, N);
        }
    }

    int getMax() {
        int max = 0;
        for (int[] row : matrix) {
            for (int i : row) {
                max = Math.max(max, i);
            }
        }
        return max;
    }

    int solution() {
        operation(0);
        return ans;
    }

    void rotate() {
        transpose();
        flip();
    }

    void transpose() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    void flip() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N / 2; j++) {
                int nj = N - 1 - j;
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][nj];
                matrix[i][nj] = tmp;
            }
        }
    }

    void pushDown() {
        visited = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                int num = matrix[i][j];
                if (num != EMPTY) {
                    int next_i = i;
                    while (next_i + 1 < N && matrix[next_i + 1][j] == EMPTY) {
                        next_i++;
                    }
                    matrix[i][j] = EMPTY;
                    matrix[next_i][j] = num;

                    if (next_i + 1 < N && num == matrix[next_i + 1][j] && !visited[next_i + 1][j]) {
                        visited[next_i + 1][j] = true;
                        matrix[next_i + 1][j] += num;
                        matrix[next_i][j] = EMPTY;
                    }
                }
            }
        }
    }

    void getInput() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Main6 m = new Main6();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {

    int[][] matrix;
    int N, M;
    static final int TYPES = 19;
    static final int[][] dxs = {{0, 0, 0, 0}, {0, 1, 2, 3},
            {0, 0, 1, 1},
            {0, 1, 2, 2}, {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, -1, -1, -1}, {0, -1, -1, -1}, {0, 0, 1, 2}, {0, 0, 1, 2},
            {0, 1, 1, 2}, {0, 1, 1, 2}, {0, 0, 1, 1}, {0, 0, 1, 1},
            {0, 1, 1, 1}, {0, 1, 0, -1}, {0, -1, -1, -1}, {0, -1, 0, 1}
    };
    static final int[][] dys = {{0, 1, 2, 3}, {0, 0, 0, 0},
            {0, 1, 0, 1},
            {0, 0, 0, 1}, {0, 0, 0, -1}, {0, 0, 1, 2}, {0, 0, -1, -2}, {0, 0, 1, 2}, {0, 0, -1, -2}, {0, -1, -1, -1}, {0, 1, 1, 1},
            {0, 0, 1, 1}, {0, 0, -1, -1}, {0, 1, 1, 2}, {0, -1, -1, -2},
            {0, -1, 0, 1}, {0, -1, -1, -1}, {0, -1, 0, 1}, {0, 1, 1, 1}
    };

    int getSum(int x, int y, int type) {
        int[] dx = dxs[type];
        int[] dy = dys[type];
        int sum = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                return -1;
            } else {
                sum += matrix[nx][ny];
            }
        }
        return sum;
    }

    int solution() {
        int ans = 0;
        for (int type = 0; type < TYPES; type++) {
            for (int start_x = 0; start_x < N; start_x++) {
                for (int start_y = 0; start_y < M; start_y++) {
                    ans = Math.max(ans, getSum(start_x, start_y, type));
                }
            }
        }
        return ans;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main4 m = new Main4();
        m.getInput();
        System.out.println(m.solution());
    }
}

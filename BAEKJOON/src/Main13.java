import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main13 {
    int N, M;
    int cur_x, cur_y;
    int[] operations;
    int[][] map;

    static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    static final int[] dx = {0, 0, 0, -1, 1};
    static final int[] dy = {0, 1, -1, 0, 0};

    class Cube {
        int F, B, U, D, L, R;
        int x, y;

        Cube(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean rotate(int dir) {
            return rotate(dir, F, B, U, D, L, R);
        }

        boolean rotate(int dir, int F, int B, int U, int D, int L, int R) {
            boolean hasMoved;
            if ((hasMoved = isValid(x + dx[dir], y + dy[dir]))) {
                x += dx[dir];
                y += dy[dir];
                if (dir == WEST) {
                    this.F = F;
                    this.B = B;
                    this.L = U;
                    this.D = L;
                    this.R = D;
                    this.U = R;
                } else if (dir == EAST) {
                    this.F = F;
                    this.B = B;
                    this.L = D;
                    this.U = L;
                    this.R = U;
                    this.D = R;
                } else if (dir == NORTH) {
                    this.L = L;
                    this.R = R;
                    this.F = D;
                    this.D = B;
                    this.B = U;
                    this.U = F;
                } else {
                    this.L = L;
                    this.R = R;
                    this.F = U;
                    this.U = B;
                    this.B = D;
                    this.D = F;
                }
                if (map[x][y] == 0) {
                    map[x][y] = this.D;
                } else {
                    this.D = map[x][y];
                    map[x][y] = 0;
                }
            }
//            System.out.println(this);
            return hasMoved;
        }

        @Override
        public String toString() {
            return "Cube{" +
                    "F=" + F +
                    ", B=" + B +
                    ", U=" + U +
                    ", D=" + D +
                    ", L=" + L +
                    ", R=" + R +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    String solution() {
        Cube cube = new Cube(cur_x, cur_y);
        String ans = "";
        for (int op : operations) {
            if (cube.rotate(op)) ans += cube.U + "\n";
        }
        return ans;
    }

    void getInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            cur_x = Integer.parseInt(line[2]);
            cur_y = Integer.parseInt(line[3]);
            int k = Integer.parseInt(line[4]);
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                line = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(line[j]);
                }
            }
            operations = new int[k];
            line = br.readLine().split(" ");
            for (int i = 0; i < k; i++) {
                operations[i] = Integer.parseInt(line[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Main13 m = new Main13();
        m.getInput();
        String ans = m.solution();
        System.out.println(ans);
    }
}

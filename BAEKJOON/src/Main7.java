import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main7 {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    final Pair NULL = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    int N;
    int[][] matrix;
    boolean[][] visited;
    int cur_x, cur_y, size, count;
    Pair prey;

    class Pair implements Comparable<Pair> {
        int x, y, dist;

        Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return this.dist == o.dist ? (this.x == o.x ? this.y - o.y : this.x - o.x) : this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    '}';
        }
    }

    boolean isBlocked(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || matrix[x][y] > size;
    }

    boolean isEatable(int x, int y) {
        return matrix[x][y] > 0 && matrix[x][y] < size;
    }

    Pair search() {

        visited = new boolean[N][N];
        Queue<Pair> bfs_q = new LinkedList<>();
        bfs_q.offer(new Pair(cur_x, cur_y, 0));
        visited[cur_x][cur_y] = true;

        prey = NULL;
        while (!bfs_q.isEmpty()) {
            Pair p = bfs_q.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;
            if (dist > prey.dist) break;
            if (isEatable(x, y) && p.compareTo(prey) < 0) prey = p;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isBlocked(nx, ny)) {
                    bfs_q.offer(new Pair(nx, ny, dist + 1));
                    visited[nx][ny] = true;
                }

            }
        }
        return prey;
    }

    void move() {
        cur_x = prey.x;
        cur_y = prey.y;
        matrix[cur_x][cur_y] = 0;
        if (++count == size) {
            size++;
            count = 0;
        }
    }

    int solution() {
        int T = 0;
        size = 2;
        count = 0;
        while (search() != NULL) {
            move();
            T += prey.dist;
        }
        return T;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(line[j]);
                if (tmp == 9) {
                    cur_x = i;
                    cur_y = j;
                    tmp = 0;
                }
                matrix[i][j] = tmp;

            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Main7 m = new Main7();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

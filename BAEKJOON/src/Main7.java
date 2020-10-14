import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main7 {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final String SEPARATOR = ",";
    int N;
    int[][] matrix;
    boolean[][] visited;
    int cur_x, cur_y, size, count;
    int prey_x, prey_y, prey_dist;


    boolean isBlocked(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || matrix[x][y] > size;
    }

    String intToString(int x, int y, int dist) {
        return x + SEPARATOR + y + SEPARATOR + dist;
    }

    void updatePrey(int x, int y, int dist) {
        if (matrix[x][y] > 0 && matrix[x][y] < size && dist <= prey_dist) {
            if (dist < prey_dist) {
                prey_dist = dist;
                prey_x = x;
                prey_y = y;
            } else {
                if (x < prey_x) {
                    prey_x = x;
                    prey_y = y;
                } else if (x == prey_x) {
                    prey_y = Math.min(y, prey_y);
                }
            }
        }
    }

    boolean search() {

        visited = new boolean[N][N];
        Queue<String> bfs_q = new LinkedList<>();
        bfs_q.offer(intToString(cur_x, cur_y, 0));
        visited[cur_x][cur_y] = true;
        prey_dist = Integer.MAX_VALUE;
        prey_x = Integer.MAX_VALUE;
        prey_y = Integer.MAX_VALUE;
        while (!bfs_q.isEmpty()) {
            String[] s = bfs_q.poll().split(SEPARATOR);
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            int dist = Integer.parseInt(s[2]);
            if (dist > prey_dist) break;
            updatePrey(x, y, dist);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isBlocked(nx, ny)) {
                    bfs_q.offer(intToString(nx, ny, dist + 1));
                    visited[nx][ny] = true;
                }

            }
        }
        return prey_dist != Integer.MAX_VALUE;
    }

    void move() {
        cur_x = prey_x;
        cur_y = prey_y;
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

        while (search()) {
            move();
            T += prey_dist;
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

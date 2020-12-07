import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main11 {

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    boolean[][] visited;
    int[][] matrix;
    int N, M;
    int[][][] dist;
    ArrayList<Pair> virus;
    int numVirus;
    int ans;

    boolean isBlocked(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || matrix[x][y] == WALL;
    }

    void bfs(int idx) {
        int[][] d = dist[idx];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        Pair v = virus.get(idx);
        Queue<Pair> bfsQ = new LinkedList<>();
        visited = new boolean[N][N];
        bfsQ.offer(v);
        visited[v.x][v.y] = true;
        d[v.x][v.y] = 0;

        while (!bfsQ.isEmpty()) {
            Pair p = bfsQ.poll();
            int x = p.x;
            int y = p.y;
            int dist = d[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isBlocked(nx, ny)) {
                    bfsQ.offer(new Pair(nx, ny));
                    d[nx][ny] = dist + 1;
                    visited[nx][ny] = true;
                }
            }
        }

    }


    void init() {
        numVirus = virus.size();
        dist = new int[numVirus][N][N];
        ans = Integer.MAX_VALUE;
    }

    boolean hasNoEmpty() {
        for (int[] row : matrix) {
            for (int i : row) {
                if (i == EMPTY) return false;
            }
        }
        return true;
    }

    int getMin(int[] combination) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == EMPTY) {
                    int min = Integer.MAX_VALUE;
                    for (int idx : combination) {
                        min = Math.min(min, dist[idx][i][j]);
                    }
                    if (min >= ans) return ans;
                    max = Math.max(max, min);
                }
            }
        }
        return max;
    }

    void combination(int[] arr, int[] result, int startIdx, int r) {
        int resultIdx = result.length - r;
        int n = arr.length;
        if (startIdx == n - r) {
            System.arraycopy(arr, startIdx, result, resultIdx, r);
            r = 0;
        }
        if (r == 0) {
            ans = Math.min(ans, getMin(result));
            return;
        }

        result[resultIdx] = arr[startIdx];
        combination(arr, result, startIdx + 1, r - 1);
        combination(arr, result, startIdx + 1, r);
    }

    int solution() {
        if (hasNoEmpty()) return 0;

        init();
        for (int i = 0; i < numVirus; i++) bfs(i);

        int[] arr = new int[numVirus];
        for (int i = 0; i < numVirus; i++) arr[i] = i;
        combination(arr, new int[M], 0, M);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        matrix = new int[N][N];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(line[j]);
                if (tmp == VIRUS) {
                    virus.add(new Pair(i, j));
                }
                matrix[i][j] = tmp;
            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Main11 m = new Main11();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }

}

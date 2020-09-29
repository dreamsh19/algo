import java.util.LinkedList;
import java.util.Queue;

public class Solution22 {

    boolean[][] isWall;
    int[][] count;
    static final int DIVISOR = 1000000007;
    static final int[] dx = {1, 0};
    static final int[] dy = {0, 1};
    int m, n;

    void bfs(int start_x, int start_y) {
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        qx.offer(start_x);
        qy.offer(start_y);
        isWall[start_x][start_y] = true;
        while (!qx.isEmpty()) {
            int x = qx.poll();
            int y = qy.poll();
            count[x][y] += (count[x - 1][y] + count[x][y - 1]) % DIVISOR;
            for (int i = 0; i < 2; i++) {
                int x_adj = x + dx[i];
                int y_adj = y + dy[i];
                if (!isWall[x_adj][y_adj]) {
                    qx.offer(x_adj);
                    qy.offer(y_adj);
                    isWall[x_adj][y_adj] = true;
                }

            }
        }

    }

    public int solution(int m, int n, int[][] puddles) {
        this.m = m;
        this.n = n;

        isWall = new boolean[m + 2][n + 2];
        count = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            isWall[i][0] = true;
            isWall[i][n + 1] = true;
        }
        for (int j = 1; j <= n; j++) {
            isWall[0][j] = true;
            isWall[m + 1][j] = true;
        }

        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            isWall[x][y] = true;
        }

        count[1][1] = 1;
        bfs(1, 1);

        return count[m][n];
    }
}

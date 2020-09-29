public class Solution22 {

    boolean[][] isWall;
    int[][] count;
    static final int DIVISOR = (int) 1e9 + 7;

    public int solution(int m, int n, int[][] puddles) {

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
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (!isWall[i][j]) {
                    count[i][j] += (count[i - 1][j] + count[i][j - 1]) % DIVISOR;
                }
            }
        }

        return count[m][n];
    }
}

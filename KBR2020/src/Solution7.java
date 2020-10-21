public class Solution7 {

    static final int VER = 1, HOR = 0;
    static final int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int[][] rotates = {{-1, 0}, {-1, 1}, {0, 0}, {0, 1}};
    static final int[][] wall = {{-1, 1}, {-1, 0}, {1, 1}, {1, 0}};

    int[][][] minDist;
    int[][] board;
    int n;

    public int solution(int[][] board) {
        n = board.length;
        minDist = new int[n][n][2];
        this.board = board;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minDist[i][j][0] = Integer.MAX_VALUE;
                minDist[i][j][1] = Integer.MAX_VALUE;
            }
        }
        minDist[0][0][0] = 0;

        dfs(0, 0, HOR);

        int answer = Math.min(minDist[n - 2][n - 1][VER], minDist[n - 1][n - 2][HOR]);
        return answer;
    }

    public boolean isReachable(int x, int y, int axis) {
        try {
            if (axis == HOR) {
                return board[x][y] == 0 && board[x][y + 1] == 0;
            } else {
                return board[x][y] == 0 && board[x + 1][y] == 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void dfs(int x, int y, int axis) {
        if (x == n - 2 && y == n - 1 && axis == VER) return;
        if (x == n - 1 && y == n - 2 && axis == HOR) return;

        for (int[] move : moves) {
            int x_new = x + move[0];
            int y_new = y + move[1];
            if (isReachable(x_new, y_new, axis)) {
                if (minDist[x_new][y_new][axis] > minDist[x][y][axis] + 1) {
                    minDist[x_new][y_new][axis] = minDist[x][y][axis] + 1;
                    dfs(x_new, y_new, axis);
                }
            }
        }
        int axis_rotate = 1 - axis;
        for (int i = 0; i < 4; i++) {
            int x_new = x + rotates[i][axis];
            int y_new = y + rotates[i][axis_rotate];
            int x_wall = x + wall[i][axis];
            int y_wall = y + wall[i][axis_rotate];
            if (isReachable(x_new, y_new, axis_rotate) && board[x_wall][y_wall] == 0
                    && minDist[x_new][y_new][axis_rotate] > minDist[x][y][axis] + 1) {
                minDist[x_new][y_new][axis_rotate] = minDist[x][y][axis] + 1;
                dfs(x_new, y_new, axis_rotate);
            }

        }

    }
}


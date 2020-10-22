import java.util.LinkedList;
import java.util.Queue;

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

    static final int[] moves_dx = {-1, 0, 1, 0};
    static final int[] moves_dy = {0, 1, 0, -1};
    static final int[] rotates_dx = {-1, 0, -1, 0};
    static final int[] rotates_dy = {0, 0, 1, 1};
    static final int[] wall_dx = {-1, 1, -1, 1};
    static final int[] wall_dy = {1, 1, 0, 0};
    static final int EMPTY = 0;
    boolean visited[][][];

    class State {
        int x, y;
        int axis;
        int dist;

        State(int x, int y, int axis, int dist) {
            this.x = x;
            this.y = y;
            this.axis = axis;
            this.dist = dist;
        }

        State rotate(int dir) {
            int nx, ny, wallx, wally;
            if (axis == HOR) {
                nx = x + rotates_dx[dir];
                ny = y + rotates_dy[dir];
                wallx = x + wall_dx[dir];
                wally = y + wall_dy[dir];
            } else {
                nx = x + rotates_dy[dir];
                ny = y + rotates_dx[dir];
                wallx = x + wall_dy[dir];
                wally = y + wall_dx[dir];
            }
            if (isBlocked(wallx, wally) || !isReachable(nx, ny, 1 - axis)) return null;
            else return new State(nx, ny, 1 - axis, dist + 1);
        }

        State move(int dir) {
            int nx = x + moves_dx[dir];
            int ny = y + moves_dy[dir];
            return isReachable(nx, ny, axis) ? new State(nx, ny, axis, dist + 1) : null;
        }

        boolean isEnd() {
            return (axis == HOR && x == n - 1 && y == n - 2) || (axis == VER && x == n - 2 && y == n - 1);
        }

    }
    boolean isBlocked(int x, int y) {
        try {
            return board[x][y] != EMPTY;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public int solution_(int[][] board) {
        n = board.length;
        this.board = board;

        State init = new State(0, 0, HOR, 0);
        Queue<State> bfsQ = new LinkedList<>();
        visited = new boolean[n][n][2];
        bfsQ.offer(init);
        visited[0][0][HOR] = true;

        while (!bfsQ.isEmpty()) {
            State s = bfsQ.poll();
            for (int dir = 0; dir < 4; dir++) {
                State ns = s.move(dir);
                if (ns == null) continue;
                if (ns.isEnd()) return ns.dist;
                if (!visited[ns.x][ns.y][ns.axis]) {
                    bfsQ.offer(ns);
                    visited[ns.x][ns.y][ns.axis] = true;
                }
            }
            for (int dir = 0; dir < 4; dir++) {
                State ns = s.rotate(dir);
                if (ns == null) continue;
                if (ns.isEnd()) return ns.dist;
                if (!visited[ns.x][ns.y][ns.axis]) {
                    bfsQ.offer(ns);
                    visited[ns.x][ns.y][ns.axis] = true;
                }
            }
        }


        return -1;

    }
}


import java.util.PriorityQueue;

public class Solution4 {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int EMPTY = 0;
    static final int RIGHT = 1, LEFT = 3;
    int[][] board;
    int n;
    int[][][] distance;


    class State implements Comparable<State> {
        int x, y;
        int dir;
        int dist;

        public State(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }

        State goStraight() {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            return isBlocked(nx, ny) ? null : new State(nx, ny, dir, dist + 1);
        }

        State turn(int turnType) {
            int ndir = (dir + turnType) % 4;
            int nx = x + dx[ndir];
            int ny = y + dy[ndir];
            return isBlocked(nx, ny) ? null : new State(nx, ny, ndir, dist + 6);
        }


        boolean isEnd() {
            return x == n - 1 && y == n - 1;
        }

        @Override
        public int compareTo(State o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "State{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", dist=" + dist +
                    '}';
        }
    }

    boolean isBlocked(int x, int y) {
        try {
            return board[x][y] != EMPTY;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        distance = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    distance[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }


        PriorityQueue<State> bfsQ = new PriorityQueue<>();
        bfsQ.add(new State(0, 0, 1, 0));
        bfsQ.add(new State(0, 0, 2, 0));
        distance[0][0][1] = 0;
        distance[0][0][2] = 0;
        while (!bfsQ.isEmpty()) {
            State s = bfsQ.remove();
            if (s.isEnd()) return s.dist * 100;
            State ns;
            if ((ns = s.goStraight()) != null) {
                if (ns.dist < distance[ns.x][ns.y][ns.dir]) {
                    distance[ns.x][ns.y][ns.dir] = ns.dist;
                    bfsQ.add(ns);
                }
            }
            if ((ns = s.turn(RIGHT)) != null) {
                if (ns.dist < distance[ns.x][ns.y][ns.dir]) {
                    distance[ns.x][ns.y][ns.dir] = ns.dist;
                    bfsQ.add(ns);
                }
            }
            if ((ns = s.turn(LEFT)) != null) {
                if (ns.dist < distance[ns.x][ns.y][ns.dir]) {
                    distance[ns.x][ns.y][ns.dir] = ns.dist;
                    bfsQ.add(ns);
                }
            }
        }

        return -1;
    }
}

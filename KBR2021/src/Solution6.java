import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution6 {

    private static final int BOARD_SIZE = 4;
    private static final int MAX_DIST = BOARD_SIZE * BOARD_SIZE;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    State initialState;
    int answer;

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    void permute(int[] arr, int l) {

        if (l == arr.length) {
            answer = Math.min(answer, initialState.transition(arr, 0));
            return;
        }

        for (int i = l; i < arr.length; i++) {
            swap(arr, l, i);
            permute(arr, l + 1);
            swap(arr, l, i);
        }
    }

    public int solution(int[][] board, int r, int c) {

        HashSet<Integer> numSet = new HashSet<>();
        for (int[] row : board) {
            for (int num : row)
                if (num != 0) numSet.add(num);
        }


        int[] arr = new int[numSet.size()];
        int idx = 0;
        for (int num : numSet) arr[idx++] = num;

        answer = Integer.MAX_VALUE;

        initialState = new State(0, board, r, c);
        permute(arr, 0);
        return answer;
    }

    boolean isOutOfIndex(int x, int y) {
        return x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE;
    }


    class State {

        int count;
        int[][] board;
        int curX, curY;

        State(int count, int[][] board, int curX, int curY) {
            this.count = count;
            this.board = new int[BOARD_SIZE][BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    this.board[i][j] = board[i][j];
                }
            }
            this.curX = curX;
            this.curY = curY;
        }

        boolean hasCard(int x, int y) {
            return board[x][y] != 0;
        }

        int[][] getDist() {
            int[][] dist = new int[BOARD_SIZE][BOARD_SIZE];
            Queue<Point> bfsQ = new LinkedList<>();
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    dist[i][j] = MAX_DIST;
                }
            }
            dist[curX][curY] = 0;
            bfsQ.offer(new Point(curX, curY));
            while (!bfsQ.isEmpty()) {
                Point p = bfsQ.poll();
                int ndist = dist[p.x][p.y] + 1;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x + dx[dir];
                    int ny = p.y + dy[dir];
                    if (!isOutOfIndex(nx, ny) && ndist < dist[nx][ny]) {
                        dist[nx][ny] = ndist;
                        bfsQ.add(new Point(nx, ny));
                    }
                }
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x;
                    int ny = p.y;
                    while (true) {
                        nx += dx[dir];
                        ny += dy[dir];
                        if (isOutOfIndex(nx, ny)) {
                            nx -= dx[dir];
                            ny -= dy[dir];
                            break;
                        }
                        if (hasCard(nx, ny)) break;
                    }
                    if (ndist < dist[nx][ny]) {
                        dist[nx][ny] = ndist;
                        bfsQ.add(new Point(nx, ny));
                    }

                }


            }
            return dist;
        }

        Point getCardPosition(int card, boolean asc) {
            if (asc) {
                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        if (board[i][j] == card) return new Point(i, j);
                    }
                }
            } else {
                for (int i = BOARD_SIZE - 1; i >= 0; i--) {
                    for (int j = BOARD_SIZE - 1; j >= 0; j--) {
                        if (board[i][j] == card) return new Point(i, j);
                    }
                }
            }
            return null;
        }


        State move(int destX, int destY) {
            int[][] dist = getDist();
            int ncount = count + dist[destX][destY] + 1;
            State ns = new State(ncount, board, destX, destY);
            ns.board[destX][destY] = 0;
            return ns;
        }

        int transition(int[] arr, int cardIdx) {
            if (cardIdx == arr.length) return count;

            int card = arr[cardIdx];
            Point p1 = getCardPosition(card, true);
            Point p2 = getCardPosition(card, false);
            State ns1 = move(p1.x, p1.y).move(p2.x, p2.y);
            State ns2 = move(p2.x, p2.y).move(p1.x, p1.y);
            return Math.min(ns1.transition(arr, cardIdx + 1), ns2.transition(arr, cardIdx + 1));
        }

        @Override
        public String toString() {
            return "State{" +
                    "count=" + count +
                    ", board=" + Arrays.deepToString(board) +
                    ", curX=" + curX +
                    ", curY=" + curY +
                    '}';
        }
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
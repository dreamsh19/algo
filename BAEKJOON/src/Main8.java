import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Main8 {

    int N, M;
    static final char WALL = '#', EMPTY = '.', HOLE = 'O';
    static final int MAX_TIME = 10;
    char[][][] matrices;

    class State implements Cloneable {

        static final int NULL = -1;

        int idx;
        int rx, ry, bx, by;
        int time;

        void setRed(int x, int y) {
            rx = x;
            ry = y;
        }

        void setBlue(int x, int y) {
            bx = x;
            by = y;
        }

        void rotate() {
            int m = matrices[idx].length;
            idx = (idx + 1) % 4;
            int tmp = rx;
            rx = ry;
            ry = m - 1 - tmp;
            tmp = bx;
            bx = by;
            by = m - 1 - tmp;
        }

        boolean isRed(int x, int y) {
            return x == rx && y == ry;
        }

        boolean isBlue(int x, int y) {
            return x == bx && y == by;
        }

        void pushDown() {
            if (rx > bx) {
                pushRedDown();
                pushBlueDown();
            } else {
                pushBlueDown();
                pushRedDown();
            }
            time++;
        }

        void pushRedDown() {
            char[][] matrix = matrices[idx];
            int m = matrix.length;
            while (++rx < m && matrix[rx][ry] != WALL && !isBlue(rx, ry)) {
                if (matrix[rx][ry] == HOLE) {
                    setRed(NULL, NULL);
                    return;
                }
            }
            rx--;
        }

        void pushBlueDown() {
            char[][] matrix = matrices[idx];
            int m = matrix.length;
            while (++bx < m && matrix[bx][by] != WALL && !isRed(bx, by)) {
                if (matrix[bx][by] == HOLE) {
                    setBlue(NULL, NULL);
                    return;
                }
            }
            bx--;
        }

        boolean hasRed() {
            return rx != NULL;
        }

        boolean hasBlue() {
            return bx != NULL;
        }

        public State clone() {
            try {
                return (State) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return idx == state.idx &&
                    rx == state.rx &&
                    ry == state.ry &&
                    bx == state.bx &&
                    by == state.by;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, rx, ry, bx, by);
        }

        @Override
        public String toString() {
            return "State{" +
                    "idx=" + idx +
                    ", rx=" + rx +
                    ", ry=" + ry +
                    ", bx=" + bx +
                    ", by=" + by +
                    ", time=" + time +
                    '}';
        }


    }

    void getRotates() {
        for (int i = 0; i < 3; i++) rotate(i);
    }

    void rotate(int i) {
        char[][] tmp = transpose(matrices[i]);
        flip(tmp);
        matrices[i + 1] = tmp;
    }

    char[][] transpose(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        char[][] tmp = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[j][i] = matrix[i][j];
            }
        }
        return tmp;
    }

    void flip(char[][] matrix) {
        int m = matrix[0].length;
        for (char[] row : matrix) {
            for (int j = 0; j < m / 2; j++) {
                char tmp = row[j];
                row[j] = row[m - 1 - j];
                row[m - 1 - j] = tmp;
            }
        }
    }

    int solution() {
        State initialState = getInitialState();
        getRotates();
        Queue<State> bfsQ = new LinkedList<>();
        HashSet<State> stateSet = new HashSet<>();

        bfsQ.offer(initialState);
        stateSet.add(initialState);

        while (!bfsQ.isEmpty()) {
            State s = bfsQ.poll();
            for (int i = 0; i < 4; i++) {
                State ns = s.clone();
                ns.pushDown();
                if (ns.hasBlue()) {
                    if (!ns.hasRed()) return ns.time;
                    else if (!stateSet.contains(ns) && ns.time < MAX_TIME) {
                        bfsQ.offer(ns);
                        stateSet.add(ns);
                    }
                }
                s.rotate();
            }
        }

        return -1;
    }


    State getInitialState() {
        State initialState = new State();
        char[][] matrix = matrices[0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 'R') {
                    initialState.setRed(i, j);
                    matrix[i][j] = EMPTY;
                } else if (matrix[i][j] == 'B') {
                    initialState.setBlue(i, j);
                    matrix[i][j] = EMPTY;
                }
            }
        }
        return initialState;
    }


    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        N = Integer.parseInt(token[0]);
        M = Integer.parseInt(token[1]);
        char[][] matrix = new char[N][M];
        for (int i = 0; i < N; i++) {
            matrix[i] = br.readLine().toCharArray();
        }
        matrices = new char[4][][];
        matrices[0] = matrix;
    }

    public static void main(String[] args) throws IOException {
        Main8 m = new Main8();
        m.getInput();
        int answer = m.solution();
        System.out.println(answer);
    }
}
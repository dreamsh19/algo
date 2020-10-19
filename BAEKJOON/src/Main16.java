import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main16 {

    int R, C, M;
    Shark[] sharks;
    int[][] map;
    static final int EMPTY = -1;
    static final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;

    class Shark {

        int id;
        int x, y;
        int dir, size, v;

        Shark(int id, int x, int y, int v, int dir, int size) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.v = v;
            this.dir = dir;
            this.size = size;
        }

        void move() {
            move(v);
        }

        void move(int toGo) {
            if (toGo == 0) return;

            if (dir == UP) {
                int dx = Math.min(x - 1, toGo);
                toGo -= dx;
                x -= dx;
                if (x == 1) dir = DOWN;

            } else if (dir == DOWN) {
                int dx = Math.min(R - x, toGo);
                toGo -= dx;
                x += dx;
                if (x == R) dir = UP;
            } else if (dir == RIGHT) {
                int dy = Math.min(C - y, toGo);
                toGo -= dy;
                y += dy;
                if (y == C) dir = LEFT;
            } else if (dir == LEFT) {
                int dy = Math.min(y - 1, toGo);
                toGo -= dy;
                y -= dy;
                if (y == 1) dir = RIGHT;
            }
            move(toGo);
        }

        boolean canEat(Shark o) {
            return size > o.size;
        }

    }


    Shark compete(Shark from, Shark to) {

        if (from.canEat(to)) {
            sharks[to.id] = null;
            return from;
        } else {
            sharks[from.id] = null;
            return to;
        }

    }

    int catchShark(int catcherY) {

        for (int x = 1; x <= R; x++) {
            if (map[x][catcherY] != EMPTY) {
                int id = map[x][catcherY];
                Shark target = sharks[id];
                int size = target.size;
                map[target.x][target.y] = EMPTY;
                sharks[id] = null;
                M--;
                return size;
            }
        }

        return 0;
    }

    void sharksMove() {
        for (Shark s : sharks) {
            if (s != null) {
                map[s.x][s.y] = EMPTY;
                s.move();
            }
        }

    }

    void updateMap() {
        for (Shark s : sharks) {
            if (s != null) {
                int x = s.x;
                int y = s.y;
                int target = map[x][y];
                if (target != EMPTY) s = compete(s, sharks[target]);
                map[x][y] = s.id;
            }
        }
    }

    int solution() {
        int catcher = 0;
        int sum = 0;
        while (++catcher <= C && M > 0) {
            sum += catchShark(catcher);
            sharksMove();
            updateMap();
        }
        return sum;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        M = Integer.parseInt(line[2]);
        map = new int[R + 1][C + 1];
        sharks = new Shark[M];
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) map[i][j] = EMPTY;
        }
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            sharks[i] = new Shark(i, x, y, Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            map[x][y] = i;
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Main16 m = new Main16();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

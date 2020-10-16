import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main12 {

    int N, H, M;
    int[][] nextLine;
    ArrayList<Line> Lines;

    class Line {
        int x, y;

        Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    int go(int y) {
        int x = 0;
        while (x < H) {
            int nextY = nextLine[x][y];
            y = nextY;
            x++;
        }
        return y;
    }

    void addLine(int x, int y) {
        int[] hor = nextLine[x - 1];
        int tmp = hor[y];
        hor[y] = hor[y + 1];
        hor[y + 1] = tmp;
    }

    boolean isPossible(Line[] combination) {
        boolean result = true;
        for (Line l : combination) {
            if (!isBuildable(l.x, l.y)) result = false;
            addLine(l.x, l.y);
        }

        if (result) {
            for (int i = 1; i <= N; i++) {
                if (i != go(i)) {
                    result = false;
                    break;
                }
            }
        }
        for (int i = combination.length - 1; i >= 0; i--) {
            addLine(combination[i].x, combination[i].y);
        }
        return result;
    }

    boolean hasLine(int x, int y) {
        return y >= 1 && y < N && nextLine[x - 1][y] == y + 1;
    }

    boolean isBuildable(int i, int j) {
        return !hasLine(i, j) && !hasLine(i, j - 1) && !hasLine(i, j + 1);
    }

    void getLines() {
        Lines = new ArrayList<>();
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (isBuildable(i, j)) {
                    Lines.add(new Line(i, j));
                }
            }
        }
    }

    int solution() {
        getLines();
        if (Lines.isEmpty()) return isPossible(new Line[0]) ? 0 : -1;
        for (int r = 0; r <= Math.min(Lines.size(), 3); r++) {
            if (combination(Lines, new Line[r], 0, r)) return r;

        }
        return -1;
    }

    boolean combination(ArrayList<Line> arr, Line[] result, int startIdx, int r) {
        int resultIdx = result.length - r;
        if (startIdx == arr.size() - r) {
            while (r-- > 0) {
                result[resultIdx++] = arr.get(startIdx++);
            }
            r = 0;
        }
        if (r == 0) {
            return isPossible(result);
        }

        result[resultIdx] = arr.get(startIdx);
        return combination(arr, result, startIdx + 1, r - 1)
                || combination(arr, result, startIdx + 1, r);
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        H = Integer.parseInt(line[2]);
        nextLine = new int[H][N + 1];
        for (int i = 0; i < H; i++) {
            for (int j = 1; j <= N; j++) {
                nextLine[i][j] = j;
            }
        }
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            addLine(a, b);
        }


    }

    public static void main(String[] args) throws IOException {
        Main12 m = new Main12();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

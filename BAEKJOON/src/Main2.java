import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main2 {

    static final int MAXTIME = 100;

    int[][] matrix;
    HashMap<Integer, Integer> dict;
    int r, c;

    ArrayList<Entry<Integer, Integer>> sortedEntry() {
        ArrayList<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(dict.entrySet());
        Collections.sort(list_entries,
                (o1, o2) -> o1.getValue() == o2.getValue() ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue());
        return list_entries;
    }

    void printMatrix() {
        String s = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                s += matrix[i][j] + "\t";
            }
            s += '\n';
        }
        System.out.println(s);
    }

    void operation() {
        if (r >= c) {
            int nextC = 0;
            for (int i = 0; i < r; i++) {
                dict.clear();
                for (int j = 0; j < c; j++) {
                    int num = matrix[i][j];
                    if (num == 0) continue;
                    int cnt = dict.getOrDefault(num, 0);
                    dict.put(num, cnt + 1);
                }
                ArrayList<Entry<Integer, Integer>> result = sortedEntry();
                int idx = 0;
                for (Entry<Integer, Integer> e : result) {
                    matrix[i][idx++] = e.getKey();
                    matrix[i][idx++] = e.getValue();
                    if (idx >= 100) break;
                }
                nextC = Math.max(nextC, idx);
                while (idx < 100) {
                    matrix[i][idx++] = 0;
                }
            }
            c = nextC;
        } else {
            int nextR = 0;
            for (int j = 0; j < c; j++) {
                dict.clear();
                for (int i = 0; i < r; i++) {
                    int num = matrix[i][j];
                    if (num == 0) continue;
                    int cnt = dict.getOrDefault(num, 0);
                    dict.put(num, cnt + 1);
                }
                ArrayList<Entry<Integer, Integer>> result = sortedEntry();
                int idx = 0;
                for (Entry<Integer, Integer> e : result) {
                    matrix[idx++][j] = e.getKey();
                    matrix[idx++][j] = e.getValue();
                    if (idx >= 100) break;
                }
                nextR = Math.max(nextR, idx);
                while (idx < 100) {
                    matrix[idx++][j] = 0;
                }
            }
            r = nextR;
        }
    }

    int solution(int R, int C, int k) {
        int T = 0;

        while (matrix[R - 1][C - 1] != k && T <= MAXTIME) {
            operation();
            T++;
        }
        return T > MAXTIME ? -1 : T;
    }

    public static void main(String[] args) throws IOException {
        Main2 m = new Main2();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int R = Integer.parseInt(line[0]);
        int C = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);
        m.matrix = new int[100][100];
        m.dict = new HashMap<>();
        m.r = 3;
        m.c = 3;
        for (int i = 0; i < 3; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                m.matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        int ans = m.solution(R, C, K);
        System.out.println(ans);
    }
}

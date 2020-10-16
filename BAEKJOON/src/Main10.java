import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10 {

    int N;
    int[][] S;

    int combination(int n, int[] result, int startIdx, int r) {
        int resultIdx = result.length - r;
        if (startIdx == n - r) {
            for (int i = startIdx; i < n; i++) result[resultIdx++] = i;
            r = 0;
        }
        if (r == 0) return getDiff(result);

        result[resultIdx] = startIdx;
        return Math.min(combination(n, result, startIdx + 1, r - 1),
                combination(n, result, startIdx + 1, r));

    }


    int getDiff(int[] combination) {

        int n = combination.length;
        int[] complement = new int[n];
        int idx = 0;
        int num = 0;
        for (int c : combination) {
            while (num < c) complement[idx++] = num++;
            num = c + 1;
        }
        while (idx < n) complement[idx++] = num++;

        int sum = 0;
        for (int i : combination) {
            for (int j : combination) sum += S[i][j];
        }

        for (int i : complement) {
            for (int j : complement) sum -= S[i][j];
        }

        return Math.abs(sum);

    }

    int solution() {
        int r = N / 2;
        return combination(N, new int[r], 0, r);
    }

    void getInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(line[j]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main10 m = new Main10();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

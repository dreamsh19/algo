import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main10 {
    int N;
    int[][] S;
    int min;

    void combination(int[] arr, int[] result, int startIdx, int r) {
        int n = arr.length;
        int resultIdx = result.length - r;
        if (startIdx == n - r) {
            System.arraycopy(arr, startIdx, result, resultIdx, r);
            r = 0;
        }
        if (r == 0) {
            min = Math.min(min, getDiff(result));
            return;
        }
        result[resultIdx] = arr[startIdx];
        combination(arr, result, startIdx + 1, r - 1);
        combination(arr, result, startIdx + 1, r);

    }


    int getDiff(int[] combination) {
        int n = combination.length;
        int combIdx = 0;
        int[] diff = new int[n];
        int diffIdx = 0;
        for (int i = 0; i < N; i++) {
            if (combIdx == n || i != combination[combIdx]) {
                diff[diffIdx++] = i;
            } else {
                combIdx++;
            }
        }
        int sum = 0;
        for(int i :combination){
            for(int j : combination) sum+=S[i][j];
        }
        for(int i : diff){
            for(int j : diff) sum-=S[i][j];
        }

        return Math.abs(sum);

    }

    int solution() {

        min = Integer.MAX_VALUE;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        int r = N / 2;
        combination(arr, new int[r], 0, r);
        return min;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(line[j]);
        }
        br.close();

    }

    public static void main(String[] args) throws IOException {
        Main10 m = new Main10();
        m.getInput();
        int ans = m.solution();
        System.out.println(ans);
    }
}

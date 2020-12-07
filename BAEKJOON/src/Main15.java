import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main15 {

    int[] time, price;
    int N;

    void getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(br.readLine());
            time = new int[N];
            price = new int[N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                time[i] = Integer.parseInt(line[0]);
                price[i] = Integer.parseInt(line[1]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int combination(int[] result, int startIdx, int resultIdx) {
        if (startIdx >= N) {
            int sum = 0;
            int end = startIdx == N ? resultIdx : resultIdx - 1;
            for (int i = 0; i < end; i++) {
                sum += price[result[i]];
            }
            return sum;
        }
        int t = time[startIdx];
        result[resultIdx] = startIdx;
        return Math.max(combination(result, startIdx + t, resultIdx + 1),
                combination(result, startIdx + 1, resultIdx));

    }

    int solution() {
        return combination(new int[N], 0, 0);
    }

    int solution_() {
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int t = time[i];
            int p = price[i];

            if (i + t > N) dp[i] = dp[i + 1];
            else dp[i] = Math.max(dp[i + 1], dp[i + t] + p);
        }   
        return dp[0];
    }


    public static void main(String[] args) {
        Main15 m = new Main15();
        m.getInput();
        int ans = m.solution_();
        System.out.println(ans);
    }
}
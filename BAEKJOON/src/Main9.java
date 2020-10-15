import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9 {

    int N, B, C;
    int[] A;

    long solution() {

        long answer = N;
        for (int a : A) {
            a -= B;
            if (a > 0) answer += (long) ((a - 1) / C) + 1;
        }
        return answer;
    }

    void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        A = new int[N];
        line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(line[i]);
        }
        line = br.readLine().split(" ");
        B = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
    }

    public static void main(String[] args) throws IOException {
        Main9 m = new Main9();
        m.getInput();
        long ans = m.solution();
        System.out.println(ans);
    }
}

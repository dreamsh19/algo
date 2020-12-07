import java.util.Scanner;

public class Main17 {

    int solution(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dist = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dist[i][0] = i;
        for (int j = 0; j <= m; j++) dist[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    dist[i][j] = Math.min(Math.min(dist[i - 1][j - 1] + 1, dist[i - 1][j] + 1), dist[i][j - 1] + 1);
                }
            }
        }
        return dist[n][m];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main17 m = new Main17();
        System.out.println(m.solution(sc.next(), sc.next()));
        sc.close();
    }
}

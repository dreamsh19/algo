public class Solution21 {

    boolean[][] beatable; // beatable[a][b] : a can win b

    public int solution(int n, int[][] results) {
        int answer = 0;
        beatable = new boolean[n + 1][n + 1];
        for (int[] res : results) {
            beatable[res[0]][res[1]] = true;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(beatable[i][k] && beatable[k][j]){
                        beatable[i][j] = true;
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            boolean rankable = true;
            for (int j = 1; j <= n; j++) {
                if (i != j && !beatable[i][j] && !beatable[j][i]) {
                    rankable = false;
                    break;
                }
            }
            if (rankable) answer++;
        }


        return answer;
    }
}

import java.util.HashSet;

public class Solution21 {

    // for solution()
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
                    if (beatable[i][k] && beatable[k][j]) {
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


    //for solution_()
    HashSet<Integer>[] parentSet;
    HashSet<Integer>[] childSet;
    boolean[] dfs_visited;


    int getChildNum(int root) {
        dfs_visited[root] = true;
        int numChild = 0;
        for (int c : childSet[root]) {
            if (!dfs_visited[c]) {
                numChild += 1 + getChildNum(c);
            }
        }
        return numChild;
    }

    int getParentNum(int root) {
        dfs_visited[root] = true;
        int numParent = 0;
        for (int p : parentSet[root]) {
            if (!dfs_visited[p]) {
                numParent += 1 + getParentNum(p);
            }
        }
        return numParent;
    }

    public int solution_(int n, int[][] results) {
        parentSet = new HashSet[n + 1];
        childSet = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            parentSet[i]=new HashSet<>();
            childSet[i]=new HashSet<>();
        }
        for (int[] res : results) {
            int win = res[0];
            int lose = res[1];
            parentSet[lose].add(win);
            childSet[win].add(lose);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            dfs_visited = new boolean[n + 1];
            if (getChildNum(i) + getParentNum(i) == n - 1) answer++;
        }

        return answer;
    }
}

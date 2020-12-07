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
    HashSet<Integer>[] parentSetFinal;
    HashSet<Integer>[] childSetFinal;


    void getParentSetFinal(int root) {
        if (parentSetFinal[root] == null) {
            parentSetFinal[root] = new HashSet<>();
            for (int p : parentSet[root]) {
                if (!parentSetFinal[root].contains(p)) {
                    parentSetFinal[root].add(p);
                    getParentSetFinal(p);
                    parentSetFinal[root].addAll(parentSetFinal[p]);
                }
            }
        }
    }

    void getChildSetFinal(int root) {
        if (childSetFinal[root] == null) {
            childSetFinal[root] = new HashSet<>();
            for (int c : childSet[root]) {
                if (!childSetFinal[root].contains(c)) {
                    childSetFinal[root].add(c);
                    getChildSetFinal(c);
                    childSetFinal[root].addAll(childSetFinal[c]);
                }
            }
        }
    }

    public int solution_(int n, int[][] results) {
        parentSet = new HashSet[n + 1];
        childSet = new HashSet[n + 1];
        parentSetFinal = new HashSet[n + 1];
        childSetFinal = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            parentSet[i] = new HashSet<>();
            childSet[i] = new HashSet<>();
        }
        for (int[] res : results) {
            int win = res[0];
            int lose = res[1];
            parentSet[lose].add(win);
            childSet[win].add(lose);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            getParentSetFinal(i);
            getChildSetFinal(i);
        }
        for (int i = 1; i <= n; i++) {
            if (parentSetFinal[i].size() + childSetFinal[i].size() == n - 1) answer++;
        }

        return answer;
    }
}

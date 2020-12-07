import java.util.LinkedList;

public class Solution31 {

    boolean[] visited;
    LinkedList<Integer>[] adjList;

    void dfs(int start) {
        visited[start] = true;
        for (int adj : adjList[start]) {
            if (!visited[adj]) dfs(adj);
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) adjList[i].add(j);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        return answer;
    }

}

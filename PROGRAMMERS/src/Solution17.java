import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution17 {
    LinkedList<Integer>[] adjList;
    int[] dist;
    boolean[] visited;

    void dfs(int start) {
        visited[start] = true;
        for (int adj : adjList[start]) {
            if (!visited[adj] && dist[start] + 1 < dist[adj]) {
                dist[adj] = dist[start] + 1;
                dfs(adj);
            }
        }
        visited[start] = false;
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        adjList = new LinkedList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList();
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        dist[1] = 0;
        dfs(1);

        int max = 0;
        for (int d : dist) {
            if(d>max){
                max=d;
                answer=1;
            }else if(d==max){
                answer++;
            }
        }

        return answer;
    }
}



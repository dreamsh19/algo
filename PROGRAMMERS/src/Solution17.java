import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution17 {
    LinkedList<Integer>[] adjList;
    int[] dist;
    boolean[] inSPT; // is included in shortest path tree

    public int solution(int n, int[][] edge) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]);

        adjList = new LinkedList[n + 1];
        dist = new int[n + 1];
        inSPT = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList();
            dist[i] = Integer.MAX_VALUE;
            inSPT[i] = false;
        }
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            int i = pq.remove();
            inSPT[i] = true;
            for (int adj : adjList[i]) {
                if (!inSPT[adj] && dist[adj] > dist[i] + 1) {
                    dist[adj] = dist[i] + 1;
                    pq.remove(adj);
                    pq.add(adj);
                }
            }
        }

        int max = 0;
        for (int d : dist) {
            if (d > max) {
                max = d;
                answer = 1;
            } else if (d == max) {
                answer++;
            }
        }

        return answer;
    }
}



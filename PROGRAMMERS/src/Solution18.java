import java.util.LinkedList;
import java.util.Queue;

public class Solution18 {

    int[] dist;
    boolean[] visited;
    Queue<Integer> q;
    LinkedList<Integer>[] adjList;

    public int getDiff(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    void addEdge(int i, int j) {
        adjList[i].add(j);
        adjList[j].add(i);
    }

    public int solution(String begin, String target, String[] words) {
        q = new LinkedList<>();
        int n = words.length;
        dist = new int[n];
        visited = new boolean[n];
        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            adjList[i] = new LinkedList<>();
        }

        int target_idx = -1;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                target_idx = i;
                break;
            }
        }
        if (target_idx == -1) return 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (getDiff(words[i], words[j]) == 1) {
                    addEdge(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (getDiff(words[i], begin) == 1) {
                dist[i] = 1;
                q.offer(i);
                visited[i] = true;
            }
        }
        while (!q.isEmpty()) {
            int next = q.poll();
            if (target_idx == next) return dist[target_idx];
            for (int adj : adjList[next]) {
                if (!visited[adj]) {
                    q.offer(adj);
                    visited[adj] = true;
                    dist[adj] = dist[next] + 1; // dist = height
                }
            }
        }
        return visited[target_idx] ? dist[target_idx] : 0;
    }
}

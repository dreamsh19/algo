import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution26 {

    int n;
    LinkedList<Node>[] adjList;
    PriorityQueue<Integer> pq;
    int[] dist;
    boolean[] inMST;

    class Node {
        int idx;
        int val;

        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    int MST(int start) {
        int len = 0;
        pq = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]);
        inMST = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        for (int i = 0; i < n; i++) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            int poll = pq.remove();
            inMST[poll] = true;
            len += dist[poll];
            for (Node adj : adjList[poll]) {
                if (!inMST[adj.idx] && adj.val < dist[adj.idx]) {
                    dist[adj.idx] = adj.val;
                    pq.remove(adj.idx);
                    pq.add(adj.idx);
                }
            }
        }
        return len;
    }


    public int solution(int n, int[][] costs) {
        this.n = n;
        adjList = new LinkedList[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
        }
        for (int[] edge : costs) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

//        int answer = Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            answer = Math.min(answer, MST(i));
//        }
        return MST(0);
    }
}

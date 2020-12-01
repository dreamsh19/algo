import java.util.LinkedList;
import java.util.Queue;

public class Solution5 {

    Node[] nodes;

    class Node {
        LinkedList<Integer> adjList;
        LinkedList<Integer> childList;
        int numParent;

        Node() {
            adjList = new LinkedList<>();
            childList = new LinkedList<>();

        }
    }

    public boolean solution(int n, int[][] path, int[][] order) {
        nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node();
        for (int[] p : path) {
            int from = p[0];
            int to = p[1];
            nodes[from].adjList.add(to);
            nodes[to].adjList.add(from);
        }

        Queue<Node> bfsQ = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        bfsQ.offer(nodes[0]);
        while (!bfsQ.isEmpty()) {
            Node node = bfsQ.poll();
            for (int adj : node.adjList) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    node.childList.add(adj);
                    nodes[adj].numParent++;
                    bfsQ.offer(nodes[adj]);
                }
            }
        }

        for (int[] o : order) {
            int pre = o[0];
            int post = o[1];
            nodes[pre].childList.add(post);
            nodes[post].numParent++;
        }

        Queue<Integer> q = new LinkedList<>();
        if (nodes[0].numParent == 0) q.offer(0);

        int count = 0;
        while (!q.isEmpty()) {
            int next = q.poll();
            for (int child : nodes[next].childList) {
                if (--nodes[child].numParent == 0) q.offer(child);
            }
            count++;
        }
        return count == n;
    }
}

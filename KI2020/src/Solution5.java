import java.util.*;

public class Solution5 {

    Node[] nodes;

    class Node {
        ArrayList<Integer> adjList;
        ArrayList<Integer> childList;
        int numParent;

        Node() {
            adjList = new ArrayList<>();
            childList = new ArrayList<>();

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
        q.offer(0);

        visited = new boolean[n];
        while (!q.isEmpty()) {
            int next = q.poll();
            visited[next] = true;
            for (int child : nodes[next].childList) {
                if (!visited[child]) {
                    if (--nodes[child].numParent == 0) q.offer(child);
                }
            }
        }
        for (Node node : nodes) {
            if (node.numParent > 0) return false;
        }
        return true;
    }
}

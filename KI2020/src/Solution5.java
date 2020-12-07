import java.util.LinkedList;
import java.util.Queue;

public class Solution5 {

    Node[] nodes;
    Node root;
    int n;

    class Node {
        LinkedList<Node> adjList;
        boolean visited;

        LinkedList<Node> outList;
        int inDegree;

        Node() {
            adjList = new LinkedList<>();
            outList = new LinkedList<>();
            inDegree = 0;
            visited = false;
        }

        void addOutEdge(Node dest) {
            outList.add(dest);
            dest.inDegree++;
        }
    }

    void init(int n, int rootIdx) {
        this.n = n;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node();
        root = nodes[rootIdx];
    }

    void dfs(Node start) {
        start.visited = true;
        for (Node adj : start.adjList) {
            if (!adj.visited) {
                start.addOutEdge(adj);
                dfs(adj);
            }
        }
    }

    void getDirectedGraph(int[][] order) {
        dfs(root);

        for (int[] o : order) {
            Node pre = nodes[o[0]];
            Node post = nodes[o[1]];
            pre.addOutEdge(post);
        }
    }

    boolean topologicalSortable() {
        Queue<Node> q = new LinkedList<>();
        if (root.inDegree == 0) q.offer(root);

        int count = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            count++;
            for (Node child : node.outList) {
                if (--child.inDegree == 0) q.offer(child);
            }
        }
        return count == n; // count < n : has cycle = cannot topologicalSort
    }


    public boolean solution(int n, int[][] path, int[][] order) {

        final int ROOT_IDX = 0;
        init(n, ROOT_IDX);

        for (int[] p : path) {
            Node from = nodes[p[0]];
            Node to = nodes[p[1]];
            from.adjList.add(to);
            to.adjList.add(from);
        }

        getDirectedGraph(order);

        return topologicalSortable();
    }
}

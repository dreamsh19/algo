import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution4 {


    private static final int MAX_DIST = 100000 * 200;

    int[][] dist;
    HashMap<Integer, Integer>[] graph;
    int aDest, bDest;


    void loadGraph(int n, int[][] fares) {
        graph = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashMap<>();
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int dist = fare[2];
            graph[from].put(to, dist);
            graph[to].put(from, dist);
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;


        aDest = a;
        bDest = b;

        loadGraph(n, fares);

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<State> bfsQ = new PriorityQueue<>();
        bfsQ.offer(new State(s, s, 0, true));
        dist[s][s] = 0;


        while (!bfsQ.isEmpty()) {
            State state = bfsQ.remove();
            if (state.isEnd()) return state.dist;

            int aCurr = state.a;
            int bCurr = state.b;

            if (dist[aCurr][bCurr] < state.dist) continue;

            if (aCurr == bCurr && state.canShare) {
                for (Map.Entry<Integer, Integer> e : graph[aCurr].entrySet()) {
                    int aTo = e.getKey();
                    int aDist = e.getValue();

                    int distNew = state.dist + aDist;
                    if (distNew < dist[aTo][aTo]) {
                        dist[aTo][aTo] = distNew;
                        bfsQ.add(new State(aTo, aTo, distNew, true));
                    }
                }
            }
            for (Map.Entry<Integer, Integer> e : graph[aCurr].entrySet()) {
                int aTo = e.getKey();
                int dDist = e.getValue();
                int distNew = state.dist + dDist;
                if (distNew < dist[aTo][bCurr]) {
                    dist[aTo][bCurr] = distNew;
                    dist[bCurr][aTo] = distNew;
                    bfsQ.add(new State(aTo, bCurr, distNew, false));
                }
            }
            for (Map.Entry<Integer, Integer> e : graph[bCurr].entrySet()) {
                int bTo = e.getKey();
                int dDist = e.getValue();
                int distNew = state.dist + dDist;
                if (distNew < dist[aCurr][bTo]) {
                    dist[aCurr][bTo] = distNew;
                    dist[bTo][aCurr] = distNew;
                    bfsQ.add(new State(aCurr, bTo, distNew, false));
                }
            }

        }


        return answer;
    }

    public int solution_(int n, int s, int a, int b, int[][] fares) {

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = MAX_DIST;
            }
        }
        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];
            dist[from][to] = cost;
            dist[to][from] = cost;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            answer = Math.min(dist[s][k] + dist[k][a] + dist[k][b], answer);
        }
        return answer;

    }

    public int solution__(int n, int s, int a, int b, int[][] fares) {

        int[] distS = new int[n + 1];
        int[] distA = new int[n + 1];
        int[] distB = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            distA[i] = MAX_DIST;
            distB[i] = MAX_DIST;
            distS[i] = MAX_DIST;
        }
        loadGraph(n, fares);

        bfs(distS, s);
        bfs(distA, a);
        bfs(distB, b);

        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, distS[k] + distA[k] + distB[k]);
        }
        return answer;

    }

    void bfs(int[] dist, int start) {

        PriorityQueue<Dist> bfsQ = new PriorityQueue<>();
        bfsQ.offer(new Dist(start, 0));
        dist[start] = 0;

        while (!bfsQ.isEmpty()) {
            Dist _dist = bfsQ.remove();
            int node = _dist.node;
            int d = _dist.dist;

            if (dist[node] < d) continue;

            for (Map.Entry<Integer, Integer> e : graph[node].entrySet()) {
                int nodeNext = e.getKey();
                int distNew = d + e.getValue();

                if (distNew < dist[nodeNext]) {
                    dist[nodeNext] = distNew;
                    bfsQ.add(new Dist(nodeNext, distNew));
                }
            }

        }


    }

    class Dist implements Comparable<Dist> {
        int node;
        int dist;

        Dist(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }


        @Override
        public int compareTo(Dist o) {
            return this.dist - o.dist;
        }
    }


    class State implements Comparable<State> {
        int a, b;
        int dist;
        boolean canShare;


        State(int a, int b, int dist, boolean canShare) {
            this.a = a;
            this.b = b;
            this.dist = dist;
            this.canShare = canShare;
        }

        @Override
        public int compareTo(State o) {
            return this.dist - o.dist;
        }

        public boolean isEnd() {
            return (a == aDest && b == bDest) || (a == bDest && b == aDest);
        }


        @Override
        public String toString() {
            return "State{" +
                    "a=" + a +
                    ", b=" + b +
                    ", dist=" + dist +
                    ", canShare=" + canShare +
                    '}';
        }
    }
}

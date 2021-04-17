import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution4 {


    int[][] dist;

    HashMap<Integer, Integer>[] graph;

    int aDest, bDest;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;


        aDest = a;
        bDest = b;

        graph = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashMap<>();


        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int dist = fare[2];
            graph[from].put(to, dist);
            graph[to].put(from, dist);
        }

        PriorityQueue<State> bfsQ = new PriorityQueue<>();
        bfsQ.offer(new State(s, s, 0, true));
        dist[s][s] = 0;


        while (!bfsQ.isEmpty()) {
            State state = bfsQ.remove();
            if (state.isEnd()) return state.dist;

            int aCurr = state.a;
            int bCurr = state.b;

            if (aCurr == bCurr && state.canShare) {
                for (int aTo : graph[aCurr].keySet()) {
                    int distNew = state.dist + graph[aCurr].get(aTo);
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
                    bfsQ.add(new State(aTo, bCurr, distNew, false));
                }
            }

            for (Map.Entry<Integer, Integer> e : graph[bCurr].entrySet()) {
                int bTo = e.getKey();
                int dDist = e.getValue();
                int distNew = state.dist + dDist;
                if (distNew < dist[aCurr][bTo]) {
                    dist[aCurr][bTo] = distNew;
                    bfsQ.add(new State(aCurr, bTo, distNew, false));
                }
            }

        }


        return answer;
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
            return a == aDest && b == bDest;
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

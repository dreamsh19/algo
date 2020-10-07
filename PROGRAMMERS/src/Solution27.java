import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Solution27 {

    static final boolean FINISH = true;
    static final String START = "ICN";
    HashMap<String, ArrayList<Edge>> adjList;
    Stack<Edge> st;
    int len;


    class Edge {
        String to;
        boolean inStack;

        Edge(String to) {
            this.to = to;
            this.inStack = false;
        }
    }

    boolean dfs(String start) {
        if (st.size() == len) return FINISH;

        for (Edge e : adjList.get(start)) {
            if (!e.inStack) {
                e.inStack = true;
                st.push(e);
                if (dfs(e.to) == FINISH) return FINISH;
                st.pop();
                e.inStack = false;
            }
        }
        return !FINISH;
    }


    public String[] solution(String[][] tickets) {
        len = tickets.length;
        adjList = new HashMap<>();
        st = new Stack<>();

        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            adjList.putIfAbsent(from, new ArrayList<>());
            adjList.get(from).add(new Edge(to));
            adjList.putIfAbsent(to, new ArrayList<>());
        }
        for (String key : adjList.keySet()) {
            ArrayList<Edge> tmp = adjList.get(key);
            Collections.sort(tmp, (o1, o2) -> o1.to.compareTo(o2.to));
        }

        dfs(START);

        String[] answer = new String[len + 1];
        for (int i = len; i >= 1; i--) {
            answer[i] = st.pop().to;
        }
        answer[0] = START;

        return answer;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution27 {
    HashMap<String, ArrayList<Node>> adjList;
    ArrayList<String> ans;
    int len;

    class Node {
        String to;
        boolean visited;

        Node(String to) {
            this.to = to;
            this.visited = false;
        }
    }

    void dfs(String start) {
        if (ans.size() == len + 1) return;
        ans.add(start);
        for (Node adjNode : adjList.get(start)) {
            if (!adjNode.visited) {
                adjNode.visited = true;
                dfs(adjNode.to);
                if (ans.size() == len + 1) break;
                ans.remove(ans.size() - 1);
                adjNode.visited = false;
            }
        }


    }


    public String[] solution(String[][] tickets) {
        len = tickets.length;
        adjList = new HashMap<>();
        ans = new ArrayList<>();
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            ArrayList<Node> tmp = adjList.getOrDefault(from, new ArrayList<>());
            tmp.add(new Node(to));
            adjList.put(from, tmp);
            adjList.putIfAbsent(to, new ArrayList<>());
        }
        for (String key : adjList.keySet()) {
            ArrayList<Node> tmp = adjList.get(key);
            Collections.sort(tmp, (o1, o2) -> o1.to.compareTo(o2.to));
        }

        dfs("ICN");

        String[] answer = new String[len + 1];
        for (int i = 0; i <= len; i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }
}

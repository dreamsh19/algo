import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution5 {

    class Node implements Comparable<Node> {
        int id;
        int value;
        int height;
        Node left, right;

        Node(int id, int value, int height) {
            this.id = id;
            this.value = value;
            this.height = height;
        }

        void insertNode(Node node) {
            if (this.compareTo(node) > 0) {
                if (left == null) {
                    this.left = node;
                } else {
                    left.insertNode(node);
                }
            } else {
                if (right == null) {
                    this.right = node;
                } else {
                    right.insertNode(node);
                }
            }
        }

        ArrayList<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            preOrder(result, this);
            return result;
        }

        void preOrder(ArrayList<Integer> result, Node root) {
            if (root == null) return;
            result.add(root.id);
            preOrder(result, root.left);
            preOrder(result, root.right);
        }

        ArrayList<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            postOrder(result, this);
            return result;
        }

        void postOrder(ArrayList<Integer> result, Node root) {
            if (root == null) return;
            postOrder(result, root.left);
            postOrder(result, root.right);
            result.add(root.id);
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", value=" + value +
                    ", height=" + height +
                    '}';
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o.height));
        int n = nodeinfo.length;
        for (int i = 0; i < n; i++) {
            int[] node = nodeinfo[i];
            pq.add(new Node(i + 1, node[0], node[1]));
        }
        Node root = pq.remove();

        while (!pq.isEmpty()) {
            root.insertNode(pq.remove());
        }

        int[][] answer = new int[2][n];
        int idx0 = 0, idx1 = 0;
        for (int id : root.preOrder()) answer[0][idx0++] = id;
        for (int id : root.postOrder()) answer[1][idx1++] = id;

        return answer;
    }
}

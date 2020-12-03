import java.util.ArrayList;
import java.util.Arrays;
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

    void insertNode(Node root, Node node) {
        if (root.compareTo(node) > 0) {
            if (root.left == null) {
                root.left = node;
                return;
            } else {
                insertNode(root.left, node);
            }
        } else {
            if (root.right == null) {
                root.right = node;
                return;
            } else {
                insertNode(root.right, node);
            }
        }

    }

    ArrayList<Node> preorder, postorder;

    void preorder(Node root) {
        if (root == null) return;
        preorder.add(root);
        preorder(root.left);
        preorder(root.right);
    }

    void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        postorder.add(root);

    }


    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> -o.height)));
        int n = nodeinfo.length;
        for (int i = 0; i < n; i++) {
            int[] node = nodeinfo[i];
            pq.add(new Node(i + 1, node[0], node[1]));
        }
        Node root = pq.remove();

        while (!pq.isEmpty()) {
            insertNode(root, pq.remove());
        }


        preorder = new ArrayList<>();
        postorder = new ArrayList<>();

        preorder(root);
        postorder(root);

        int[][] answer = new int[2][n];
        int idx = 0;
        for (Node node : preorder) {
            answer[0][idx++] = node.id;
        }
        idx = 0;
        for (Node node : postorder) {
            answer[1][idx++] = node.id;
        }

        return answer;
    }
}

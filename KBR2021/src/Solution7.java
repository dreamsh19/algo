import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution7 {


    HashMap<Integer, ArrayList<Node>> heightMap;

    public int solution(int[] sales, int[][] links) {

        Node[] nodes = new Node[sales.length + 1];
        for (int i = 0; i < sales.length; i++) {
            nodes[i + 1] = new Node(sales[i]);
        }


        for (int[] link : links) {
            int parent = link[0];
            int child = link[1];
            nodes[parent].childList.add(nodes[child]);
        }

        heightMap = new HashMap<>();
        calHeight(nodes[1]);

        ArrayList<Integer> heightList = new ArrayList<>(heightMap.keySet());
        Collections.sort(heightList, Collections.reverseOrder());
        for (int h : heightList) {
            ArrayList<Node> nodeList = heightMap.get(h);
            for (Node node : nodeList) {
                node.calOut();
                node.calIn();
                node.out = Math.min(node.in, node.out);

            }
        }


        Node head = nodes[1];
        return Math.min(head.in, head.out);

    }

    void calHeight(Node head) {

        int height = head.height;
        ArrayList<Node> arr = heightMap.getOrDefault(height, new ArrayList<>());
        arr.add(head);
        heightMap.put(height, arr);

        if (head.childList.isEmpty()) return;

        for (Node child : head.childList) {
            child.height = height + 1;
            calHeight(child);
        }

    }

    class Node {

        ArrayList<Node> childList;
        int in, out;
        int value;
        int height;


        Node(int value) {
            childList = new ArrayList<>();
            this.value = value;
        }

        void calIn() {
            in = value;

            if (childList.isEmpty()) {
                return;
            }
            for (Node child : childList) {
                in += child.out;
            }
        }

        void calOut() {
            if (childList.isEmpty()) return;

            int min = Integer.MAX_VALUE;
            for (Node childIn : childList) {
                int tmp = 0;
                for (Node childOut : childList) {
                    if (childIn == childOut) {
                        tmp += childIn.in;
                    } else {
                        tmp += childOut.out;
                    }
                }
                min = Math.min(min, tmp);
            }
            out = min;
        }

        @Override
        public String toString() {
            return "Node{" +
//                    "childList=" + childList +
                    ", in=" + in +
                    ", out=" + out +
                    ", value=" + value +
                    ", height=" + height +
                    '}';
        }
    }
}
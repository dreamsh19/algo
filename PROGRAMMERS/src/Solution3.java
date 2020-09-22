import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {
    class Truck {

        int weight;
        int position;

        Truck(int w, int p) {
            this.weight = w;
            this.position = p;
        }

        void goForward() {
            position++;
        }

        @Override
        public String toString() {
            return "Truck{" +
                    "weight=" + weight +
                    ", position=" + position +
                    '}';
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int time = 1;
        int truckFinished = 0;
        Queue<Truck> onBridge = new LinkedList<>();
        Queue<Truck> waiting = new LinkedList<>();
        for (int w : truck_weights) {
            waiting.offer(new Truck(w, 0));
        }

        Truck first = waiting.poll();
        onBridge.offer(first);
        int weightLeft = weight - first.weight;

        while (truckFinished < truck_weights.length) {
            for (Truck t : onBridge) {
                t.goForward();
            }
            if (onBridge.peek().position >= bridge_length) {
                Truck finish = onBridge.poll();
                weightLeft += finish.weight;
                truckFinished++;
            }

            if (!waiting.isEmpty() && weightLeft >= waiting.peek().weight) {
                Truck start = waiting.poll();
                weightLeft -= start.weight;
                onBridge.offer(start);
            }
            time++;
        }

        return time;
    }
}

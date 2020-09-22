import java.util.ArrayList;

public class Solution3 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int nextTruck = 0;
        int weightLeft = weight;
        int time;
        int truckFinished = 0;
        int[] onBridge = new int[bridge_length + 1];
        for (time = 0; truckFinished < truck_weights.length; time++) {
            for (int i = onBridge.length - 2; i >= 0; i--) {
                onBridge[i + 1] = onBridge[i];
            }
            if (onBridge[bridge_length] > 0) {
                weightLeft += onBridge[bridge_length];
                truckFinished++;
            }
            if (nextTruck < truck_weights.length && weightLeft >= truck_weights[nextTruck]) {
                weightLeft -= truck_weights[nextTruck];
                onBridge[0] = truck_weights[nextTruck++];
            } else {
                onBridge[0] = 0;
            }

        }

        return time;
    }
}

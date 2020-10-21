import java.util.Arrays;

class Solution6 {
    int numPoints;
    int[] diff;
    
    int nextIdx(int worker, int from) {
        int sum = 0;
        int i;
        for (i = from; i < numPoints; i++) {
            sum += diff[i];
            if (sum > worker) return i + 1;
        }
        return numPoints;
    }

    boolean canFixAll(int[] dist, int r) {
        for (int shift = 0; shift < numPoints; shift++) {
            int idx = 0;
            for (int i = 0; i <= r; i++) {
                if ((idx = nextIdx(dist[i], idx)) == numPoints) return true;
            }
            shift(diff);
        }
        return false;
    }

    boolean permute(int[] dist, int l, int r) {
        if (l >= r) {
            return canFixAll(dist, r);
        } else {
            for (int i = l; i <= r; i++) {
                swap(dist, l, i);
                if (permute(dist, l + 1, r)) return true;
                swap(dist, l, i);
            }
            return false;
        }
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    void shift(int[] arr) {
        int tmp = arr[0];
        int i;
        for (i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[i] = tmp;
    }

    public int solution(int n, int[] weak, int[] dist) {
        numPoints = weak.length;
        int numWorkers = dist.length;
        Arrays.sort(dist);
        for (int i = 0; i <= (numWorkers / 2 - 1); i++) swap(dist, i, numWorkers - 1 - i);

        diff = new int[numPoints];

        for (int i = 0; i < numPoints - 1; i++) {
            diff[i] = weak[i + 1] - weak[i];
        }
        diff[numPoints - 1] = n + weak[0] - weak[numPoints - 1];

        for (int workerNum = 0; workerNum < numWorkers; workerNum++) {
            if (permute(dist, 1, workerNum)) return workerNum + 1;
        }

        return -1;
    }
}
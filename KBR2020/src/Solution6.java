import java.util.Arrays;

class Solution6 {
    int numPoints;
    int[] diff;

    int nextIdx(int worker, int from, int end) {
        int sum = 0;
        for (int i = from; i < end; i++) {
            sum += diff[i];
            if (sum > worker) return i + 1;
        }
        return end;
    }

    boolean canFixAll(int[] dist, int r) {
        for (int diffHead = 0; diffHead < numPoints; diffHead++) {
            int start = diffHead;
            int end = start + numPoints;
            for (int i = 0; i <= r; i++) {
                if ((start = nextIdx(dist[i], start, end)) == end) return true;
            }
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

    void getDiff(int n, int[] weak) {
        diff = new int[2 * numPoints];

        for (int i = 0; i < numPoints - 1; i++) {
            diff[i] = weak[i + 1] - weak[i];
        }
        diff[numPoints - 1] = n + weak[0] - weak[numPoints - 1];
        System.arraycopy(diff, 0, diff, numPoints, numPoints);

    }

    public int solution(int n, int[] weak, int[] dist) {
        numPoints = weak.length;
        int numWorkers = dist.length;
        Arrays.sort(dist);
        for (int i = 0; i <= (numWorkers / 2 - 1); i++) swap(dist, i, numWorkers - 1 - i);

        getDiff(n, weak);

        for (int workerNum = 0; workerNum < numWorkers; workerNum++) {
            if (permute(dist, 1, workerNum)) return workerNum + 1;
        }

        return -1;
    }
}
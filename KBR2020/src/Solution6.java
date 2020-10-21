import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

class Solution6 {
    int numPoints;
    int[] diff;
    HashSet<ArrayList<Integer>> permutations;

    int nextIdx(int worker, int from) {
        int sum = 0;
        int i;
        for (i = from; i < numPoints; i++) {
            sum += diff[i];
            if (sum > worker) return i + 1;
        }
        return numPoints;
    }


    void permute(ArrayList<Integer> dist, int l, int r) {
        if (l == r) {
            permutations.add((ArrayList<Integer>) dist.clone());
        } else {
            for (int i = l; i <= r; i++) {
                Collections.swap(dist, l, i);
                permute(dist, l + 1, r);
                Collections.swap(dist, l, i);
            }
        }
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
        ArrayList<Integer> totalWorkers = new ArrayList<>();
        for (int d : dist) {
            totalWorkers.add(d);
        }
        Collections.sort(totalWorkers, Collections.reverseOrder());

        diff = new int[numPoints];

        for (int i = 0; i < numPoints - 1; i++) {
            diff[i] = weak[i + 1] - weak[i];
        }
        diff[numPoints - 1] = n + weak[0] - weak[numPoints - 1];

        ArrayList<Integer> workingList = new ArrayList<>();
        for (int workerNum = 0; workerNum < dist.length; workerNum++) {
            workingList.add(totalWorkers.get(workerNum));
            permutations = new HashSet<>();
            permute(workingList, 0, workerNum);
            for (int shiftCount = 0; shiftCount < numPoints; shiftCount++) {
                for (ArrayList<Integer> workers : permutations) {
                    int idx = 0;
                    for (int worker : workers) {
                        idx = nextIdx(worker, idx);
                        if (idx == numPoints) return workerNum + 1;
                    }

                }
                shift(diff);
            }
        }

        return -1;
    }
}
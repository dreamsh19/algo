import java.util.ArrayList;

public class Solution2 {

    class Stage implements Comparable<Stage> {
        int id;
        double failRate;

        Stage(int id, int failNum, int totalNum) {
            this.id = id;
            failRate = totalNum == 0 ? 0 : (double) failNum / totalNum;
        }

        @Override
        public int compareTo(Stage o) {
            double diff = o.failRate - failRate;
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return id - o.id;
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] count = new int[N + 2];
        for (int stage : stages) count[stage]++;

        ArrayList<Stage> stageList = new ArrayList<>(N);
        int totalNum = count[N + 1];
        for (int i = N; i > 0; i--) {
            stageList.add(new Stage(i, count[i], totalNum += count[i]));
        }
        stageList.sort(null);
        int[] result = new int[N];
        int idx = 0;
        for (Stage stage : stageList) result[idx++] = stage.id;

        return result;
    }
}

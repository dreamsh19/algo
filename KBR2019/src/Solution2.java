import java.util.Arrays;

public class Solution2 {

    class Stage implements Comparable<Stage> {
        int id;
        double failRate;

        Stage(int id, int failNum, int totalNum) {
            this.id = id;
            this.failRate = totalNum == 0 ? 0 : (double) failNum / totalNum;
        }

        @Override
        public int compareTo(Stage o) {
            double diff = o.failRate - failRate;
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return id - o.id;
        }

        @Override
        public String toString() {
            return "Stage{" +
                    "id=" + id +
                    ", failRate=" + failRate +
                    '}';
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] count = new int[N + 2];
        for (int stage : stages) {
            count[stage]++;
        }

        Stage[] stageList = new Stage[N + 1];

        stageList[0] = new Stage(0, -1, 1);
        int totalNum = count[N + 1];
        for (int i = N; i > 0; i--) {
            totalNum += count[i];
            stageList[i] = new Stage(i, count[i], totalNum);
        }
        Arrays.sort(stageList);
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = stageList[i].id;
        }

        return result;
    }
}

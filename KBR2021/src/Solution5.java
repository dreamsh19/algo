public class Solution5 {
    public String solution(String play_time, String adv_time, String[] logs) {


        int totalLength = str2sec(play_time);

        int[] numRuns = new int[totalLength + 1];

        for (String log : logs) {
            String[] tokens = log.split("-");
            int startsAt = str2sec(tokens[0]);
            int endsAt = str2sec(tokens[1]);
            numRuns[startsAt]++;
            numRuns[endsAt]--;
        }


        for (int i = 1; i <= totalLength; i++) {
            numRuns[i] += numRuns[i - 1];
        }

        int adSec = str2sec(adv_time);
        long runSum = 0;
        for (int i = 0; i < adSec; i++) {
            runSum += numRuns[i];
        }

        long maxRunSum = runSum;
        int maxI = 0;


        for (int i = 1, j = i + adSec - 1; j <= totalLength; i++, j++) {
            runSum += (numRuns[j] - numRuns[i - 1]);

            if (runSum > maxRunSum) {
                maxI = i;
                maxRunSum = runSum;
            }
        }

        return sec2str(maxI);
    }

    int str2sec(String s) {
        String[] tokens = s.split(":");
        return Integer.parseInt(tokens[0]) * 3600
                + Integer.parseInt(tokens[1]) * 60
                + Integer.parseInt(tokens[2]);
    }

    String sec2str(int sec) {
        int h = sec / 3600;
        sec %= 3600;
        int m = sec / 60;
        sec %= 60;
        int s = sec;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}

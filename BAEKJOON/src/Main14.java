import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main14 {

    static final int CLOCKWISE = 1, COUNTERCLOCKWISE = -1, DO_NOTHING = 0;

    class Wheel {
        int head;
        int[] arr;

        Wheel() {
            arr = new int[8];
        }

        Wheel(String s) {
            this();
            char[] tmp = s.toCharArray();
            for (int i = 0; i < 8; i++) {
                arr[i] = tmp[i] - '0';
            }
        }

        void rotate(boolean isClockwise) {
            head += isClockwise ? 7 : 1;
            head %= 8;
        }

        void action(int act) {
            if (act == CLOCKWISE) rotate(true);
            else if (act == COUNTERCLOCKWISE) rotate(false);
        }

        int getRight() {
            return arr[(head + 2) % 8];
        }

        int getLeft() {
            return arr[(head + 6) % 8];
        }

        int getHead() {
            return arr[head];
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < 8; i++) {
                s += arr[(head + i) % 8];
            }
            return "Wheel{" +
                    "head=" + head +
                    ", arr=" + s +
                    '}';
        }
    }

    int solution() throws IOException {
        Wheel[] wheels = new Wheel[5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 4; i++) {
            wheels[i] = new Wheel(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            String[] line = br.readLine().split(" ");
            int i = Integer.parseInt(line[0]);
            int act = Integer.parseInt(line[1]);
            int[] actions = new int[5];
            actions[i] = act;
            for (int left = i - 1; left > 0; left--) {
                if (actions[left + 1] != DO_NOTHING && wheels[left].getRight() != wheels[left + 1].getLeft())
                    actions[left] = -actions[left + 1];
            }

            for (int right = i + 1; right <= 4; right++) {
                if (actions[right - 1] != DO_NOTHING && wheels[right].getLeft() != wheels[right - 1].getRight())
                    actions[right] = -actions[right - 1];
            }
            for (int j = 1; j <= 4; j++) {
                wheels[j].action(actions[j]);
            }
        }
        int score = 0;
        for (int i = 4; i >= 1; i--) {
            score <<= 1;
            score += wheels[i].getHead();
        }
        return score;
    }


    public static void main(String[] args) throws IOException {

        Main14 m = new Main14();
        int ans = m.solution();
        System.out.println(ans);
    }
}

import java.util.HashSet;

public class Solution29 {

    static final int MAX = 8;

    public int solution(int N, int number) {
        HashSet<Integer>[] possibleNum = new HashSet[MAX + 1];
        HashSet<Integer> unionSet = new HashSet<>();
        int first = 0;
        for (int i = 1; i <= MAX; i++) {
            possibleNum[i] = new HashSet<>();
            first = 10 * first + N;
            possibleNum[i].add(first);
            for (int j = 1; j <= i / 2; j++) {
                HashSet<Integer> num1 = possibleNum[j];
                HashSet<Integer> num2 = possibleNum[i - j];
                for (int n1 : num1) {
                    for (int n2 : num2) {
                        possibleNum[i].add(n1 + n2);
                        possibleNum[i].add(n1 * n2);
                        possibleNum[i].add(n1 - n2);
                        possibleNum[i].add(n2 - n1);
                        if (n1 != 0) possibleNum[i].add(n2 / n1);
                        if (n2 != 0) possibleNum[i].add(n1 / n2);
                    }
                }
            }
            if (possibleNum[i].contains(number)) return i;

            possibleNum[i].removeAll(unionSet);
            unionSet.addAll(possibleNum[i]);

        }

        return -1;

    }
}

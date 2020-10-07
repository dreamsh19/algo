import java.util.HashSet;

public class Solution29 {

    public int solution(int N, int number) {
        HashSet<Integer>[] possibleNum = new HashSet[9];
        int first = 0;
        for (int i = 1; i <= 8; i++) {
            possibleNum[i] = new HashSet<>();
            first = 10 * first + N;
            possibleNum[i].add(first);
            for (int j = 1; j < i; j++) {
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
        }

        return -1;

    }
}

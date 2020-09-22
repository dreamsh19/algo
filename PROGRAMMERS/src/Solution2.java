public class Solution2 {
    public int solution(int[] numbers, int target) {
        if (numbers.length == 0) return target == 0 ? 1 : 0;
        int[] nextNumbers = new int[numbers.length - 1];
        int firstNum = numbers[0];
        for (int i = 0; i < nextNumbers.length; i++) {
            nextNumbers[i] = numbers[i + 1];
        }
        return solution(nextNumbers, target + firstNum) + solution(nextNumbers, target - firstNum);
    }
}
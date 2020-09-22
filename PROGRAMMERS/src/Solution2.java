public class Solution2 {
    int findSol(int[] numbers, int startIdx, int target) {
        if (startIdx == numbers.length) return target == 0 ? 1 : 0;
        int firstNum = numbers[startIdx];
        return findSol(numbers, startIdx + 1, target + firstNum) + findSol(numbers, startIdx + 1, target - firstNum);
    }

    public int solution(int[] numbers, int target) {
        return findSol(numbers, 0, target);
    }
}
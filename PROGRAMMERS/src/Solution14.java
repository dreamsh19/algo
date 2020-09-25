public class Solution14 {

    int maxSum(int[][] triangle, int floor, int idx) {
        if (floor == triangle.length-1) return triangle[floor][idx];
        return triangle[floor][idx] + Math.max(maxSum(triangle, floor + 1, idx), maxSum(triangle, floor + 1, idx + 1));
    }

    public int solution(int[][] triangle) {
        return maxSum(triangle, 0, 0);
    }
}

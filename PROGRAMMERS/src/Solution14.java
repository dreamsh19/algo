public class Solution14 {

    public int solution(int[][] triangle) {
        for (int floor = triangle.length - 2; floor >= 0; floor--) {
            for (int i = 0; i < triangle[floor].length; i++) {
                triangle[floor][i] += Math.max(triangle[floor + 1][i], triangle[floor + 1][i + 1]);
            }
        }
        return triangle[0][0];
    }
}

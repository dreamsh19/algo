import java.util.ArrayList;

public class Solution14 {

    public int solution(int[][] triangle) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= triangle.length; i++) ans.add(0);
        for (int floor = triangle.length - 1; floor >= 0; floor--) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < triangle[floor].length; i++) {
                tmp.add(triangle[floor][i] + Math.max(ans.get(i), ans.get(i + 1)));
            }
            ans = tmp;
        }
        return ans.get(0);
    }
}

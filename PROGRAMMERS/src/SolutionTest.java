public class SolutionTest {

    static void printIntArray(int[] arr) {
        String s = "[";
        for (int a : arr) {
            s += a + ",";
        }
        s = s.substring(0, s.length() - 1) + "]";
        System.out.println(s);
    }

    public static void main(String[] args) {
        int T;

//        //Solution1
//        T = 3;
//        int[] brown = {10, 8, 24};
//        int[] yellow = {2, 1, 24};
//        Solution1 sol = new Solution1();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(brown[i],yellow[i]);
//            printIntArray(ans);
//        }

//        // Solution2
//        T = 1;
//        int[][] numbers = {{1, 1, 1, 1, 1}};
//        int[] targets = {3};
//        Solution2 sol = new Solution2();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(numbers[i], targets[i]);
//            System.out.println(ans);
//        }

//        // Solution3
//        T = 3;
//        int[] bridge_lengths = {2,100,100};
//        int[] weights = {10,100,100};
//        int[][] truck_weights ={{7,4,5,6},{10},{10,10,10,10,10,10,10,10,10,10}};
//        Solution3 sol = new Solution3();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(bridge_lengths[i], weights[i],truck_weights[i]);
//            System.out.println(ans);
//        }

//        // Solution4
//        T = 1;
//        int[][] prices = {{1, 2, 3, 2, 3}};
//        Solution4 sol = new Solution4();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(prices[i]);
//            printIntArray(ans);
//        }

//        // Solution5
//        T = 1;
//        String[][] genres = {{"classic", "pop", "classic", "classic", "pop"}};
//        int[][] plays = {{500, 600, 150, 800, 2500}};
//        Solution5 sol = new Solution5();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(genres[i], plays[i]);
//            printIntArray(ans);
//        }

//        // Solution6
//        T = 2;
//        String[][][] clothes = {{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}},
//                {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}};
//        Solution6 sol = new Solution6();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(clothes[i]);
//            System.out.println(ans);
//        }

        // Solution7
        T = 2;
        int[][] arrows = {
                {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0},
                {0, 4}
        };
        Solution7 sol = new Solution7();
        for (int i = 0; i < T; i++) {
            int ans = sol.solution(arrows[i]);
            System.out.println(ans);
        }


    }


}



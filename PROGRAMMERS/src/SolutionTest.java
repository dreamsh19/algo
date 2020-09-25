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

//        // Solution7
//        T = 3;
//        int[][] arrows = {
//                {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0},
//                {0, 4},
//                {0, 2, 4, 6, 0, 2, 4, 6}
//        };
//        Solution7 sol = new Solution7();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(arrows[i]);
//            System.out.println(ans);
//        }

//        // Solution8
//        T = 2;
//        int[][] scovilles = {
//                {1, 2, 3, 9, 10, 12},
//                {1, 1},
//        };
//        int[] K = {7, 7};
//        Solution8 sol = new Solution8();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(scovilles[i], K[i]);
//            System.out.println(ans);
//        }

//        // Solution9
//        T = 1;
//        int[][] array = {{1, 5, 2, 6, 3, 7, 4}};
//        int[][][] commands = {{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}};
//        Solution9 sol = new Solution9();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(array[i], commands[i]);
//            printIntArray(ans);
//        }

//        // Solution10
//        T = 1;
//        int[][][] routes = {{{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}}};
//        Solution10 sol = new Solution10();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(routes[i]);
//            System.out.println(ans);
//        }

//        // Solution11
//        T = 2;
//        int[][] answers = {{1, 2, 3, 4, 5}, {1, 3, 2, 4, 2}};
//        Solution11 sol = new Solution11();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(answers[i]);
//            printIntArray(ans);
//        }

//        // Solution12
//        T = 1;
//        int[][] citations = {{3,0,6,1,5}};
//        Solution12 sol = new Solution12();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(citations[i]);
//            System.out.println(ans);
//        }

//        // Solution13
//        T = 2;
//        String[][] operations = {{"I 16", "D 1"}, {"I 7", "I 5", "I -5", "D -1"}};
//        Solution13 sol = new Solution13();
//        for (int i = 0; i < T; i++) {
//            int[] ans = sol.solution(operations[i]);
//            printIntArray(ans);
//        }

//        // Solution14
//        T = 1;
//        int[][][] triangles = {{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}};
//        Solution14 sol = new Solution14();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(triangles[i]);
//            System.out.println(ans);
//        }

//        // Solution15
//        T = 3;
//        String[] numbers = {"1924", "1231234", "4177252841"};
//        int[] k = {2, 3, 4};
//        Solution15 sol = new Solution15();
//        for (int i = 0; i < T; i++) {
//            String ans = sol.solution(numbers[i], k[i]);
//            System.out.println(ans);
//        }

        // Solution16
        T = 2;
        int[][] priorities = {{2, 1, 3, 2},{1, 1, 9, 1, 1, 1}}	;
        int[] location = {2, 0};
        Solution16 sol = new Solution16();
        for (int i = 0; i < T; i++) {
            int ans = sol.solution(priorities[i],location[i]);
            System.out.println(ans);
        }

    }


}



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

//        // Solution16
//        T = 2;
//        int[][] priorities = {{2, 1, 3, 2}, {1, 1, 9, 1, 1, 1}};
//        int[] location = {2, 0};
//        Solution16 sol = new Solution16();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(priorities[i], location[i]);
//            System.out.println(ans);
//        }

//        //Solution17
//        T = 1;
//        int[] n = {6};
//        int[][][] vertex = {{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}};
//        Solution17 sol = new Solution17();
//        for (int i = 0; i < T; i++) {
//            int ans = sol.solution(n[i],vertex[i]);
//            System.out.println(ans);
//        }

//        //Solution18
//        T = 3;
//        String[] begin = {"hit", "hit","hit"};
//        String[] target = {"cog", "cog","zzz"};
//        String[][] words = {{"hot", "dot", "dog", "lot", "log", "cog"}, {"hot", "dot", "dog", "lot", "log"}, {"hot", "dot", "dog", "lot", "log","zzz"}};
//        for (int i = 0; i < T; i++) {
//            Solution18 sol = new Solution18();
//            int ans = sol.solution(begin[i], target[i], words[i]);
//            System.out.println(ans);
//        }

//        // Solution19
//        T = 3;
//        String[][] participant = {{"leo", "kiki", "eden"},
//                {"marina", "josipa", "nikola", "vinko", "filipa"},
//                {"mislav", "stanko", "mislav", "ana"}
//        };
//        String[][] completion = {{"eden", "kiki"}, {"josipa", "filipa", "marina", "nikola"}, {"stanko", "ana", "mislav"}};
//        for (int i = 0; i < T; i++) {
//            Solution19 sol = new Solution19();
//            String ans = sol.solution(participant[i], completion[i]);
//            System.out.println(ans);
//        }

//        // Solution20
//        T = 2;
//        int[][] people = {{70, 50, 80, 50}, {70, 80, 50}};
//        int[] limit = {100, 100};
//        for (int i = 0; i < T; i++) {
//            Solution20 sol = new Solution20();
//            int ans = sol.solution(people[i], limit[i]);
//            System.out.println(ans);
//        }


//        // Solution21
//        T = 2;
//        int[] n = {5, 7};
//        int[][][] results = {{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}},
//                {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}
//        };
//        for (int i = 0; i < T; i++) {
//            Solution21 sol = new Solution21();
//            int ans = sol.solution_(n[i], results[i]);
//            System.out.println(ans);
//        }

//        // Solution22
//        T = 1;
//        int[] m = {4};
//        int[] n = {3};
//        int[][][] puddles = {{{2, 2}}};
//        for (int i = 0; i < T; i++) {
//            Solution22 sol = new Solution22();
//            int ans = sol.solution(m[i], n[i], puddles[i]);
//            System.out.println(ans);
//        }

//        // Solution23
//        T = 3;
//        String[][] phone_book = {{"119", "97674223", "1195524421"}, {"123","456","789"}, {"12","123","1235","567","88"}};			;
//        for (int i = 0; i < T; i++) {
//            Solution23 sol = new Solution23();
//            boolean ans = sol.solution(phone_book[i]);
//            System.out.println(ans);
//        }

//        // Solution24
//        T = 6;
//        int[] n = {6, 1, 2, 10, 2, 2};
//
//        int[][] times = {{7, 10}, {5, 5}, {5, 6, 6, 6}, {2, 4, 4, 4, 4}, {5, 5, 6},
//                {1000000000}
//        };
//        for (int i = 0; i < T; i++) {
//            Solution24 sol = new Solution24();
//            long ans = sol.solution(n[i], times[i]);
//            System.out.println(ans);
//        }

//        // Solution25
//        T = 2;
//        int[][][] jobs = {{{0,3},{1,9},{2,6}},{{1,3},{1,9},{2,6}}};
//        for (int i = 0; i < T; i++) {
//            Solution25 sol = new Solution25();
//            int ans = sol.solution(jobs[i]);
//            System.out.println(ans);
//        }

//        // Solution26
//        T = 1;
//        int[] n = {4};
//        int[][][] costs = {{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}};
//        for (int i = 0; i < T; i++) {
//            Solution26 sol = new Solution26();
//            int ans = sol.solution(n[i], costs[i]);
//            System.out.println(ans);
//        }

        // Solution27
        T = 3;
        String[][][] tickets = {{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}},
                {{"ICN", "DEF"}, {"DEF", "GHI"}, {"GHI", "JKL"}, {"JKL", "ICN"}, {"ICN", "ABC"}}
        };
        for (int i = 0; i < T; i++) {
            Solution27 sol = new Solution27();
            String[] ans = sol.solution(tickets[i]);
            for (String s : ans) {
                System.out.print(s + " ");
            }
            System.out.println();
        }


    }


}



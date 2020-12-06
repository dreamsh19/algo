import java.util.Arrays;
import java.util.Scanner;

public class SolutionTest {

    static void print(Object o) {

        String s;
        if (o.getClass().isArray()) {
            if (o instanceof int[]) s = Arrays.toString((int[]) o);
            else s = Arrays.deepToString((Object[]) o);
        } else {
            s = o.toString();
        }
        System.out.println(s);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("SOLUTION NUMBER TO TEST : ");
        int SOLUTION_NUMBER = sc.nextInt();
        sc.close();

        switch (SOLUTION_NUMBER) {
            case 1: {
                String[][] records = {{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"}};
                Solution1 s = new Solution1();
                for (int t = 0; t < records.length; t++) print(s.solution(records[t]));
            }
            break;
            case 2: {
                int[] N = {5, 3};
                Solution2 s = new Solution2();
                int[][] stages = {{2, 1, 2, 6, 2, 4, 3, 3}, {2}};
                for (int t = 0; t < N.length; t++) print(s.solution(N[t], stages[t]));
            }
            break;
            case 3: {
                String[][][] relation = {{
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}}
                };
                Solution3 s = new Solution3();
                for (int t = 0; t < relation.length; t++) print(s.solution_(relation[t]));

            }
            break;
            case 4: {
                int[][] food_times = {{3, 1, 2}};
                long[] k = {5};
                Solution4 s = new Solution4();
                for (int t = 0; t < food_times.length; t++) print(s.solution(food_times[t], k[t]));
            }
            break;
            case 5: {
                int[][][] nodeinfo = {{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}}};
                Solution5 s = new Solution5();
                for (int t = 0; t < nodeinfo.length; t++) {
                    print(s.solution(nodeinfo[t]));
                }
            }
            break;
            case 6: {
                String[] word = {"blind", "Muzi"};
                String[][] pages = {{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"},
                        {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}
                };
                Solution6 s = new Solution6();
                for (int t = 0; t < word.length; t++) print(s.solution(word[t], pages[t]));

            }
            break;
            case 7: {
                Solution7 s = new Solution7();
                int[][][] board = {
                        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5}, {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}},
                        {{0, 0, 0, 0}, {1, 0, 0, 2}, {1, 0, 0, 2}, {1, 1, 2, 2}}
                };
                for (int t = 0; t < board.length; t++) print(s.solution(board[t]));

            }
            break;
            default:
                throw new IllegalArgumentException("No such solution number " + SOLUTION_NUMBER);
        }
    }
}

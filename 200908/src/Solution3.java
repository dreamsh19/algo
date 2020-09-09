/*
자물쇠와 열쇠

문제 설명
고고학자인 튜브는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.

잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.

자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.

열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.

제한사항
key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
M은 항상 N 이하입니다.
key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
0은 홈 부분, 1은 돌기 부분을 나타냅니다.

입출력 예
key	lock	result
[[0, 0, 0], [1, 0, 0], [0, 1, 1]]	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]	true


 */

class Solution3 {
    public boolean solution(int[][] key, int[][] lock) {
        int[][] critical;
        try {
            critical = criticalLock(lock);
        } catch (NegativeArraySizeException e) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (key[i][j] == 0) return true;
                }
            }
            return false;
        }
        if (sol(key, critical)) return true;
        for (int i = 0; i < 3; i++) {
            critical = rotate(critical);
            if (sol(key, critical)) return true;
        }
        return false;
    }

    public boolean sol(int[][] key, int[][] critical) {
        int row_c = critical.length;
        int col_c = critical[0].length;

        for (int move_x = 0; move_x <= key.length - row_c; move_x++) {
            for (int move_y = 0; move_y <= key.length - col_c; move_y++) {
                boolean temp = true;

                for (int i = 0; i < row_c; i++) {
                    for (int j = 0; j < col_c; j++) {
                        if (key[move_x + i][move_y + j] == critical[i][j]) {
                            temp = false;
                            break;
                        }
                    }
                    if (!temp) break;
                }
                if (temp) return true;

            }
        }
        return false;
    }

    public int[][] rotate(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] result = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[j][M - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public int[][] criticalLock(int[][] lock) {
        int N = lock.length;
        int x_min = N, y_min = N, x_max = -1, y_max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) {
                    x_min = Math.min(i, x_min);
                    x_max = Math.max(i, x_max);
                    y_min = Math.min(j, y_min);
                    y_max = Math.max(j, y_max);
                }
            }
        }
        int[][] critical = new int[x_max - x_min + 1][y_max - y_min + 1];
        for (int i = x_min; i <= x_max; i++) {
            for (int j = y_min; j <= y_max; j++) {
                critical[i - x_min][j - y_min] = lock[i][j];
            }
        }
        return critical;
    }

    public static void print(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        Solution3 sol = new Solution3();
        boolean ans = sol.solution(key, lock);
        System.out.println(ans);
    }
}

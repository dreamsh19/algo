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

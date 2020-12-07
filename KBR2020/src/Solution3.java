class Solution3 {

    int M, N;
    int[][] key, lock;

    boolean isInKey(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < M;
    }

    boolean solve(int key_x, int key_y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int ki = i - key_x;
                int kj = j - key_y;

                if (lock[i][j] == 1) {
                    if (isInKey(ki, kj) && key[ki][kj] == 1) return false;
                } else {
                    if (!isInKey(ki, kj) || key[ki][kj] == 0) return false;
                }
            }
        }
        return true;
    }

    public boolean solution(int[][] key, int[][] lock) {

        this.key = key;
        this.lock = lock;
        M = key.length;
        N = lock.length;
        int leftEnd = -M + 1;
        int rightEnd = N - 1 + M - 1;

        for (int i = 0; i < 4; i++) {
            for (int key_x = leftEnd; key_x <= rightEnd; key_x++) {
                for (int key_y = leftEnd; key_y <= rightEnd; key_y++) {
                    if (solve(key_x, key_y)) return true;
                }
            }
            keyRotate();
        }
        return false;
    }

    void keyRotate() {
        int[][] result = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                result[j][M - 1 - i] = key[i][j];
            }
        }
        key = result;
    }
}

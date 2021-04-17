import java.util.ArrayList;

public class Solution7 {

    int[][] board;
    int N;
    int[] columnTop;
    static final int NULL = Integer.MIN_VALUE;
    ArrayList<Block> possibleBlocks;

    interface Block {
        boolean isClearable();

        void clear();
    }

    class HorBlock implements Block {
        int left_x, left_y;
        int dy;
        boolean cleared;

        HorBlock(int x, int y, int dy) {
            left_x = x;
            left_y = y;
            this.dy = dy;
        }

        @Override
        public boolean isClearable() {
            if (cleared) return false;
            int num = 0;
            for (int yy = 0; yy < 3; yy++) {
                int ny = yy + left_y;
                if (yy != dy && columnTop[ny] == left_x - 1) num++;
            }
            return num == 2;
        }

        @Override
        public void clear() {
            for (int ny = left_y; ny < left_y + 3; ny++) {
                board[left_x][ny] = 0;
            }
            board[left_x - 1][left_y + dy] = 0;
            cleared = true;
        }
    }

    class VerBlock implements Block {
        int bottom_x, bottom_y;
        int dy;
        boolean cleared;

        VerBlock(int x, int y, int dy) {
            bottom_x = x;
            bottom_y = y;
            this.dy = dy;
        }

        @Override
        public boolean isClearable() {
            return !cleared && columnTop[bottom_y + dy] == bottom_x - 1;
        }

        @Override
        public void clear() {
            for (int nx = bottom_x; nx > bottom_x - 3; nx--) {
                board[nx][bottom_y] = 0;
            }
            board[bottom_x][bottom_y + dy] = 0;
            cleared = true;
        }
    }

    int isHorBlock(int x, int y) {
        int num = board[x][y];
        try {
            if (num != 0 && board[x][y + 1] == num && board[x][y + 2] == num) {
                for (int topIdx = 0; topIdx < 3; topIdx++) {
                    if (board[x - 1][y + topIdx] == num) return topIdx;
                }
            }
            return NULL;
        } catch (ArrayIndexOutOfBoundsException e) {
            return NULL;
        }
    }

    int isVerBlock(int x, int y) {
        int num = board[x][y];
        try {
            if (num != 0 && board[x - 1][y] == num && board[x - 2][y] == num) {
                if (y - 1 >= 0 && board[x][y - 1] == num) return -1;
                if (y + 1 <= N && board[x][y + 1] == num) return 1;
            }
            return NULL;
        } catch (ArrayIndexOutOfBoundsException e) {

            return NULL;
        }

    }

    void getColumnTop() {
        for (int col = 0; col < N; col++) {
            int x;
            for (x = 0; x < N; x++) {
                if (board[x][col] != 0) break;
            }
            columnTop[col] = x - 1;

        }
    }

    public int solution(int[][] board) {
        this.board = board;
        N = board.length;
        columnTop = new int[N];
        getColumnTop();
        possibleBlocks = new ArrayList<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int dy;
                if ((dy = isHorBlock(x, y)) != NULL) possibleBlocks.add(new HorBlock(x, y, dy));
                if ((dy = isVerBlock(x, y)) != NULL) possibleBlocks.add(new VerBlock(x, y, dy));
            }
        }
        int answer = 0;

        while (true) {
            Block clearableBlock = null;
            for (Block b : possibleBlocks) {
                if (b.isClearable()) {
                    clearableBlock = b;
                    break;
                }
            }
            if (clearableBlock != null) {
                clearableBlock.clear();
                getColumnTop();
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}

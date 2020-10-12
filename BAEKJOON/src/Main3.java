import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {

    static final int EMPTY = 0;
    static final int DOT = 1;
    static final int HOR = 2;
    static final int VER = 3;
    static final int HEIGHT = 6;
    static final int WIDTH = 4;
    static int score;
    static int[][] greenBoard, blueBoard;

    static boolean hasToPush(int[][] board) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] != EMPTY) return true;
            }
        }
        return false;
    }

    static void operation(int[][] board, int type, int x, int y) {
        if (type == DOT) {
            x = 0;
            while (x < HEIGHT && board[x][y] == EMPTY) {
                x++;
            }
            board[x - 1][y] = DOT;

        } else if (type == HOR) {
            x = 0;
            while (x < HEIGHT && board[x][y] == EMPTY && board[x][y + 1] == EMPTY) {
                x++;
            }
            board[x - 1][y] = HOR;
            board[x - 1][y + 1] = HOR;
        } else if (type == VER) {
            x = 0;
            while (x + 1 < HEIGHT && board[x + 1][y] == EMPTY) {
                x++;
            }
            board[x - 1][y] = DOT;
            board[x][y] = DOT;
        }

        while (clearBoard(board)) {
            moveToBottom(board);
        }

        while (hasToPush(board)) {
            pushBoard(board, HEIGHT - 1);
        }

    }

    static void goToBottom(int[][] board, int i, int j) {
        int type = board[i][j];
        if (type == DOT) {
            int next_i = i;
            while (next_i + 1 < HEIGHT && board[next_i + 1][j] == EMPTY) {
                next_i++;
            }
            board[i][j] = EMPTY;
            board[next_i][j] = type;
//            System.out.printf("TYPE[%d]i[%d]j[%d]next_i[%d]\n", type, i, j, next_i);
        } else if (type == HOR && j + 1 < WIDTH && board[i][j + 1] == HOR) {
            int next_i = i;
            while (next_i + 1 < HEIGHT
                    && board[next_i + 1][j] == EMPTY && board[next_i + 1][j + 1] == EMPTY) {
                next_i++;
            }
//            System.out.printf("TYPE[%d]i[%d]j[%d]next_i[%d]\n", type, i, j, next_i);

            board[i][j] = EMPTY;
            board[i][j + 1] = EMPTY;
            board[next_i][j] = type;
            board[next_i][j + 1] = type;
        }

    }

    static void moveToBottom(int[][] board) {
        for (int h = HEIGHT - 2; h >= 0; h--) {
            for (int col = 0; col < WIDTH; col++) {
                goToBottom(board, h, col);
            }
        }


//        for (int col = 0; col < WIDTH; col++) {
//            for (int h = HEIGHT - 2; h >= 0; h--) {
//                while (h + 1 < HEIGHT && board[h][col] == DOT && board[h + 1][col] == EMPTY) {
//                    pushCol(board, col, h + 1);
//                    h++;
//                }
////                while (h + 1 < HEIGHT && col + 1 < WIDTH && board[h][col] == HOR && board[h][col + 1] == HOR
////                        && board[h + 1][col] == EMPTY && board[h + 1][col + 1] == EMPTY) {
////                    pushCol(board, col, h + 1);
////                    pushCol(board, col + 1, h + 1);
////                    h++;
////                }
//            }
//        }
    }

    static void printBoard(int[][] board) {
        String s = "";
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                s += board[i][j] + " ";
            }
            s += '\n';
        }
        System.out.println(s);
    }

    static boolean clearBoard(int[][] board) {
        boolean hasClearable = false;
        for (int h = HEIGHT - 1; h >= 2; ) {
            boolean clearable = true;
            for (int i : board[h]) {
                if (i == EMPTY) {
                    clearable = false;
                    break;
                }
            }
            if (clearable) {
                hasClearable = true;
                score++;
                pushBoard(board, h);
            } else {
                h--;
            }
        }
        return hasClearable;
    }

    static void pushCol(int[][] board, int col, int start_h) {
        for (int h = start_h; h > 0; h--) {
            board[h][col] = board[h - 1][col];
        }
        board[0][col] = EMPTY;

    }

    static void pushBoard(int[][] board, int start_h) {
        for (int j = 0; j < WIDTH; j++) {
            pushCol(board, j, start_h);
        }
    }

    static int getSum() {
        int sum = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (greenBoard[i][j] != EMPTY) sum++;
                if (blueBoard[i][j] != EMPTY) sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        score = 0;
        greenBoard = new int[HEIGHT][WIDTH];
        blueBoard = new int[HEIGHT][WIDTH];
        int N = Integer.parseInt(line[0]);
        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            int type = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[2]);
            operation(greenBoard, type, x, y);
            if (type != DOT) {
                type = type == HOR ? VER : HOR;
            }
            operation(blueBoard, type, y, x);
//            printBoard(greenBoard);
//            printBoard(blueBoard);
        }
        System.out.println(score);
        System.out.println(getSum());

    }
}
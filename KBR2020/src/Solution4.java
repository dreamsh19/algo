import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {
    static final int ROW = 2, COL = 1;

    int[][] wall;

    public boolean hasType(int x, int y, int type) {
        try {
            return (wall[x][y] & type) != 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isBuildable(int x, int y, int type) {
        if (type == ROW)
            return hasType(x, y - 1, COL)
                    || hasType(x + 1, y - 1, COL)
                    || (hasType(x - 1, y, ROW) && hasType(x + 1, y, ROW));
        else
            return y == 0
                    || hasType(x, y - 1, COL)
                    || hasType(x - 1, y, ROW)
                    || hasType(x, y, ROW);
    }


    public boolean isRemovable(int x, int y, int type) {
        if (type == ROW) {
            return (!hasType(x - 1, y, ROW) || isBuildable(x - 1, y, ROW))
                    && (!hasType(x + 1, y, ROW) || isBuildable(x + 1, y, ROW))
                    && (!hasType(x, y, COL) || isBuildable(x, y, COL))
                    && (!hasType(x + 1, y, COL) || isBuildable(x + 1, y, COL));
        } else {
            return (!hasType(x, y + 1, COL) || isBuildable(x, y + 1, COL))
                    && (!hasType(x - 1, y + 1, ROW) || isBuildable(x - 1, y + 1, ROW))
                    && (!hasType(x, y + 1, ROW) || isBuildable(x, y + 1, ROW));
        }
    }

    public int[][] getResult() {
        Queue<Integer> tmp = new LinkedList<>();
        int n = wall.length;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int type = COL; type <= ROW; type++) {
                    if (hasType(i, j, type)) {
                        tmp.add(i);
                        tmp.add(j);
                        tmp.add(type - 1);
                    }

                }
            }
        }
        int len = tmp.size() / 3;
        int[][] result = new int[len][3];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = tmp.poll();
            }
        }
        return result;
    }

    public int[][] solution(int n, int[][] build_frame) {
        wall = new int[n + 1][n + 1];

        for (int[] element : build_frame) {
            int x = element[0];
            int y = element[1];
            int type = element[2] + 1;
            boolean isBuild = element[3] == 1;
            if (isBuild) {
                if (isBuildable(x, y, type)) wall[x][y] |= type;
            } else {
                wall[x][y] &= ~type;
                if (!isRemovable(x, y, type)) wall[x][y] |= type;
            }
        }
        return getResult();

    }
}
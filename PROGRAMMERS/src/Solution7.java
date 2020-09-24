import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Solution7 {

    HashSet<Point> visited;
    HashSet<HashSet<Point>> visitedEdge;

    class Point {
        int x;
        int y;
        HashSet<Point> parents;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.parents = new HashSet<>();
        }

        Point movePoint(int dir) {
            int dx = dir == 0 || dir == 4 ? 0 : (dir > 0 && dir < 4 ? 1 : -1);
            int dy = dir == 2 || dir == 6 ? 0 : (dir > 2 && dir < 6 ? -1 : 1);
            return new Point(x + dx, y + dy);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
//                    ", parent=" + parent +
                    '}';
        }
    }

    public int solution(int[] arrows) {
        int ans = 0;
        visited = new HashSet<>();
        visitedEdge = new HashSet<>();
        Point cur = new Point(0, 0);
        for (int arrow : arrows) {
            for (int r = 0; r < 2; r++) {
                visited.add(cur);
                Point next = cur.movePoint(arrow);
                HashSet<Point> edge = new HashSet<>();
                edge.add(cur);
                edge.add(next);
                if (visited.contains(next) && !visitedEdge.contains(edge)) {
                    ans++;
                }
                visitedEdge.add(edge);
                cur = next;
            }

        }

        return ans;
    }
}
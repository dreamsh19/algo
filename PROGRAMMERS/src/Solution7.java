import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Solution7 {

    HashSet<Point> visited;
    HashSet<Edge> visitedEdge;

    class Edge {
        HashSet<Point> points;

        Edge(Point p1, Point p2) {
            points = new HashSet<>(Arrays.asList(new Point[]{p1, p2}));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(points, edge.points);
        }

        @Override
        public int hashCode() {
            return Objects.hash(points);
        }
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
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
-                    '}';
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
                Edge edge = new Edge(cur, next);
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
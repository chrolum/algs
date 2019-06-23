package PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        argsTest(points);
        this.segments = new ArrayList<>();
        Arrays.sort(points);
        int n = points.length;
        for (int p = 0; p < n; p++) {
            for (int s = p + 1; s < n; s++) {
                for (int r = s + 1; r < n; r++) {
                    for (int q = r + 1; q < n; q++) {
                        if (Double.compare(points[p].slopeTo(points[s]), points[s].slopeTo(points[r])) == 0
                                && Double.compare(points[s].slopeTo(points[r]), points[r].slopeTo(points[q])) == 0) {
//                            System.out.printf("currten point is p:%d -> s:%d -> r:%d -> q:%d\n", p, s, r, q);
                            segments.add(new LineSegment(points[p],points[q]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return this.segments.size();
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[numberOfSegments()]);
    }

    //null and duplicated entry check
    private void argsTest(Point[] points) {
        if (points == null || points[0] == null) throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i] == null || points[i].compareTo(points[i-1]) == 0) throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points)
        {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

package PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.segments = new ArrayList<>();
        for (int p = 0; p < points.length; p++) {
            for (int s = 0; s < points.length; s++) {
                if (points[s].compareTo(points[p]) < 1) continue;
                for (int r = 0; r < points.length; r++) {
                    if (points[r].compareTo(points[s]) < 1 || points[p].slopeTo(points[s]) != points[s].slopeTo(points[r])) continue;//p->s, s->r are co
                    for (int q = 0; q < points.length; q++) {
                        if (points[q].compareTo(points[r]) < 1 || points[s].slopeTo(points[r]) != points[r].slopeTo(points[q])) continue;
                        segments.add(new LineSegment(points[p],points[q]));
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return this.segments.size();
    }

    public LineSegment[] segments()
    {

        return this.segments.toArray(new LineSegment[0]);
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

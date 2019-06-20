package PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Comparator<Point> comp = points[i].slopeOrder();
            Arrays.sort(points, comp);
            //Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to
            int j = 1;
            while (j < points.length) {// the first point is the original point
                double currSloper = points[i].slopeTo(points[j-1]);
                int cnt = 0;
                while (j < points.length && points[i].slopeTo(points[j]) == currSloper) {
                    j++; cnt++;
                }
                j++;
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
    //unit test
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
    }
}

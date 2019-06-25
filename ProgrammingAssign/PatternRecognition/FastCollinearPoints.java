package ProgrammingAssign.PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class FastCollinearPoints {
    private final ArrayList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        argsTest(points);

        this.segments = new ArrayList<>();
        Point[] origin = Arrays.copyOf(points, points.length);

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points);//sort the point by x,y-coondinated
            Arrays.sort(points, origin[i].slopeOrder());

            int start = 0;
            for (int j = 0; j < points.length; j++) {
                if (Double.compare(origin[i].slopeTo(points[start]), origin[i].slopeTo(points[j])) == 0){
                    continue;
                }
                else {
                    if (j - start >= 3 && origin[i].compareTo(points[start]) < 0) {
                        segments.add(new LineSegment(origin[i], points[j-1]));
                    }
                    start = j;
                }
            }
            if (points.length - start >= 3 && origin[i].compareTo(points[start]) < 0) segments.add(new LineSegment(origin[i], points[points.length-1]));
        }
    }

    public int numberOfSegments() {
        return this.segments.size();
    }

    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[0]);
    }

    // args test
    private void argsTest(Point[] points) {
        if (points == null || points[0] == null) throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i] == null || points[i].compareTo(points[i-1]) == 0) throw new IllegalArgumentException();
        }
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

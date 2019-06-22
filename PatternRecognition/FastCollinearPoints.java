package PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.segments = new ArrayList<>();
        Point[] origin = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, origin[i].slopeOrder());

//            for (Point p : points) {
//                System.out.print(p + " ");
//            }

            //Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to
            int start = 0;
            for (int j = 0; j < points.length; j++) {
//                System.out.print(origin[i].slopeTo(points[j]) + " ");
                if (Double.compare(origin[i].slopeTo(points[start]), origin[i].slopeTo(points[j])) == 0) continue;
                else {
                    if (j - start >= 3 && origin[i].compareTo(points[start]) < 0) {
                        segments.add(new LineSegment(origin[i], points[j-1]));
                    }
                    start = j;
                }
            }
//            System.out.println();
            //the last segment if it exist
            if (points.length - start >= 3 && origin[i].compareTo(points[start]) < 0) segments.add(new LineSegment(origin[i], points[points.length-1]));
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

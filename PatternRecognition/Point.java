package PatternRecognition;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(double x, double y) {                     // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw()  {                           // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {                // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {              // compare two points by y-coordinates, breaking ties by x-coordinates
            if (this.y <= that.y && this.x < that.y) { //y0 <= y1 and x0 < x1
                return 1;
            }
            else if (this.y == that.y && this.x == that.x) {
                return 0;
            }
            else return -1;
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {             // the slope between this point and that point
        if (that.x == this.x && that.y == this.y) return Double.NEGATIVE_INFINITY;// the same points
        if (that.y != this.y && that.x == this.x) return Double.POSITIVE_INFINITY;// two points is vertical
        return (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {         // compare two points by slopes they make with this point

    }

    /**
     * units test
     * @param args
     */
    public static void main(String[] args) {
        //case 1: compareTo() test
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(1,1);
        Point p4 = new Point(1,2);

        System.out.println(p1.compareTo(p2));//return 1
        System.out.println(p1.compareTo(p4));//return 1
        System.out.println(p2.compareTo(p3));//return 0
        System.out.println(p3.compareTo(p4));//return -1

        //case2: sloperTo() test
        Point p5 = new Point(0, 1);
        System.out.println("(0, 1) and (0, 0)'s sloper is " + p5.slopeTo(p1));//vertical return POSITIVE_INFINITY
        System.out.println("two the same points's sloper is " + p2.slopeTo(p3));//return NEGATIVE_INFINITY
        Point p6 = new Point(3.14, 2.32);
        Point p7 = new Point(31.14, 23.32);
        System.out.println("p6 and p7's sloper is " + p6.slopeTo(p7));//normal case
        Point p8 = new Point(12, 7.01);
        Point p9 = new Point(13, 7.01);
        System.out.println("the two horizontal points' sloper is " + p8.slopeTo(p9));//horizontal case
        System.out.println("equality test: " + p9.equals(p8));



    }
}



















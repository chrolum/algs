package ProgrammingAssign.kdTree;

import ProgrammingAssign.PatternRecognition.Point;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class PointSet {
    private TreeSet<Point2D> set;

    public PointSet() {
        this.set = new TreeSet<>();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        set.add(p);
    }

    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> res = new LinkedList<>();
        double xmin = rect.xmin(), xmax = rect.xmax(), ymin = rect.ymin(), ymax = rect.ymax();
        for (Point2D p : set) {
            if (rect.contains(p)) res.add(p);
        }
        return res;
    }
    //sweep-line-algs
    public Point2D nearest(Point2D p) {
        return this.set.ceiling(p) < this.set.floor(p) ? this.set.ceiling(p) : this.set.floor(p);

    }
    //unit test
    public static void main(String... args) {

    }
}

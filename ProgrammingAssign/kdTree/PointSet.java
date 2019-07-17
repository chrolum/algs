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
    TreeSet<Point2D> set;
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
        if (!set.contains(p)) {
            set.add(p);
        }
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

    }

    public Point2D nearest(Point2D p) {

    }

    public static void main(String... args) {

    }
}

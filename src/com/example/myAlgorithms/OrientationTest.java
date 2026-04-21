package com.example.myAlgorithms;

public class OrientationTest {
    public static int orientation(Point p1, Point p2, Point p3) {
        return (int)Math.signum((p2.x - p1.x) * (p3.y - p1.y) - (p2.y-p1.y) * (p3.x - p1.x));
    }

    public static void main(String[] args) {
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(1, 2)));
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(0, 2)));
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(-1, 2)));
    }
}

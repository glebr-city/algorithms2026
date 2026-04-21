package com.example.myAlgorithms;

public class OrientationTest {
    /// Takes in three points, and checks the position of the third in relation to the vector between the first and the second. Returns an integer between -1 and 1, to indicate the direction. -1 = right, 0 = in line, 1 = left.
    public static int orientation(Point p1, Point p2, Point p3) {
        return (int)Math.signum((p2.x - p1.x) * (p3.y - p1.y) - (p2.y-p1.y) * (p3.x - p1.x));
    }

    public static void main(String[] args) {
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(1, 2)));
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(0, 2)));
        IO.println(orientation(new Point(0, 0), new Point(0, 1), new Point(-1, 2)));
    }
}

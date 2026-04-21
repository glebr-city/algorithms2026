package com.example.myAlgorithms;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PointInPolygon {
    /// Find if the given point is inside the given polygon by counting its intersections along the X axis.
    public static boolean isInsidePolygon(Point q, Point[] polygon) {
        if (polygon.length < 3) {
            IO.println("A polygon must have at least 3 points!");
            return false;
        }
        ArrayList<Point> polygonList = new ArrayList<>(List.of(polygon));
        polygonList.addLast(polygon[0]);
        int intersections = 0;
        for (int i = 0; i < polygonList.size() - 1; i++) {
            Point p1 = polygonList.get(i);
            Point p2 = polygonList.get(i + 1);
            //Using >= to try and count the intersection, but only once (and not when this same point, p1, is labeled p2).
            if (p1.y >= q.y) {
                if (p2.y < q.y) {
                    if (sufficientGradient(q, p1, p2))
                        intersections++;
                }
            } else if (p2.y > q.y) {
                if (p1.y < q.y) {
                    if (sufficientGradient(q, p2, p1))
                        intersections++;
                }
            }
        }
        return Math.floorMod(intersections, 2) == 1;
    }
    /// Find whether the line is to the right of q at its height.
    private static boolean sufficientGradient (Point q, Point higher, Point lower) {
        Point potentialIntersection = new Point(higher.x - (higher.x - lower.x) / (higher.y - lower.y) * (higher.y - q.y), q.y);
        //Once again, being on the edge counts as "inside."
        return (potentialIntersection.x > q.x);
    }


    public static void main(String[] args) {
        IO.println("Square, point inside: " + isInsidePolygon(new Point(1, 1), new Point[]{new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)}));
        IO.println("Square, point outside: " + isInsidePolygon(new Point(-1, 1), new Point[]{new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)}));
        IO.println("Square, point on the edge: " + isInsidePolygon(new Point(0, 1), new Point[]{new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)}));
        IO.println("Mirrored L, point outside: " + isInsidePolygon(new Point(0, 0), new Point[]{new Point(-2, -1), new Point(1, -1), new Point(1, 2), new Point(2, 2), new Point(2, -2), new Point(-2, -2)}));
    }
}

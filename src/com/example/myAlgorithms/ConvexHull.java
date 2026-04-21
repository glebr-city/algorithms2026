package com.example.myAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.myAlgorithms.OrientationTest.orientation;

public class ConvexHull {
    static int checkCount = 0;

    public static List<Point> convexHull(Point[] points) {
        checkCount = 0;
        if (points.length < 2) {
            IO.println("Please provide a list of more than one point to avoid an infinite loop " +
        "(should really be more than three, but we are required to test this on a triangle)");
            return Arrays.asList(points);
        }
        ArrayList<Point> startingList = new ArrayList<>(Arrays.asList(points));
        ArrayList<Point> output = new ArrayList<Point>();
        double lowestX = startingList.getFirst().x;
        Point startingPoint = startingList.getFirst();
        //Find the leftmost point, guaranteed to be on the hull.
        for (int i = 1; i < startingList.size(); i++) {
            if (startingList.get(i).x < lowestX) {
                lowestX = startingList.get(i).x;
                startingPoint = startingList.get(i);
            }
        }
        output.addLast(startingPoint);
        //Remove it from the list until we have found a second hull point to make sure that we don't end early.
        startingList.remove(startingPoint);
        Point currentPoint = startingList.getFirst();
        int loopCount = 0;
        while (true) {
            loopCount++;
            //We make use of our knowledge that the starting point is at the END of the list/
            if (currentPoint == startingPoint) {
                IO.println("[DEBUG] Loop count: " + loopCount + ", Check count: " + checkCount);
                return output;
            }
            Point potentialNextPoint = checkPoint(output.getLast(), currentPoint, startingList, startingPoint);
            if (potentialNextPoint == currentPoint) {
                if (output.size() == 1)
                    //We need to make sure we close the polygon, so the starting point is added once again.
                    startingList.addLast(startingPoint);
                output.add(currentPoint);
                startingList.remove(currentPoint);
                currentPoint = startingList.getFirst();
            } else {
                currentPoint = potentialNextPoint;
            }
        }
    }
    private static Point checkPoint(Point knownHullPoint, Point pointToCheck, List<Point> otherPoints, Point startingPoint) {
        checkCount++;
        List<Point> tempList = new ArrayList<>(otherPoints);
        tempList.remove(pointToCheck);
        for (Point o : tempList) {
            if (orientation(knownHullPoint, pointToCheck, o) < 0) {
                    return o;
            }
        }
        return pointToCheck;
    }

    public static void main(String[] args) throws Exception {

        Point[] points1 = new Point[3];
        points1[0] = new Point(0, 0);
        points1[1] = new Point(0, 1);
        points1[2] = new Point(1, 1);
        Point[] points2 = new Point[8];
        points2[0] = new Point(1, 3);
        points2[1] = new Point(0, 4);
        points2[2] = new Point(2, -1);
        points2[3] = new Point(1, 2);
        points2[4] = new Point(-5, -5);
        points2[5] = new Point(5, -5);
        points2[6] = new Point(0, 0);
        points2[7] = new Point(-1, -3);
        ArrayList<Point[]> testCases = new ArrayList<>(Arrays.asList(points1, points2));
        for (Point[] points : testCases) {
            StringBuilder sb = new StringBuilder();
            List<Point> outputList = convexHull(points);
            for (Point p : outputList) {
                sb.append("(");
                sb.append(p.x);
                sb.append(", ");
                sb.append(p.y);
                sb.append("), ");
            }
            sb.delete(sb.length() - 2, sb.length());
            IO.println(sb.toString());
        }
    }

}

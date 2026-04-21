package com.example.myAlgorithms;

public class PolygonArea {
    /// Find the area of a polygon using an ordered array of points on a plane, where each is connected to the last and the next.
    public static double polygonArea(Point[] vertices) {
        //Create a double to add to for each vertex.
        double output = 0.0;
        //We want to perform the calculation for every pair of connected vertices, including the last and the first
        //of the array. Thus, we make a new array and duplicate the first vertex at the end.
        Point[] verticesButComplete = new Point[vertices.length + 1];
        System.arraycopy(vertices, 0, verticesButComplete, 0, vertices.length);
        verticesButComplete[vertices.length] = vertices[0];
        for (int i = 0; i < vertices.length; i++) {
            output += (verticesButComplete[i].x * verticesButComplete[i+1].y) - (verticesButComplete[i+1].x * verticesButComplete[i].y);
        }
        //Area is not signed. It would be positive or negative depending on the position of the vertices, but we do not
        //have a use for that, so we make it absolute.
        output = Math.abs(output);
        return output / 2;
    }

    public static void main(String[] args) {
        IO.println("Right-angled triangle of area 2.0 : " + polygonArea(new Point[]{new Point(0,0), new Point(0,2), new Point(2,2)}));
        IO.println("A unit square (of area 1) : " +  polygonArea(new Point[]{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,0)}));
        IO.println("An irregular polygon with five vertices : " + polygonArea(new Point[]{new Point(0,0), new Point(2,3), new Point(3,4), new Point(-3,5), new Point(-2,0)}));
    }
}

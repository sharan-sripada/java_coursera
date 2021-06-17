package course1.week1.assignment;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints=0;
        for(Point currPoint:s.getPoints()){
            numPoints++;
        }


        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int numPoints=getNumPoints(s);
        double perimeter=getPerimeter(s);
        return perimeter/numPoints;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide=Double.MIN_VALUE;
        Point prevPoint=s.getLastPoint();
        for(Point currPoint:s.getPoints()){
            double currDist=prevPoint.distance(currPoint);
            largestSide=Math.max(largestSide,currDist);
            prevPoint=currPoint;
        }



        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX=Double.MIN_VALUE;
        for(Point point: s.getPoints()){
            double x=point.getX();
            largestX=Math.max(x,largestX);
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = Double.MIN_VALUE;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);

            double perimeter = getPerimeter(s);

            largestPerimeter=Math.max(largestPerimeter,perimeter);
        }

        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double largestPerimeter=Double.MIN_VALUE;
        DirectoryResource dr=new DirectoryResource();

        File temp = null;    // replace this code

        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            Shape s=new Shape(fr);

            double currPerimeter =getPerimeter(s);
            if(currPerimeter>largestPerimeter){
                largestPerimeter=currPerimeter;
                temp=f;
            }


        }


        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + getNumPoints(s));
        System.out.println("average length = " + getAverageLength(s));
        System.out.println("longest side = " + getLargestSide(s));
        System.out.println("largest x = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("largest perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("file name = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        System.out.println("Assignment 1 :");
        pr.testPerimeter();
        System.out.println("\nAssignment 2 :");
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}

/* Justin Farnsworth
 * May 4, 2016
 * Sierpinski Hexagon 
 * 
 * This program demonstrates recursion by graphing a recursive pattern with hexagons.
 * 
 * Java Compile & Run Commands:
 * javac -cp +libs/doodlepad.jar SierpinskiHexagon.java
 * java -cp .;+libs/doodlepad.jar SierpinskiHexagon
 */

import doodlepad.*;

public class SierpinskiHexagon {
    // Instance variable
    private static Pad p;
    
    // Recursive method
    private static void drawHexagon(double x1, double y1, double x2, double y2, double x3, double y3,
                                    double x4, double y4, double x5, double y5, double x6, double y6) {
        // Stops recursion when hexagon height or width falls below 8 pixels (Base case)
        if (((y4 - y1) < 8) || ((x3 - x6) < 8)) {
            return;
        }
        
        // Creates hexagon
        double[] xCoordinates = {x1, x2, x3, x4, x5, x6};
        double[] yCoordinates = {y1, y2, y3, y4, y5, y6};
        Polygon hexagon = new Polygon(xCoordinates, yCoordinates);
        hexagon.setFillColor(0, 0, 0, 50);
        hexagon.setStroked(false);
        
        // Calcuations are used to help position hexagons in recursion
        double a = ((x5 - x6) / 3);
        double b = ((x2 - x1) / 3);
        double c = ((y4 - y1) / 3);
        double d = ((y4 - y1) / 4);
        
        // Uses recursion to draw hexagons inside of the hexagons
        drawHexagon(x1, y1, x1+b, y2, x1+d, y2+b, x1+b, y2+c, x5, y1+c, x1-a, y1+b);     // Upper-left
        drawHexagon(x1+c, y1, x2, y2, x2+a, y2+b, x4, y2+c, x2-b, y1+c, x1+d, y1+b);     // Upper-right
        drawHexagon(x2, y2+c, x2+b, y2+c, x3, y3, x4+b, y4-c, x2, y5-c, x3-c, y6);       // Middle-right
        drawHexagon(x1+c, y4-c, x2, y4-c, x2+a, y4-b, x4, y4, x2-b, y5, x1+d, y4-b);     // Lower-right
        drawHexagon(x1, y5-c, x1+b, y5-c, x1+d, y5-b, x5+b, y4, x5, y5, x1-a, y5-b);     // Lower-left
        drawHexagon(x1-b, y1+c, x1, y1+c, x6+c, y3, x1, y5-c, x1-b, y5-c, x6, y6);       // Middle-left
        drawHexagon(x1+b, y2+c, x2-b, y1+c, x3-c, y3, x1+c, y4-c, x5+b, y5-c, x6+c, y6); // Center
    }
    
    // Executes program
    public static void main(String[] args) {
        // The dimensions of the hexagon can customized via command line argument
        // Only 1 argument is needed since it generates a square window
        if (args.length > 0) {
            int n = Integer.parseInt(args[0]);
            p = new Pad("Sierpinski Hexagon", n, n);
            drawHexagon(n/4.0, 0, (3*n)/4.0, 0, n, n/2.0, (3*n)/4.0, n, n/4.0, n, 0, n/2.0);
        } else { 
            // Defaults to an 800x800 window
            p = new Pad("Sierpinski Hexagon", 800, 800);
            drawHexagon(200, 0, 600, 0, 800, 400, 600, 800, 200, 800, 0, 400);
        }
    }
}
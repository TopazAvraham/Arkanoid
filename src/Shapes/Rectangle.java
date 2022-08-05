//Topaz Avraham 206842627

package Shapes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * The class point is used to represent rectangle in a plane
 */
public class Rectangle {
    private Line leftLine;
    private Line rightLine;
    private Line downLine;
    private Line upperLine;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor. Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
     public Rectangle(Point upperLeft, double width, double height) {
         this.upperLeft = upperLeft;
         this.width = width;
         this.height = height;
         //setting the sides of the rectangle
         setLines(upperLeft, width, height);
     }

    /**
     * @return leftLine - the left line of the rectangle
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * @return rightLine - the right line of the rectangle
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * @return downLine - the down line of the rectangle
     */
    public Line getDownLine() {
        return downLine;
    }

    /**
     * @return upperLine - - the upper line of the rectangle
     */
    public Line getUpperLine() {
        return upperLine;
    }

    /**
     * this method returns a (possibly empty) List of intersection points with the specified line.
     * @param line - the line to check intersection points with
     * @return List of intersection points (possibly empty)
     */
    public List<Point> intersectionPoints(Line line) {
        //creating an empty list
        List<Point> intersectionPoints = new ArrayList<>();
        int counter = 0;

        //checking for each line of the rectangle what are the intersection point of the rectangle with it
        //and if there is, add it to the list
        if (upperLine.intersectionWith(line) != null) {
            counter++;
            intersectionPoints.add(upperLine.intersectionWith(line));
        }
        if (downLine.intersectionWith(line) != null) {
            counter++;
            intersectionPoints.add(downLine.intersectionWith(line));
        }

        if (leftLine.intersectionWith(line) != null) {
            counter++;
            intersectionPoints.add(leftLine.intersectionWith(line));
        }
        if (this.rightLine.intersectionWith(line) != null) {
            counter++;
            intersectionPoints.add(rightLine.intersectionWith(line));
        }
        int multipleLinesError = 4;
        if (counter > multipleLinesError) {
            return null;
        }
        return intersectionPoints;
    }

    /**
     * this method is used to set the sides of the rectangle.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     */
    public void setLines(Point upperLeft, double width, double height) {
         this.upperLine = new Line(upperLeft.getX(), upperLeft.getY(),
                 upperLeft.getX() + width, upperLeft.getY());

         this.downLine = new Line(upperLeft.getX(), upperLeft.getY() + height,
                 upperLeft.getX() + width, upperLeft.getY() + height);

         this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                 upperLeft.getX() + width,  upperLeft.getY() + height);

         this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(),
                 upperLeft.getY() + height);
     }

    /**
     * @return - the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return - the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method draw the rectangle on the given DrawSurface.
     * @param d - the surface to draw the block on
     */
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) this.getUpperLine().start().getX(), (int) this.getUpperLine().start().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }


    /**
     * This method draw the rectangle on the given DrawSurface.
     * @param d - the surface to draw the block on
     * @param color - the color of the rectangle
     */
    public void drawOn(DrawSurface d, Color color) {
        //first draw the inside of the rectangle
        d.setColor(color);
        d.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());

        //then draw the boundaries of the rectangle
        d.setColor(Color.black);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}


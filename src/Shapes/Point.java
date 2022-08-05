//Topaz Avraham 206842627

package Shapes;
/**
 * @author Topaz Avraham
 * The class point is used to represent point in a plane
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x the value of X coordinate
     * @param y the value of Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - the point to calculate the distance to
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return  Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * @param other point to calculate is both are equal
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }

    /**
     * @return the value of X coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @param x The value to set the X coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the value of Y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param y The value to set the Y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method receives 2 lines and calculate if the point is on both lines.
     * @param l1 - the first line
     * @param l2 - the second line
     * @return true is the point is on both lines, false otherwise
     */
    public boolean isPointOnBothLines(Line l1, Line l2) {
        if (l1.start().distance(this) <= l1.length() && (l1.end().distance(this) <= l1.length())) {
            if ((l2.start().distance(this) <= l2.length()) && (l2.end().distance(this) <= l2.length())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to check if the point is on a certain line.
     * @param l - the line to check if the point is on it
     * @return - true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Line l) {
        //if the x coordinate of the point is not on the line
        if (((getX() < l.start().getX()) && (getX() < l.end().getX()))) {
            return false;
        }
        if ((getX() > l.start().getX()) && (getX() > l.end().getX())) {
            return false;
        }
        //if the line is vertical and the y coordinate is not on the line
        if (((l.start().getX() == l.end().getX())) && (getY() < l.start().getY()) && (getY() < l.end().getY())) {
            return false;
        }
        if ((l.start().getX() == l.end().getX()) && (getY() < l.start().getY()) && (getY() < l.end().getY())) {
            return false;
        }

        //the line is linear/horizontal - check if the x,y coordinates maintain the equation of the line
        Point p = new Point(this.getX(), l.getSlope() * this.getX() + l.getValueOfBInLineEquation());
        if (this.equals(p)) {
            return true;
        }
        if (l.start().getX() == l.end().getX() && l.start().getX() == this.getX()) {
            return true;
        }
        return false;
    }
}



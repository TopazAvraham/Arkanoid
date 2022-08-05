//Topaz Avraham 206842627

package Shapes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Topaz Avraham
 * The class point is used to represent line in a plane
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     * @param start the starting point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     * @param x1 - the X coordinate of the starting point of the line
     * @param y1 - the Y coordinate of the starting point of the line
     * @param x2 - the X coordinate of the ending point of the line
     * @param y2 - the Y coordinate of the ending point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This method calculates the length of a line by calculating the distance between the starting and ending point.
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end());
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * @return slope - the slope of the line (given the assumption the line is not vertical)
     */
    public double getSlope() {
        double slope;
        slope = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        return slope;
    }

    /**
     * @return the b value in the line equation y= mx+b (given the assumption the line is linear)
     */
    public double getValueOfBInLineEquation() {
        return (this.end.getY() - (this.getSlope() * this.end.getX()));
    }

    /**
     * @param other - the line to check if they are intersecting
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }

        //if both lines are the same vertical lines
        if (other.start.getX() == other.end.getX() && (this.start.getX() == this.end.getX())
                && (this.start.getX() == other.end.getX())) {

            //if one starts when the second ends
            if (this.start.getY() == other.start.getY() || this.start.getY() == other.end.getY()
                    || this.end.getY() == other.start.getY() || this.end.getY() == other.end.getY()) {
                return true;
            }
            //if they don't touch
            if ((other.start.getY() < this.start.getY()) && (other.end.getY() < this.start.getY())) {
                return false;
            }
            if ((other.start.getY() > this.start.getY()) && (other.end.getY() > this.start.getY())) {
                return false;
            }
            return true;
        }

        if (other.start.getX() == other.end.getX() && (this.start.getX() == this.end.getX())
                && (this.start.getX() != other.end.getX())) {
            return false;
        }

        //if both lines are the same linear / horizontal lines
        if ((other.getSlope() == this.getSlope())
                && (this.getValueOfBInLineEquation() == other.getValueOfBInLineEquation())) {
            //if both the same horizontal
            if (other.getSlope() == 0) {
                //if one starts when the second ends
                if (this.start.getX() == other.start.getX() || this.start.getX() == other.end.getX()
                        || this.end.getX() == other.start.getX() || this.end.getX() == other.end.getX()) {
                    return true;
                }
                //if they don't touch
                if ((other.start.getX() < this.start.getX()) && (other.end.getX() < this.start.getX())) {
                    return false;
                }
                if ((other.start.getX() > this.start.getX()) && (other.end.getX() > this.start.getX())) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * @param other - the line to check the intersection point with (if exists).
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        double interSectionX, interSectionY;

        //if this line has no slope
        if (this.start.getX() == this.end.getX()) {
            interSectionX = this.start.getX();

            //if the other line also without slope but their equation of x is different
            if ((other.start.getX() == other.end.getX()) && this.start.getX() != other.start.getX()) {
                return null;
            }
            //if the other line is horizontal
            if (other.getSlope() == 0) {
                interSectionY = other.start.getY();
                Point intersection = new Point(interSectionX, interSectionY);
                if (intersection.isPointOnBothLines(this, other)) {
                    return intersection;
                }
                return null;
            }
            //if they are the same vertical line
            if (other.start.getX() == other.end.getX() && this.start.getX() == other.end.getX()) {
                return null;
            } else {
                //1 is x= something, 2 is y=mx+b
                interSectionY = other.getSlope() * this.start.getX() + other.getValueOfBInLineEquation();
                Point intersection = new Point(interSectionX, interSectionY);
                if (intersection.isPointOnBothLines(this, other)) {
                    return intersection;
                }
                return null;
            }
        } else if (other.start.getX() == other.end.getX()) {
            interSectionX = other.start.getX();

            //if this line is horizontal
            if (this.getSlope() == 0) {
                interSectionY = this.start.getY();
                Point intersection = new Point(interSectionX, interSectionY);
                if (intersection.isPointOnBothLines(this, other)) {
                    return intersection;
                }
                return null;
            } else {
                //1 is y=mx+b, second is x=something
                interSectionY = this.getSlope() * other.start.getX() + this.getValueOfBInLineEquation();
                Point intersection = new Point(interSectionX, interSectionY);
                if (intersection.isPointOnBothLines(this, other)) {
                    return intersection;
                }
                return null;
            }
        }

        //if reached here, both lines are not vertical
        double slopeFirstLine = this.getSlope();
        double slopeSecondLine = other.getSlope();
        double bFirstLine = this.getValueOfBInLineEquation();
        double bSecondLine = other.getValueOfBInLineEquation();

        //if the 2 lines are parallel - they not intersect
        if ((slopeFirstLine == slopeSecondLine) && (bFirstLine != bSecondLine)) {
            return null;
        }

        // if the 2 lines are the same - we return null
        if ((slopeFirstLine == slopeSecondLine) && (bFirstLine == bSecondLine)) {
            //potential for common points
            if (this.end.getX() == other.start.getX() || this.start.getX() == other.end.getX()) {
                // if they have one common point
                if (this.start.getX() < this.end.getX() && this.end.getX() < other.end.getX()
                        || (this.start.getX() > this.end.getX() && this.end.getX() > other.end.getX())) {
                    interSectionX = this.end.getX();
                    interSectionY = this.end.getY();
                    return new Point(interSectionX, interSectionY);
                }
                // if they have one common point
                if (this.start.getX() > this.end.getX() && this.start.getX() < other.start.getX()
                        || (this.start.getX() < this.end.getX() && this.start.getX() > other.start.getX())) {
                    interSectionX = this.start.getX();
                    interSectionY = this.end.getY();
                    return new Point(interSectionX, interSectionY);
                }
             return null;
            }

            if (this.start.getX() == other.start.getX()) {

                if (this.start.getX() > this.end.getX() && other.start.getX() < other.end.getX()
                        || (this.start.getX() < this.end.getX() && other.start.getX() > other.end.getX())) {
                    interSectionX = this.start.getX();
                    interSectionY = this.end.getY();
                    return new Point(interSectionX, interSectionY);
                }
                return null;
            }

            if (this.end.getX() == other.end.getX()) {
                if (this.start.getX() > this.end.getX() && other.start.getX() < other.end.getX()
                        || (this.start.getX() < this.end.getX() && other.start.getX() > other.end.getX())) {
                    interSectionX = this.end.getX();
                    interSectionY = this.end.getY();
                    return new Point(interSectionX, interSectionY);
                }
                return null;
            }

            return null;
        }

        interSectionX = ((bSecondLine - bFirstLine) / (slopeFirstLine - slopeSecondLine));
        interSectionY = (slopeFirstLine * interSectionX) + bFirstLine;
        Point intersection = new Point(interSectionX, interSectionY);

        if (intersection.isPointOnBothLines(this, other)) {
            return intersection;
        }
        return null;
    }

    /**
     * @param other - the second line.
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || (this.end.equals(other.start) && (this.start.equals(other.end)))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect - the rectangle to check if the line is colliding with, and if so then where
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //creating an empty list of potential intersection points
        List<Point> intersectionsList = new ArrayList<>();
        //filling the list with the all the possible intersection points
        intersectionsList = rect.intersectionPoints(this);

        //if the size is zero - no collision has occurred. therefore, return null
        if (intersectionsList.size() == 0) {
            return null;
        } else {
            //there is at least one intersection point, so we will use a standard find minimum algorithm
            //to find the closest point to the start of the line
            Point closestToStart = intersectionsList.get(0);
            for (int i = 1; i < intersectionsList.size(); i++) {

                if (this.start.distance(closestToStart) > this.start.distance(intersectionsList.get(i))) {
                    closestToStart = intersectionsList.get(i);
                }
            }
            return closestToStart;
        }
    }
}


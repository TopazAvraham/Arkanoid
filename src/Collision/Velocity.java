//Topaz Avraham 206842627
package Collision;

import Shapes.Point;

/**
 * @author Topaz Avraham
 * The class velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx - the amount of movement to left/right in every move
     * @param dy - the amount of movement to up/down in every move
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle the direction of the ball
     * @param speed the speed of the ball
     * @return the velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (speed) * Math.sin(Math.toRadians(angle));
        double dy = (-speed) * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * this method is used to calculate the speed from the velocity - (I found this formula from YouTube).
     * @return res - the speed of the moving object
     */
    public double speedFromVelocityFormula() {
        double res = Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2));
        return res;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - point in a plane before changes
     * @return - the point after we add the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @param dx - the amount of movement to left/right in every move
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * @param dy - the amount of movement to up/down in every move
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @return dx - the amount of movement to left/right in every move
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy - the amount of movement to up/down in every move
     */
    public double getDy() {
        return dy;
    }

}
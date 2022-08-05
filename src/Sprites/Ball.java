//Topaz Avraham 206842627

package Sprites;
import Collision.CollisionInfo;
import Collision.Velocity;
import Game.GameLevel;
import Game.GameEnvironment;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * The class Sprites.Ball is used to represent Sprites.Ball in a plane
 */
public class Ball implements Sprite {
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity ballVelocity;
    private GameEnvironment gameDecor;


    /**
     * Constructor.
     * Sets the velocity to (0,0) as default
     * @param center - the center point of the ball
     * @param r - the radius of the circle (ball)
     * @param color - the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.ballVelocity = new Velocity(0, 0);
    }

    /**
     * Constructor.
     * Sets the velocity to (0,0) as default
     * @param x - the X coordinate of the center point of the ball
     * @param y - the Y coordinate of the center point of the ball
     * @param r - the radius of the circle (ball)
     * @param color - the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
        this.ballVelocity = new Velocity(0, 0);
    }

    /**
     * @param gameDecor - the game environment
     */
    public void setGameDecor(GameEnvironment gameDecor) {
        this.gameDecor = gameDecor;
    }

    /**
     * @return the X coordinate of the center point of the ball
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the Y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius of the circle (ball)
     */
    public int getSize() {
        return radius;
    }

    /**
     * @param center the center point of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * This method draw the ball on the given DrawSurface.
     * @param surface - the surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
    * @param v - the velocity to set to the ball
    */
    public void setVelocity(Velocity v) {
        ballVelocity = v;
    }

    /**
     * @param dx - the amount of movement to left/right in every move
     * @param dy - the amount of movement to up/down in every move
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        setVelocity(v);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return ballVelocity;
    }

    /**
     * This method makes the ball move one step on the surface.
     * it checks what is the ball's closest collision point and adjust the ball location and velocity accordingly
     */
    public void moveOneStep() {

        //get the closest collision point
        CollisionInfo check = this.gameDecor.getClosestCollision(computeBallTrajectory());

        //if there are none - meaning the ball is not going to collide anything while doing this step
        //so the ball can be moved exactly as needed, and the velocity stays the same
        if (check == null) {
            this.center = this.getVelocity().applyToPoint(center);
        } else {
            //there is a collision in this step, receiving the data about the collision
            Point hitPoint = check.collisionPoint();
            Rectangle hitRectangle = check.collisionObject().getCollisionRectangle();
            Collidable obstacle = check.collisionObject();

            //if the hitting point is on the edges on the block
            if (hitPoint.equals(hitRectangle.getUpperLine().start())
                    || hitPoint.equals(hitRectangle.getUpperLine().end())
                    || hitPoint.equals(hitRectangle.getDownLine().start())
                    || hitPoint.equals(hitRectangle.getDownLine().end())) {

                //moving the ball almost to collision point in respect to its x,y coordinates
                if (center.getX() < hitPoint.getX()) {
                    center.setX(hitPoint.getX() - 1);
                }
                if (center.getX() > hitPoint.getX()) {
                    center.setX(hitPoint.getX() + 1);
                }
                if (center.getY() < hitPoint.getY()) {
                    center.setX(hitPoint.getX() - 1);
                }
                if (center.getY() > hitPoint.getY()) {
                    center.setX(hitPoint.getX() + 1);
                }
            } else {
                // if reached here, the ball is colliding, but not on the edges of the block, it collides with a
                // specific line, so we'll move the ball almost to collision point in respect to its x,y coordinates
                if (hitPoint.isPointOnLine(hitRectangle.getDownLine())) {
                    center.setY(hitPoint.getY() + 1);
                }

                if (hitPoint.isPointOnLine(hitRectangle.getUpperLine())) {
                    center.setY(hitPoint.getY() - 1);
                }

                if (hitPoint.isPointOnLine(hitRectangle.getLeftLine())) {
                    center.setX(hitPoint.getX() - 1);
                }

                if (hitPoint.isPointOnLine(hitRectangle.getRightLine())) {
                    center.setX(hitPoint.getX() + 1);
                }
            }
            //setting the velocity of the ball after it hit the block
            this.setVelocity(obstacle.hit(this, hitPoint, ballVelocity));
        }
    }

    /**
     * this method is used to compute how the ball will move without any obstacles.
     * @return the line the ball will "move like"
     */
    public Line computeBallTrajectory() {
        return new Line(center.getX(), center.getY(), center.getX() + ballVelocity.getDx(),
                center.getY() + ballVelocity.getDy());
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public void timePassed() {
            moveOneStep();
    }

    /**
     * this method adds the ball to the game.
     * @param g - the game that we add the ball to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this method removes the ball to the game.
     * @param g - the game that we remove the ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
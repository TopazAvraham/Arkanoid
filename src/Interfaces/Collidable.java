//Topaz Avraham 206842627

package Interfaces;
import Collision.Velocity;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Ball;

/**
 * @author Topaz Avraham
 * This interface is used to represent abilities the collidable objects can do in the game
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
       Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint - the point of the collision that about to occur
     * @param currentVelocity - the velocity of the moving object at the moment
     * @param hitter - the ball that hits the collidable
     * @return the velocity of the moving object that hit the collidable after the hit
     */
       Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

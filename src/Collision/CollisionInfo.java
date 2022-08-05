//Topaz Avraham 206842627

package Collision;
import Interfaces.Collidable;
import Shapes.Point;

/**
 * @author Topaz Avraham
 * This class is used to give data about a certain collision in the game
 */
public class CollisionInfo {

    private Point aboutToHitFirst;
    private Collidable obstacle;

    /**
     * Constructor.
     * @param aboutToHitFirst - the point of collision
     * @param obstacle - the collidable the moving object hit
     */
    public CollisionInfo(Point aboutToHitFirst, Collidable obstacle) {
        this.aboutToHitFirst = aboutToHitFirst;
        this.obstacle = obstacle;
    }

    /**
     * @return the point at which the collision occurs.
     */
       public Point collisionPoint() {
           return aboutToHitFirst;
       }

    /**
     * @return the collidable object involved in the collision.
     */
       public Collidable collisionObject() {
           return obstacle;
       }
}

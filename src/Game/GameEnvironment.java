//Topaz Avraham 206842627

package Game;
import Collision.CollisionInfo;
import Interfaces.Collidable;
import Shapes.Line;
import Shapes.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Topaz Avraham
 * The class Game.GameEnvironment is used to represent the collidbles on the screen
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructor.
     * creating an empty list of collidables
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * this method is used to add the given collidable to the environment.
     * @param c - the collidable to add to the environment.
     */
   public void addCollidable(Collidable c) {
       collidables.add(c);
   }

    /**
     * @return the collidables list
     */
   public List<Collidable> getCollidables() {
    return collidables;
}

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - how the ball will move without any obstacles
     * @return the Closest Collision
     */
   public CollisionInfo getClosestCollision(Line trajectory) {
        //if there are no collidables, there will be so collision so return null
        if (collidables.size() == 0) {
            return null;
        }

        //assuming the ball is about to hit the first collidable first (similar to find minimum algorithm)
        Point aboutToHitFirst = trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());
        Collidable obstacle = collidables.get(0);
        int counter = 1;

        while (counter < collidables.size()) {
            //for each collidable, compute the closest intersection if exists
            Point aboutToHit =
                    trajectory.closestIntersectionToStartOfLine(collidables.get(counter).getCollisionRectangle());

            //if the ball will not collide with the first collidable in the list, but will collide with this collidable
            // so change the point of collision and the collidable obstacle
            if (aboutToHitFirst == null && aboutToHit != null) {
                aboutToHitFirst = aboutToHit;
                obstacle = collidables.get(counter);
                continue;
            }

            //if the ball will not collide with this collidable
            if (aboutToHit == null) {
                counter++;
                continue;
            }

            //if the ball will collide with this collidable and the point is more close than the closest point yet
            if (aboutToHit.distance(trajectory.start()) < aboutToHitFirst.distance(trajectory.start())) {
                aboutToHitFirst = aboutToHit;
                obstacle = collidables.get(counter);
            }
            counter++;
        }

        //after finishing the loop, we'll end up with the closest point, if it is exists.
        if (aboutToHitFirst != null) {
            return new CollisionInfo(aboutToHitFirst, obstacle);
        }

        //if reached here the ball will not collide with any of the collidables in the list
        return null;
   }

    /**
     * This method removes the collidable from the game.
     * @param c - the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}




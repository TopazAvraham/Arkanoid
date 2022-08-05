//Topaz Avraham 206842627

package Interfaces;
import Sprites.Ball;
import Sprites.Block;

/**
 * @author Topaz Avraham
 * This interface is used to enable object to know when there was a hit event in the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that is being hit
     * @param hitter - the Sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

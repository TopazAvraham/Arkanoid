//Topaz Avraham 206842627

package Interfaces;

/**
 * @author Topaz Avraham
 * This interface is used to enable object to notify when there was a hit event in the game.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the listener to remove
     */
    void removeHitListener(HitListener hl);
}

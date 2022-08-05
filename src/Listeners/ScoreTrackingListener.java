//Topaz Avraham 206842627

package Listeners;
import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import Game.Counter;

/**
 * @author Topaz Avraham
 * The class point is used to track when there is a hit event in the game, and increases the score accordingly.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     *  Constructor.
     * @param scoreCounter - the current score in the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}

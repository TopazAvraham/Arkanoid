//Topaz Avraham 206842627

package Listeners;
import Game.GameLevel;
import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import Game.Counter;

/**
 * @author Topaz Avraham
 * The class Sprites.Ball is used to Balls from the game
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game - the game the ball is in
     * @param remainingBalls - the amount of balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}

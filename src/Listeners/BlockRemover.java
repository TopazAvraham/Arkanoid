//Topaz Avraham 206842627

package Listeners;
import Game.GameLevel;
import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import Game.Counter;

/**
 * @author Topaz Avraham
 * A Listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game - the game the block is in
     * @param removedBlocks - the amount of blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }

    /**
     * This method used to set the remaining blocks amount in the game.
     * @param remainingBlocks - the number of blocks remaining in the game
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }
}
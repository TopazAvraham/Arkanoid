//Topaz Avraham 206842627

package Interfaces;

import Collision.Velocity;
import Sprites.Block;
import java.util.List;

/**
 * @author Topaz Avraham
 * This interface is used to represent animations in the game
 */
public interface LevelInformation {
    /**
     * @return the number of balls there are in the level
     */
    int numberOfBalls();

    /**
     * @return a list with the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle in the level
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle in the level
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed in the level
     */
    int numberOfBlocksToRemove();
}

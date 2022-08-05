//Topaz Avraham 206842627

package Animations;

import Interfaces.Animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Topaz Avraham
 * This class is used to present the game-over screen animation.
 */
public class GameOverScreen implements Animation {
    private int finalScore;
    private boolean stop;

    /**
     * Constructor.
     * @param finalScore - the final score of the game.
     */
    public GameOverScreen(int finalScore) {
        this.stop = false;
        this.finalScore = finalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + finalScore, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}


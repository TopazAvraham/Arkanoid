//Topaz Avraham 206842627

package Sprites;
import Game.Counter;
import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Topaz Avraham
 * The class point is used to represent the score in the game
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCount;

    /**
     * Constructor.
     *
     * @param score - the score in the game
     */
    public ScoreIndicator(Counter score) {
        scoreCount = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(300, 15, "score: " + scoreCount.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method is used to add the score indicator to the game.
     * @param game - the game to add the score indicator to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}

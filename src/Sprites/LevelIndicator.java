//Topaz Avraham 206842627

package Sprites;

import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * The class point is used to represent the level name of the game
 */
public class LevelIndicator implements Sprite {

    private String levelName;

    /**
     * Constructor.
     * @param levelName - the name of the current level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(600, 15, "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method is used to add the level indicator to the game.
     * @param game - the game to add the level indicator to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}

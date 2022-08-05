//Topaz Avraham 206842627

package Animations;

import Interfaces.Animation;
import Interfaces.LevelInformation;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;


/**
 * @author Topaz Avraham
 * This class is used to present the countdown screen animation before the level starts.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private LevelInformation level;
    private Sleeper sleeper = new Sleeper();

    /**
     * Constructor.
     * @param numOfSeconds - the number of seconds until the animation will be over
     * @param countFrom - the initial number of which the countdown will start from
     * @param gameScreen - the sprites in the level
     * @param level - the level which will be played after the countdown is over
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation level) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.level = level;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        level.getBackground().drawOn(d);
        gameScreen.drawAllOn(d);
        d.setColor(Color.yellow);
        d.drawText((d.getWidth() / 2) - 17, d.getHeight() / 2, Integer.toString(countFrom), 70);


        if (countFrom < 3) {
            sleeper.sleepFor(1000);
        }

        if (countFrom == 0) {
            this.stop = true;
        }
        countFrom -= 1;

    }
    @Override
    public boolean shouldStop() {
       return this.stop;
    }
}

//Topaz Avraham 206842627

package Animations;

import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * This class is used to present the pause screen animation.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

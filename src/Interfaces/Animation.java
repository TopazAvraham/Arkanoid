//Topaz Avraham 206842627

package Interfaces;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * This interface is used to represent animations in the game
 */
public interface Animation {
    /**
     * This method is used to draw a single frame animation on the drawing surface.
     * @param d - the drawing surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method is used to determine whether the animation should stop and not do another frame.
     * @return - true if needed to continue the animation, false otherwise
     */
    boolean shouldStop();
    }


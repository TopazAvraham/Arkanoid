//Topaz Avraham 206842627

package Interfaces;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * This interface is used to describe abilities the sprites can do
 */
public interface Sprite {
    /**
     * This method is used to draw the sprite to the screen.
     * @param d - the drawing surface
     */
        void drawOn(DrawSurface d);

    /**
     * this method is used to notify the sprite that time has passed.
     */
       void timePassed();
}

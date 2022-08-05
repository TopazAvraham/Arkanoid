//Topaz Avraham 206842627

package Sprites;
import java.util.ArrayList;
import java.util.List;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * the class is used describe object that are a collection of sprites
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructor.
     * creates an empty list of sprites
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * this method is used to add a sprite to the collection.
     * @param s - the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * this method is used to call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
         for (int i = 0; i < sprites.size(); i++) {
             sprites.get(i).timePassed();
           }
    }

    /**
     * this method is used to call drawOn(d) on all sprites.
     * @param d - the drawing surface
     */
    public void drawAllOn(DrawSurface d) {
       for (int i = 0; i < sprites.size(); i++) {
           sprites.get(i).drawOn(d);
       }
    }

    /**
     * This method is used to remove a sprite from the game.
     * @param s - the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}
//Topaz Avraham 206842627

package Sprites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Collision.Velocity;
import Game.GameLevel;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import Shapes.Point;
import Shapes.Rectangle;
import biuoop.DrawSurface;

/**
 * @author Topaz Avraham
 * The class Sprites.Block is used to represent a paddle in a plane, moving object can be hit by it, and it's drawable
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param upperLeft - the upper left point of the rectangle of the block
     * @param width - the width of the rectangle that represents the block
     * @param height - the height of the rectangle that represents the block
     * @param color - the color of the rectangle that represents the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.hitListeners = new ArrayList<HitListener>();
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Constructor.
     * @param rectangle - the rectangle that represents the block
     * @param color - the color fot the rectangle of the block
     */
    public Block(Rectangle rectangle, Color color) {
        hitListeners = new ArrayList<HitListener>();
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Velocity afterHit = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //if the collision will be at the edges of the block
        if (collisionPoint.equals(this.rectangle.getUpperLine().start())
                || collisionPoint.equals(this.rectangle.getUpperLine().end())
                || collisionPoint.equals(this.rectangle.getDownLine().start())
                || collisionPoint.equals(this.rectangle.getDownLine().end())) {
            afterHit.setDx(-currentVelocity.getDx());
            afterHit.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
            return afterHit;
        }

        //if the collision point will be on the vertical lines
        if (collisionPoint.isPointOnLine(this.rectangle.getLeftLine())
                || collisionPoint.isPointOnLine(this.rectangle.getRightLine())) {

            afterHit.setDx(-currentVelocity.getDx());
            this.notifyHit(hitter);
            return afterHit;
        }

        //if the collision point will be on the horizontal lines
        if (collisionPoint.isPointOnLine(this.rectangle.getUpperLine())
                || collisionPoint.isPointOnLine(this.rectangle.getDownLine())) {

            afterHit.setDy(-currentVelocity.getDy());
            this.notifyHit(hitter);
            return afterHit;
        }
        this.notifyHit(hitter);
        return afterHit;
    }


    /**
     * This method draw the ball on the given DrawSurface.
     * @param d - the surface to draw the block on
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
    }

    /**
     * this method removes the block to the game.
     * @param g - the game that we remove the block from
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this method return the rectangle of the block.
     * @return the rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public void timePassed() {
        //
    }

    /**
     * this method adds the block to the game.
     * @param g - the game that we add the block to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

//Topaz Avraham 206842627

package Sprites;

import java.awt.Color;
import Collision.Velocity;
import Game.GameLevel;
import Interfaces.Collidable;
import Interfaces.Sprite;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Topaz Avraham
 * The class Sprites.Paddle is used to represent a paddle in a plane
 */
public class Paddle implements Sprite, Collidable {
   private KeyboardSensor keyboard;
   private Rectangle rectangle;
   private int rightLimitX;
   private int leftLimitX;


    /**
     * Constructor.
     * @param keyboard - the keyboard of the user.
     * @param rectangle - the rectangle of the paddle
     */
   public Paddle(KeyboardSensor keyboard, Rectangle rectangle) {
       this.keyboard = keyboard;
       this.rectangle = rectangle;
       this.rightLimitX = 780;
       this.leftLimitX = 20;
   }

    /**
     * this method is used to move the paddle to the left after the user pressed the left button.
     */
   public void moveLeft() {

       //checking if already reached to the limit of the screen from the left. if so -
       //move the rectangle exactly to the limit
       if (rectangle.getLeftLine().start().getX() <= leftLimitX) {
           Point newUpper = new Point(leftLimitX, rectangle.getUpperLeft().getY());
           rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
           return;
       }
       //if reached here we are not at the limit, so move the paddle to the left
           Point newUpper = new Point(this.rectangle.getUpperLeft().getX() - 9, rectangle.getUpperLeft().getY());
           rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
   }

    /**
     * this method is used to move the paddle to the right after the user pressed the right button.
     */
   public void moveRight() {

       //checking if already reached to the limit of the screen from the right. if so -
       //move the rectangle exactly to the limit
       if (rectangle.getRightLine().start().getX() >= rightLimitX) {
           Point newUpper = new Point(rightLimitX - rectangle.getWidth(), rectangle.getUpperLeft().getY());
           rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
           return;
       }
       //if reached here we are not at the limit, so move the paddle to the right
       Point newUpper = new Point(this.rectangle.getUpperLeft().getX() + 9, rectangle.getUpperLeft().getY());
       rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
   }

   @Override
   public void timePassed() {
       if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           moveLeft();
        }
       if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
           moveRight();
        }
   }

   @Override
   public void drawOn(DrawSurface d) {
       this.rectangle.drawOn(d, Color.ORANGE);
   }


    /**
     * This method computes on which area in the line the collision occurs.
     * @param collisionPoint - the point of collision
     * @return - the area of the upper line of the paddle the collision is happening in
     */
   public int getPaddleArea(Point collisionPoint) {

       Line paddleUpperLine = this.rectangle.getUpperLine();

       double lineLength = paddleUpperLine.length();

       //checking with respect to the x coordinate on with area is the point and return the number area accordingly
       if (collisionPoint.getX() >=  paddleUpperLine.start().getX()
               && collisionPoint.getX() <= lineLength * ((double) 1 / 5) + paddleUpperLine.start().getX()) {
            return 1;
       } else if (collisionPoint.getX() > lineLength * ((double) 1 / 5) + paddleUpperLine.start().getX()
               && collisionPoint.getX() <= lineLength * ((double) 2 / 5) + paddleUpperLine.start().getX()) {
            return 2;
       } else if (collisionPoint.getX() > lineLength * ((double) 2 / 5) + paddleUpperLine.start().getX()
               && collisionPoint.getX() <= lineLength * ((double) 3 / 5) + paddleUpperLine.start().getX()) {
           return 3;
       } else if (collisionPoint.getX() > lineLength * ((double) 3 / 5) + paddleUpperLine.start().getX()
               && collisionPoint.getX() <= lineLength * ((double) 4 / 5) + paddleUpperLine.start().getX()) {
            return 4;
       } else if (collisionPoint.getX() > lineLength * ((double) 4 / 5) + paddleUpperLine.start().getX()
               && collisionPoint.getX() <= lineLength + paddleUpperLine.start().getX()) {
            return 5;
       }
       return 0;
   }

    /**
     * @return the rectangle of the paddle
     */
   public Rectangle getCollisionRectangle() {
       return this.rectangle;
   }

   @Override
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

       //if the ball has hit the paddle from the sides - change only its dx movement
       if (!collisionPoint.isPointOnLine(rectangle.getUpperLine())) {
           return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
       } else {
           //the ball has hit the top of the paddle
           int area = this.getPaddleArea(collisionPoint);
                if (area == -1) {
                return null;
                } else {
                    //setting the velocity of the ball with angles written in instructions
                    if (area == 1) {
                        return Velocity.fromAngleAndSpeed(300, currentVelocity.speedFromVelocityFormula());
                    }
                    if (area == 2) {
                           return Velocity.fromAngleAndSpeed(330, currentVelocity.speedFromVelocityFormula());
                    }
                    if (area == 3) {
                        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    }
                    if (area == 4) {
                        return Velocity.fromAngleAndSpeed(60, currentVelocity.speedFromVelocityFormula());
                    }
                    if (area == 5) {
                        return Velocity.fromAngleAndSpeed(30, currentVelocity.speedFromVelocityFormula());
                    }
                }
           }
       return null;
}

    /**
     * this method is used to add this paddle to the game.
     * @param g - the game to add the paddle to
     */
   public void addToGame(GameLevel g) {
       g.addSprite(this);
       g.addCollidable(this);
   }
}
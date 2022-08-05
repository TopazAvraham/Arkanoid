//Topaz Avraham 206842627

package Game;

import java.awt.Color;
import java.util.Random;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Animations.KeyPressStoppableAnimation;
import Animations.PauseScreen;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.ScoreIndicator;
import Sprites.Block;
import Sprites.Ball;
import Sprites.Paddle;
import Sprites.SpriteCollection;
import Sprites.LevelIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Topaz Avraham
 * This class is used to represent a level in the game
 */
public class GameLevel implements Animation {

       private SpriteCollection sprites;
       private GameEnvironment environment;
       private GUI gui;
       private Counter blocksCounter;
       private Counter ballsCounter;
       private Counter score;
       private AnimationRunner runner;
       private boolean running;
       private Color screenColor;
       private KeyboardSensor keyboard;
       private LevelInformation level;

    /**
     * Constructor.
     * creates an environment and empty Interfaces.Sprite collection
     * @param level - the current level in the game
     */
    public GameLevel(LevelInformation level) {
           this.level = level;
           this.sprites = new SpriteCollection();
           this.environment = new GameEnvironment();
       }

    /**
     * Constructor.
     * creates an environment and empty Interfaces.Sprite collection
     * @param levelInfo - the current level in the game
     * @param ar - the animation runner with will run the animations during the game
     * @param ks - the keyboard of the user
     * @param entireGameScore - the game score reached until this level
     * @param gui - the gui which the game will be played on
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar,
                     Counter entireGameScore, GUI gui) {
        this.level = levelInfo;
        this.keyboard = ks;
        this.runner = ar;
        this.score = entireGameScore;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
    }

    /**
     * this method is used to add a collidable to the list of collidables in the game environment.
     * @param c - the collidable to add
     */
    public void addCollidable(Collidable c) {
           this.environment.addCollidable(c);
       }

    /**
     * This method is used to remove a collidable from the game.
     * @param c - the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this method is used to add a sprite to the list of sprites.
     * @param s - the sprite to add
     */
    public void addSprite(Sprite s) {
           this.sprites.addSprite(s);
       }

    /**
     * this method is used to set the blocks for the screen limits.
     * @param ballRemover - the listener to attach to the death region block
     */
    public void setBlocksForGuiScreen(BallRemover ballRemover) {

        // create 4 rectangles for 4 blocks with respect to the size of the screen
           Rectangle[] rects = new Rectangle[4];
           rects[0] = new Rectangle(new Point(20, 20), 800, 20);
           rects[1] = new Rectangle(new Point(0, 0), 20, 800);
           rects[2] = new Rectangle(new Point(780, 0), 20, 600);
           rects[3] = new Rectangle(new Point(0, 580), 800, 20);

           //creating the blocks
            for (int i = 0; i < 4; i++) {
                Block block = new Block(rects[i], Color.GRAY);

                //if it's death-region block
                if (i == 3) {
                    block.addHitListener(ballRemover);
                }
                block.addToGame(this);
            }
       }

    /**
     * this method is used to generate a random color.
     * @return a random color
     */
       public Color randomColor() {
           Random rand = new Random();
           float r = rand.nextFloat();
           float g = rand.nextFloat();
           float b = rand.nextFloat();
           return new Color(r, g, b);
       }

    /**
     * this method is used to initialize a new level: create the Blocks, Balls, paddle, score and the screen.
     */
    public void initialize() {
            //creating the balls
           int counterEven = 0;
           int counterOdd = 0;
           int counter = 0;
           for (int i = 0; i < level.numberOfBalls(); i++) {
               Ball b = null;
               if (counter % 2 == 0) {
                   b = new Ball(new Point(400 - (39 * counterEven), 553), 7, Color.WHITE);
                   if (counterEven == 0) {
                       counterOdd++;
                   }
                   counterEven++;
               } else {
                   b = new Ball(new Point(400 + (39 * counterOdd), 553), 7, Color.WHITE);
                   counterOdd++;
               }
               b.setVelocity(level.initialBallVelocities().get(i));
               b.setGameDecor(environment);
               b.addToGame(this);
               counter++;
           }
           ballsCounter = new Counter(level.numberOfBalls());
           BallRemover ballRemover = new BallRemover(this, ballsCounter);

           //set the boundaries blocks for the screen
           setBlocksForGuiScreen(ballRemover);

           //creating the paddle
           if (level.levelName().compareTo("Wide Easy") == 0) {
               Rectangle rectPaddle = new Rectangle(new Point(120, 560), level.paddleWidth(), 20);
               Paddle paddle = new Paddle(keyboard, rectPaddle);
               paddle.addToGame(this);
           } else {
               Rectangle rectPaddle = new Rectangle(new Point(350, 560), level.paddleWidth(), 20);
               Paddle paddle = new Paddle(keyboard, rectPaddle);
               paddle.addToGame(this);
           }

           //creating the score
           ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
           ScoreIndicator si = new ScoreIndicator(this.score);
           si.addToGame(this);

           LevelIndicator li = new LevelIndicator(level.levelName());
           li.addToGame(this);

           blocksCounter = new Counter(0);
           BlockRemover blockRemover = new BlockRemover(this, blocksCounter);

          for (Block b : level.blocks()) {
              b.addToGame(this);
              blocksCounter.increase(1);
              b.addHitListener(blockRemover);
              b.addHitListener(stl);
           }

           blockRemover.setRemainingBlocks(blocksCounter);
           this.runner = new AnimationRunner(this.gui, 60);
       }

    /**
     * this method is used to run the game -- start the animation loop.
     */
       public void run() {
           this.runner.run(new CountdownAnimation(3, 3, sprites, this.level));
           this.running = true;
           this.runner.run(this);
       }

    /**
     * This method is used to remove a sprite from the game.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.keyboard = gui.getKeyboardSensor();

        if (this.keyboard.isPressed("p")) {
            PauseScreen ps = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, ps));
        }

        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the amount of balls currently are in this level
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
    * @return the amount of blocks currently are in this level
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    public static int getGuiWidth() {
        return 800;
    }

    public static int getGuiHeight() {
        return 600;
    }
}


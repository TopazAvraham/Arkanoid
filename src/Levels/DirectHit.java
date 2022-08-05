//Topaz Avraham 206842627

package Levels;

import Collision.Velocity;
import Game.GameLevel;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Shapes.Point;
import Sprites.Block;
import Sprites.ImageBackground;
import biuoop.DrawSurface;
import Shapes.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Topaz Avraham
 * This class is used to represent the first level in the game.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velList = new LinkedList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velList.add(new Velocity(0, -10));
        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {

                d.setColor(Color.black);
                d.fillRectangle(0, 0, (int) GameLevel.getGuiWidth(), (int) GameLevel.getGuiHeight());
                d.setColor(Color.blue);
                d.drawCircle((int) GameLevel.getGuiWidth() / 2, 140, 100);
                d.drawCircle((int) GameLevel.getGuiWidth() / 2, 140, 80);
                d.drawCircle((int) GameLevel.getGuiWidth() / 2, 140, 60);
                d.drawLine(280, 135, 380, 135);
                d.drawLine(420, 135, 520, 135);
                d.drawLine((int) GameLevel.getGuiWidth() / 2, 40, (int) GameLevel.getGuiWidth() / 2, 126);
                d.drawLine((int) GameLevel.getGuiWidth() / 2, 150, (int) GameLevel.getGuiWidth() / 2, 250);
                d.setColor(new Color(220, 205, 45));

            }

            @Override
            public void timePassed() {
            }
        };
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        blockList.add(new Block(new Rectangle(new Point(385, 120), 30, 30), Color.RED));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}

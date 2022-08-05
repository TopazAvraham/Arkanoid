//Topaz Avraham 206842627

package Levels;

import Collision.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;
import Sprites.ImageBackground;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Topaz Avraham
 * This class is used to represent the second level in the game.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velList = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < numberOfBalls(); i++) {
            velList.add(new Velocity(rand.nextInt(4) + 4, rand.nextInt(4) + 4));
        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite easyBackground = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(new Color(239, 231, 176));
                d.fillCircle(150, 150, 60);
                for (int x = 10; x <= 780; x += 10) {
                    d.drawLine(150, 150, x, 250);
                }
                d.setColor(new Color(236, 215, 73));
                d.fillCircle(150, 150, 50);
                d.setColor(new Color(255, 255, 24));
                d.fillCircle(150, 150, 40);

            }

            @Override
            public void timePassed() {

            }
        };

       return easyBackground;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new LinkedList<>();
        Color chosenColor = Color.BLACK;
        int counter = 0;
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                chosenColor = Color.RED;
            } else if (i < 4) {
                chosenColor = Color.orange;
            } else if (i < 6) {
                chosenColor = Color.yellow;
            } else if (i < 9) {
                chosenColor = Color.green;
            } else if (i < 11) {
                chosenColor = Color.blue;
            } else if (i < 13) {
                chosenColor = Color.pink;
            } else {
                chosenColor = Color.CYAN;
            }
            Block block = new Block(new Shapes.Rectangle(
                    new Shapes.Point(20 + (50.7 * i), 250), 50.7, 25), chosenColor);
            blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }
}

//Topaz Avraham 206842627

package Levels;

import Collision.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Shapes.Point;
import Sprites.Block;
import Sprites.ImageBackground;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Topaz Avraham
 * This class is used to represent the third level in the game.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velList = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < numberOfBalls(); i++) {
            velList.add(new Velocity(rand.nextInt(4) + 3, rand.nextInt(4) + 3));
        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 110;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite greenBackground = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(50, 200, 50));
                d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

                d.setColor(Color.BLACK);
                d.fillRectangle(110, 200, 10, 200);

                d.setColor(Color.RED);
                d.fillCircle(115, 200, 12);

                d.setColor(Color.ORANGE);
                d.fillCircle(115, 200, 8);

                d.setColor(Color.WHITE);
                d.fillCircle(115, 200, 3);

                d.setColor(Color.BLACK);
                d.fillRectangle(100, 400, 30, 200);

                d.setColor(Color.BLACK);
                d.fillRectangle(65, 450, 100, 200);

                d.setColor(Color.WHITE);

                for (int x = 0; x < 5; ++x) {
                    for (int y = 0; y < 5; ++y) {
                        d.fillRectangle(75 + x * 18, 460 + y * 32, 10, 25);
                    }
                }

            }

            @Override
            public void timePassed() {

            }
        };
        return greenBackground;
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


    @Override
    public List<Block> blocks() {

        List<Block> blockList = new LinkedList<>();
        Block block = null;
        int counterForRows = 0;
        while (counterForRows < 5) {
            Color randomColor = randomColor();
            int counterForCols = counterForRows;
            while (counterForCols < 10) {
                int leftUpY = 130 + counterForRows * 20;
                int leftUpX = 280 + counterForCols * 50;
                Shapes.Rectangle blockRectangle = new Shapes.Rectangle(new Point(leftUpX, leftUpY), 50, 20);

                switch (counterForRows) {
                    case 0:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 1:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 2:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 3:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 4:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 5:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    default:
                        return null;
                }
                blockList.add(block);
                counterForCols++;
            }
            counterForRows++;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}

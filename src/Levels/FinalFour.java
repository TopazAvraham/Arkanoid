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
 * This class is used to represent the forth level in the game.
 */
public class FinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
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
        return 120;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite finalFourBackground = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#1788d0"));
                d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(200 + i * 10, 400, 230 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(200, 400, 25);
                d.fillCircle(220, 420, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(240, 390, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(260, 420, 23);
                d.fillCircle(280, 400, 35);

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(500 + i * 10, 350, 530 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(500, 350, 25);
                d.fillCircle(520, 370, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(540, 340, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(560, 370, 23);
                d.fillCircle(580, 350, 35);
            }

            @Override
            public void timePassed() {

            }
        };

        return finalFourBackground;
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
        while (counterForRows < 7) {
            Color randomColor = randomColor();
            int counterForCols = 0;
            while (counterForCols < 15) {
                double leftUpY = 100 + counterForRows * 24;
                double leftUpX = 20 + counterForCols * 50.7;
                Shapes.Rectangle blockRectangle = new Shapes.Rectangle(new Point(leftUpX, leftUpY), 50.7, 24);

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
                    case 6:
                        block = new Block(blockRectangle, randomColor);
                        break;
                    case 7:
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

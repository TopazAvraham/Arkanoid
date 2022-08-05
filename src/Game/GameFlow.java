//Topaz Avraham 206842627

package Game;

import Animations.AnimationRunner;
import Animations.GameOverScreen;
import Animations.KeyPressStoppableAnimation;
import Animations.WinningScreen;
import Interfaces.LevelInformation;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Topaz Avraham
 * This class is used to run all the levels in the game one by one.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;

    /**
     * Constructor.
     * @param ar - the animation runner with will run the animations during the game
     * @param ks - the keyboard of the user
     * @param gui - the gui which the game will be played on
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
    this.ar = ar;
    this.ks = ks;
    this.gui = gui;
}

    /**
     * This method is used to run all the levels of the game one by one.
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {
    Counter entireGameScore = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, entireGameScore, gui);
            level.initialize();
            //while level has more blocks and balls
            while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
                level.run();
            }
            if (level.getBallsCounter().getValue() <= 0) {
                KeyPressStoppableAnimation kpaFinish = new KeyPressStoppableAnimation(
                        ks, KeyboardSensor.SPACE_KEY, new GameOverScreen(entireGameScore.getValue()));
                ar.run(kpaFinish);
                return;
            }
        }
        KeyPressStoppableAnimation kpaWinning = new KeyPressStoppableAnimation(
                ks, KeyboardSensor.SPACE_KEY, new WinningScreen(entireGameScore.getValue()));
        ar.run(kpaWinning);
        return;
    }
}
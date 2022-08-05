//Topaz Avraham 206842627

import Animations.AnimationRunner;
import Game.GameFlow;
import Interfaces.LevelInformation;
import Levels.DirectHit;
import Levels.FinalFour;
import Levels.Green3;
import Levels.WideEasy;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Topaz Avraham
 * This class is used to run the levels of the "Arkanoid" game
 */
public class Ass6Game {

    /**
     * This method is used to run the level the user wants to play.
     * If no input was entered all the levels are run as default
     * @param args - the levels the user wants to play
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        //if the user didn't enter his choice
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        } else {
            //the user entered a choice
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "1":
                        levels.add(new DirectHit());
                        break;

                    case "2":
                        levels.add(new WideEasy());
                        break;

                    case "3":
                        levels.add(new Green3());
                        break;

                    case "4":
                        levels.add(new FinalFour());
                        break;

                    default:
                        continue;
                }
            }
            //if reached here- the user entered only words without numbers- so as default, it adds all levels
            if (levels.size() == 0) {
                levels.add(new DirectHit());
                levels.add(new WideEasy());
                levels.add(new Green3());
                levels.add(new FinalFour());
            }
        }

        GUI gui = new GUI("Ass6", 800, 600);
        GameFlow gf = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor(), gui);
        gf.runLevels(levels);
        gui.close();
    }
}

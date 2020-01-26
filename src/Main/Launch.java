package Main;


import java.awt.*;

/**
 * Created by AlexVR on 1/24/2020.
 */

public class Launch {

    public static void main(String[] args) {
        GameSetUp game = new GameSetUp("Game Engines", Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        game.start();
    }
}

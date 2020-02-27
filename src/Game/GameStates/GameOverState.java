package Game.GameStates;

import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Main.Handler;
import Resources.Images;

import java.awt.*;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class GameOverState extends State {

    private int count = 0;
    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        refresh();

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pausebutt && count>=30){
            count=0;

            State.setState(handler.getGameState());
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.gameOverBackground,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }

    @Override
    public void refresh() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        uiManager.addObjects(new UIImageButton(56, 223, 128, 64, Images.restartButton, () -> {
            handler.getMouseManager().setUimanager(null);
                State.setState(handler.getGalagaState());
                handler.getGalagaState().entityManager.playerShip.setHealth(3);
        }));

        uiManager.addObjects(new UIImageButton(56, (223+(64+16)), 128, 64, Images.pauseToTitleButton, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getMenuState());
            handler.getGalagaState().entityManager.playerShip.setHealth(3);
        }));
    }
}

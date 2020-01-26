package Game.Galaga.Entities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 1/25/2020
 */
public class EntityManager {

    public ArrayList<BaseEntity> entities;
    public PlayerShip playerShip;

    public EntityManager(PlayerShip playerShip) {
        entities = new ArrayList<>();
        this.playerShip = playerShip;
    }

    public void tick(){
        playerShip.tick();
        for (BaseEntity entity: entities){
            entity.tick();
            if (entity.bounds.intersects(playerShip.bounds)){
                playerShip.damage(entity);
            }
        }

    }

    public void render(Graphics g){
        for (BaseEntity entity: entities){
            entity.render(g);
        }
        playerShip.render(g);

    }

}

package Game.Galaga.Entities;

import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 1/25/2020
 */
public class EnemyLaser extends BaseEntity {

    EntityManager player;
    int speed = 6;

    public EnemyLaser(int x, int y, int width, int height, BufferedImage sprite, Handler handler,EntityManager player) {
        super(x, y, width, height, sprite, handler);
        this.player=player;
    }

    @Override
    public void tick() {
        if (!remove) {
            super.tick();
            y += speed;
            bounds.y = y;
            for (BaseEntity player : player.entities) {
                if (player instanceof MyEnemy || player instanceof EnemyLaser || player instanceof EnemyBee) {
                    continue;
                }
                if (player.bounds.intersects(bounds)) {
                    player.damage(this);
                }
            }
        }
    }
}

package Game.Galaga.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 1/25/2020
 */
public class BaseEntity {
    public int x,y,width,height;
    public BufferedImage sprite;
    public Rectangle bounds;
    Handler handler;

    public BaseEntity(int x, int y, int width, int height, BufferedImage sprite, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.bounds = new Rectangle(x,y,width,height);
        this.handler = handler;
    }

    public void tick(){

    }

    public void render(Graphics g) {
        g.drawImage(sprite,x,y,width,height,null);
        if (Handler.DEBUG){
            g.setColor(Color.red);
            ((Graphics2D)g).draw(bounds);
        }
    }

    public void damage(BaseEntity damageSource) {
    }

}

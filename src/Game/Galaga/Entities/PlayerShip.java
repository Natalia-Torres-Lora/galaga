package Game.Galaga.Entities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.MenuState;
import Game.GameStates.PauseState;

/**
 * Created by AlexVR on 1/25/2020
 */
public class PlayerShip extends BaseEntity{

	private int health = 3,attackCooldown = 30,speed =6,destroyedCoolDown = 60*7;
	private boolean attacking = false, destroyed = false;
	private Animation deathAnimation;
//	//Random ran = new Random();
//	int position[][] = new int[4][8];
//	int oldPosition[][]=new int[4][8];

     public PlayerShip(int x, int y, int width, int height, BufferedImage sprite, Handler handler) {
        super(x, y, width, height, sprite, handler);
        deathAnimation = new Animation(256,Images.galagaPlayerDeath);
           
    }

    @Override
    public void tick() {
        super.tick();
        if (destroyed){
            if (destroyedCoolDown<=0){
                destroyedCoolDown=60*7;
                destroyed=false;
                deathAnimation.reset();
                bounds.x=x;
            }else{
                deathAnimation.tick();
                destroyedCoolDown--;
            }
        }else {
            if (attacking) {
                if (attackCooldown <= 0) {
                    attacking = false;
                } else {
                    attackCooldown--;
                }
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attacking) {
                handler.getMusicHandler().playEffect("laser.wav");
                attackCooldown = 15;
                attacking = true;
                handler.getGalagaState().entityManager.entities.add(new PlayerLaser(this.x + (width / 2), this.y - 3, width / 5, height / 2, Images.galagaPlayerLaser, handler, handler.getGalagaState().entityManager));

			}
			if (handler.getKeyManager().left) {
				x -= (speed);
			}
			if (handler.getKeyManager().right) {
				x += (speed);
			}
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
            	int row= new Random().nextInt(3)+3;
            	int col= new Random().nextInt(8);
//            	while(position[row][col]==oldPosition[row][col]) {
//            		row=new Random().nextInt(3)+3;
//            		col= new Random().nextInt(8);
//            		oldPosition[row][col]=position[row][col];
            	handler.getGalagaState().entityManager.entities.add(new EnemyBee(0,0,32,32,handler,row,col));
            	
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_O)) {
            	int row= new Random().nextInt(2)+1;
            	int col= new Random().nextInt(6)+1;
            	handler.getGalagaState().entityManager.entities.add(new MyEnemy(0,0,32,32,handler,row,col));
            }           
            
            //Adding left and right bounds
            if (handler.getKeyManager().left) {
                x -= (speed);
                if(x<arena.x) {
                	x=arena.x;
                }
            }
            if (handler.getKeyManager().right) {
                x += (speed);
                if(x >(arena.x + arena.width-width)){
                	x = arena.x + arena.width-width;
                }
            }
			if (handler.getKeyManager().die){
				if(getHealth()==0) {
					handler.changeState(handler.getGameOverState());
				}
				else {
					setHealth(getHealth()-1);
					destroyed = true;
				}
				
			}
            
			if (handler.getKeyManager().addLife){
				if(getHealth()!=3)
					setHealth(getHealth()+1);
			}

			bounds.x = x;
    }
}

    

	@Override
	public void render(Graphics g) {
		if (destroyed){
			if (deathAnimation.end){
				g.drawString("READY",handler.getWidth()/2-handler.getWidth()/12,handler.getHeight()/2);
			}else {
				g.drawImage(deathAnimation.getCurrentFrame(), x, y, width, height, null);
			}
		}else {
			super.render(g);
		}
	}

	@Override
	public void damage(BaseEntity damageSource) {
		if (damageSource instanceof PlayerLaser){
			return;
		}
		health--;
		destroyed = true;
		handler.getMusicHandler().playEffect("explosion.wav");

		bounds.x = -10;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

}

package Modul;

import Animation.Animation;
import Animation.DataLoader;

import java.awt.*;
import java.util.Random;

public class Boss extends BomberGame {

    private Animation bossRightAnim;
    private Animation bossLeftAnim;
    private Animation bossUpAnim;
    private Animation bossDownAnim;

    private Animation monterRightAnim;
    private Animation monterLeftAnim;
    private Animation monterUpAnim;
    private Animation monterDownAnim;

    private Animation monterARight;
    private Animation monterALeft;
    private Animation monterAUp;
    private Animation monterADown;


    Manager manager;

    public Boss(float x, float y,Manager manager,int type) {
        super(x, y, 40, 40, 1, manager);
        setType(type);
        bossLeftAnim = DataLoader.getInstance().getAnimation("bossleft");

        bossRightAnim = DataLoader.getInstance().getAnimation("bossright");

        bossUpAnim = DataLoader.getInstance().getAnimation("bossup");

        bossDownAnim = DataLoader.getInstance().getAnimation("bossdown");
//---------------------------------
        monterLeftAnim = DataLoader.getInstance().getAnimation("monsterleft");

        monterRightAnim = DataLoader.getInstance().getAnimation("monsterright");

        monterUpAnim = DataLoader.getInstance().getAnimation("monsterup");

        monterDownAnim = DataLoader.getInstance().getAnimation("monsterdown");

        monterALeft= DataLoader.getInstance().getAnimation("monsterAleft");


        monterAUp= DataLoader.getInstance().getAnimation("monsterAup");
        monterADown= DataLoader.getInstance().getAnimation("monsterAdown");





    }

    public void draw(Graphics2D g2d){
        if (getType() == ENEMY){
                if(getDirboss() == RIGHT_DIR){
                    monterRightAnim.updateImage(System.nanoTime());
                    monterRightAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirboss() == LEFT_DIR){
                    monterLeftAnim.updateImage(System.nanoTime());
                    monterLeftAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirboss() == UP_DIR){
                    monterUpAnim.updateImage(System.nanoTime());
                    monterUpAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirboss() == DOWN_DIR){
                    monterDownAnim.updateImage(System.nanoTime());
                    monterDownAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
        }
        if (getType() == MONSTERA){
            if(getDirboss() == RIGHT_DIR){

                monterARight.updateImage(System.nanoTime());
                monterARight.draw((int) (getPosX()), (int) getPosY() , g2d);
            }
            if(getDirboss() == LEFT_DIR){
                monterALeft.updateImage(System.nanoTime());
                monterALeft.draw((int) (getPosX()), (int) getPosY() , g2d);
            }
            if(getDirboss() == UP_DIR){
                monterAUp.updateImage(System.nanoTime());
                monterAUp.draw((int) (getPosX()), (int) getPosY() , g2d);
            }
            if(getDirboss() == DOWN_DIR){
                monterADown.updateImage(System.nanoTime());
                monterADown.draw((int) (getPosX()), (int) getPosY() , g2d);
            }
        }

        if (getType() == MONSTER) {
            if (getDirboss() == RIGHT_DIR) {
                bossRightAnim.updateImage(System.nanoTime());
                bossRightAnim.draw((int) (getPosX()), (int) getPosY(), g2d);
            }
            if (getDirboss() == LEFT_DIR) {
                bossLeftAnim.updateImage(System.nanoTime());
                bossLeftAnim.draw((int) (getPosX()), (int) getPosY(), g2d);
            }
            if (getDirboss() == UP_DIR) {
                bossUpAnim.updateImage(System.nanoTime());
                bossUpAnim.draw((int) (getPosX()), (int) getPosY(), g2d);
            }
            if (getDirboss() == DOWN_DIR) {
                bossDownAnim.updateImage(System.nanoTime());
                bossDownAnim.draw((int) (getPosX()), (int) getPosY(), g2d);
            }
        }


    }

    public void update(){
        super.update();
        setSpeedBossy(1);
        setSpeedBossX(1);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithEnemy();
        return rect;
    }

    @Override
    public void attack() {

    }
    public boolean isImpactBossVsBombang(BomBang bomBang){
        if(getState()==DEAD){
            return false;
        }
        Rectangle rec1 = getBoundForCollisionWithMap();
        Rectangle rec2 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2) - 45, (int)(bomBang.getPosY() - getHeight()/2), (int)bomBang.getWidth() - 10,(int) bomBang.getHeight()-10);
        Rectangle rec3 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2) + 45, (int)(bomBang.getPosY() - getHeight()/2), (int)bomBang.getWidth()-10,(int) bomBang.getHeight()-10);
        Rectangle rec4 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2), (int)(bomBang.getPosY() - getHeight()/2) -45 , (int)bomBang.getWidth()-10,(int) bomBang.getHeight()-10);
        Rectangle rec5 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2), (int)(bomBang.getPosY() - getHeight()/2) + 45, (int)bomBang.getWidth()-10,(int) bomBang.getHeight()-10);
        if (rec1.intersects(rec2) || rec1.intersects(rec3) || rec1.intersects(rec4) || rec1.intersects(rec5)){
            return true;
        }
        return false;
    }
    public boolean isImpactBossVsBomb(Bomb bomb){
        if(getState()==DEAD){
            return false;
        }
        Rectangle rec1 = getBoundForCollisionWithMap();
        Rectangle rec2 = new Rectangle((int)((bomb.getPosX()/45))*45, ((int)(bomb.getPosY()/45))*45, (int)bomb.getWidth(),(int) bomb.getHeight());
        return rec1.intersects(rec2);
    }
}

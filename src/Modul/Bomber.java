package Modul;

import Animation.Animation;
import Animation.DataLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class Bomber extends BomberGame {

    public static final int RUNSPEED = 3;
    private Animation runRightAnim;
    private Animation runLeftAnim;
    private Animation runUpAnim;
    private Animation runDownAnim;

    private Animation idleUpAnim, idleDownAnim,idleLeftAnim,idleRightAnim;

    Manager manager;

    private int speed;

    public Bomber(float x, float y,Manager manager) {
        super(x, y, 20, 25, 3, manager);
        setType(BOMBER);
        this.speed = 3;

        runLeftAnim = DataLoader.getInstance().getAnimation("left");

        runRightAnim = DataLoader.getInstance().getAnimation("right");

        runUpAnim = DataLoader.getInstance().getAnimation("up");

        runDownAnim = DataLoader.getInstance().getAnimation("down");

        idleUpAnim = DataLoader.getInstance().getAnimation("idleup");
        idleRightAnim = DataLoader.getInstance().getAnimation("idleright");
        idleDownAnim = DataLoader.getInstance().getAnimation("idledown");
        idleLeftAnim = DataLoader.getInstance().getAnimation("idleleft");
    }

    public void draw(Graphics2D g2d){

        switch (getState()){
            case ALIVE:
                if (getSpeedX() > 0){
                    runRightAnim.updateImage(System.nanoTime());
                    runRightAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                    break;
                }
                if (getSpeedX() < 0){
                    runLeftAnim.updateImage(System.nanoTime());
                    runLeftAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                    break;
                }
                if (getSpeedY() < 0){
                    runUpAnim.updateImage(System.nanoTime());
                    runUpAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                    break;
                }
                if (getSpeedY() > 0){
                    runDownAnim.updateImage(System.nanoTime());
                    runDownAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                    break;
                }
                if(getDirection() == RIGHT_DIR){
                    idleRightAnim.updateImage(System.nanoTime());
                    idleRightAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirection() == LEFT_DIR){
                    idleLeftAnim.updateImage(System.nanoTime());
                    idleLeftAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirection() == UP_DIR){
                    idleUpAnim.updateImage(System.nanoTime());
                    idleUpAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
                if(getDirection() == DOWN_DIR){
                    idleDownAnim.updateImage(System.nanoTime());
                    idleDownAnim.draw((int) (getPosX()), (int) getPosY() , g2d);
                }
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void update(){
       super.update();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }


    @Override
    public void attack() {

    }

    public void move(){
        if (getDirection() == LEFT_DIR){
            setSpeedX(-getSpeed());
        }
        if (getDirection() == RIGHT_DIR){
            setSpeedX(getSpeed());
        }
        if (getDirection() == UP_DIR){
            setSpeedY(-getSpeed());
        }
        if (getDirection() == DOWN_DIR){
            setSpeedY(getSpeed());
        }
    }
    public static void playSound(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
    public boolean isImpactBomberVsBoss(Boss boss){
        if(getState()==DEAD){

            return false;
        }
        Rectangle rec1 = getBoundForCollisionWithMap();
        Rectangle rec2 = new Rectangle((int) (boss.getPosX() - getWidth()/2), (int)(boss.getPosY() - getHeight()/2), (int)boss.getWidth(),(int) boss.getHeight());
        return rec1.intersects(rec2);
    }
    public boolean isImpactBomberVsBombang(BomBang bomBang){
        if(getState()==DEAD){
            return false;
        }
        Rectangle rec1 = getBoundForCollisionWithMap();
        Rectangle rec2 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2) - 45, (int)(bomBang.getPosY() - getHeight()/2), (int)bomBang.getWidth(),(int) bomBang.getHeight());
        Rectangle rec6 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2), (int)(bomBang.getPosY() - getHeight()/2), (int)bomBang.getWidth(),(int) bomBang.getHeight());
        Rectangle rec3 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2) + 45, (int)(bomBang.getPosY() - getHeight()/2), (int)bomBang.getWidth(),(int) bomBang.getHeight());
        Rectangle rec4 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2), (int)(bomBang.getPosY() - getHeight()/2) -45 , (int)bomBang.getWidth(),(int) bomBang.getHeight());
        Rectangle rec5 = new Rectangle((int) (bomBang.getPosX() - getWidth()/2), (int)(bomBang.getPosY() - getHeight()/2) + 45, (int)bomBang.getWidth(),(int) bomBang.getHeight());
        if (rec1.intersects(rec2) || rec1.intersects(rec3) || rec1.intersects(rec4) || rec1.intersects(rec5)|| rec1.intersects(rec6)){
            return true;
        }
        return false;
    }
    public int isImpactBomberVsItem(){
        if(getState()==DEAD){
            return 0;
        }
        Rectangle rec1 = getBoundForCollisionWithMap();
        Rectangle rect2 = new Rectangle(0,0,0,0);
        Rectangle rect3 = new Rectangle(0,0,0,0);
        for (int y = 0 ; y < getManager().physicMap.phys_map.length ; y++){
            for (int x = 0;x < getManager().physicMap.phys_map[0].length;x++){
                if (getManager().physicMap.phys_map[y][x] == 4){
                    rect2 = new Rectangle(x*45,y*45,45,45);
                }
                if (getManager().physicMap.phys_map[y][x] == 6){
                    rect3 = new Rectangle(x*45,y*45,45,45);
                }
            }
        }
        if (rec1.intersects(rect2)){
            return 1;
        }
        if (rec1.intersects(rect3)){
            return 2;
        }
        return 0;
    }
}

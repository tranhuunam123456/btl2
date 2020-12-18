package Modul;

import Animation.Animation;

import java.awt.*;
import java.util.Timer;

import Ui.Main;
//import sound.GameSound;

public abstract class PartcularGame extends Entity{
    public static final int ENEMY = 2;
    public static final int BOMBER = 1;
    public static final int MONSTER = 3;
    public static final int MONSTERA = 4;


    public static final int LEFT_DIR = 1;
    public static final int RIGHT_DIR = 2;
    public static final int UP_DIR = 3;
    public static final int DOWN_DIR = 4;

    public static final int ALIVE = 1;
    public static final int DEAD = 2;


    private int state = ALIVE;

    private float width;
    private float height;
    private float speedX;
    private float speedY;
    private float speedBossX;
    private float speedBossy;
    private float heart;

    private int direction;
    private int dirboss;
    private int timeDead = 0;

    protected Animation forwardAnim , backAnim;
    private int type;
    Main main;
    Timer timer;



    public PartcularGame(float x, float y, float width,float height,float heart,Manager manager) {
        super(x, y, manager);
        setWidth(width);
        setHeight(height);
        setHeart(heart);
        direction = DOWN_DIR;
        dirboss = DOWN_DIR;

    }

    public float getSpeedBossX() {
        return speedBossX;
    }

    public void setSpeedBossX(float speedBossX) {
        this.speedBossX = speedBossX;
    }

    public float getSpeedBossy() {
        return speedBossy;
    }

    public void setSpeedBossy(float speedBossy) {
        this.speedBossy = speedBossy;
    }

    public int getDirboss() {
        return dirboss;
    }

    public void setDirboss(int dirboss) {
        this.dirboss = dirboss;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getHeart() {
        return heart;
    }

    public void setHeart(float heart) {
        this.heart = heart;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public abstract void attack();


    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();

        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width =  (int) getWidth();
        bound.height = (int) getHeight();
        return bound;

    }
    public void beHurt(){
        setHeart(getHeart() - 1);
        if (getManager().bomber.getHeart() == 0){
            state = DEAD;
        }
        else if (getManager().bomber.getHeart() > 0){
           // GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
            while (timeDead < 100000){
                timeDead++;
                System.out.println(timeDead);
            }
            if (timeDead == 100000){
                setPosX(65);
                setPosY(65);
                timeDead = 0;
            }
        }
    }

    @Override
    public void update(){
        if (getType() == BOMBER){
            switch (state){
                case ALIVE:
                    for(int i = 0;i  < getManager().boss.size() ; i++){
                        if (getManager().bomber.isImpactBomberVsBoss(getManager().boss.get(i))){
                                beHurt();
                        }
                    }
                    break;
                case DEAD:
                    break;
            }
        }
    }

    public void drawWithMap(Graphics2D g2d){
        Rectangle rect = getBoundForCollisionWithMap();

        g2d.setColor(Color.RED);
        g2d.drawRect(rect.x , rect.y , rect.width ,rect.height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(rect.x + rect.width/2 , rect.y + rect.height/2 ,2,2);
    }
    public Rectangle getBoundForCollisionWithEnemy(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() + getWidth());
        bound.y = (int) (getPosY() + getHeight());
        bound.width =  (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    };

    public abstract void draw(Graphics2D g2d);


}

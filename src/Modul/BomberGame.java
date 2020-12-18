package Modul;

import java.awt.*;
import java.util.Random;

public abstract class BomberGame extends PartcularGame {
    private boolean isRunning;
    private Random random = new Random();

    public BomberGame(float x, float y, float width, float height, float heart, Manager manager) {
        super(x, y, width, height, heart, manager);
        setState(ALIVE);
    }


    @Override
    public void update(){
        super.update();
        if (getType() == BOMBER){
            if (getState() == ALIVE){
                setPosX(getPosX() + getSpeedX());
                setPosY(getPosY() + getSpeedY());

                if (getDirection() == LEFT_DIR &&
                        getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){
                    Rectangle rectLeftWall = getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                }
                if(getDirection() == RIGHT_DIR &&
                        getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){
                    Rectangle rectRightWall = getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);
                }
                if(getDirection() == DOWN_DIR &&
                        getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap())!=null){
                    Rectangle rectLandWall = getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap());
                    setPosY(rectLandWall.y - getHeight()/2);
                }
                if(getDirection() == UP_DIR &&
                        getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap())!=null){
                    Rectangle rectTopWall = getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap());
                    setPosY(rectTopWall.y + rectTopWall.height + getHeight()/2);
                }
            }
        }
        if (getType() == ENEMY){
            if (getState() == ALIVE){
                if (getDirboss() == LEFT_DIR){
                    setSpeedBossX(-2);
                    setSpeedBossy(0);
                }
                if (getDirboss() == RIGHT_DIR){
                    setSpeedBossX(2);
                    setSpeedBossy(0);
                }
                if (getDirboss() == UP_DIR){
                    setSpeedBossy(-2);
                    setSpeedBossX(0);
                }
                if (getDirboss() == DOWN_DIR){
                    setSpeedBossy(2);
                    setSpeedBossX(0);
                }
                setPosX(getPosX() + getSpeedBossX());
                setPosY(getPosY() + getSpeedBossy());
                if (getDirboss() == LEFT_DIR &&
                        getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){
                    Rectangle rectLeftWall = getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == RIGHT_DIR &&
                        getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){
                    Rectangle rectRightWall = getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == DOWN_DIR &&
                        getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLandWall = getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap());
                    setPosY(rectLandWall.y - getHeight()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == UP_DIR &&
                        getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap())!=null){

                    Rectangle rectTopWall = getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap());

                    setPosY(rectTopWall.y + rectTopWall.height + getHeight()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
            }
        }
        if (getType() == MONSTER){
            if (getState() == ALIVE){
                if (getDirboss() == LEFT_DIR){
                    setSpeedBossX(-2);
                    setSpeedBossy(0);
                }
                if (getDirboss() == RIGHT_DIR){
                    setSpeedBossX(2);
                    setSpeedBossy(0);
                }
                if (getDirboss() == UP_DIR){
                    setSpeedBossy(-2);
                    setSpeedBossX(0);
                }
                if (getDirboss() == DOWN_DIR){
                    setSpeedBossy(2);
                    setSpeedBossX(0);
                }
                setPosX(getPosX() + getSpeedBossX());
                setPosY(getPosY() + getSpeedBossy());
                if (getDirboss() == LEFT_DIR &&
                        getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){
                    Rectangle rectLeftWall = getManager().physicMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == RIGHT_DIR &&
                        getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){
                    Rectangle rectRightWall = getManager().physicMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == DOWN_DIR &&
                        getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLandWall = getManager().physicMap.haveCollisionWithLand(getBoundForCollisionWithMap());
                    setPosY(rectLandWall.y - getHeight()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
                if(getDirboss() == UP_DIR &&
                        getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap())!=null){

                    Rectangle rectTopWall = getManager().physicMap.haveCollisionWithTop(getBoundForCollisionWithMap());

                    setPosY(rectTopWall.y + rectTopWall.height + getHeight()/2);
                    setDirboss(random.nextInt(4) + 1);
                }
            }
        }

    }
}

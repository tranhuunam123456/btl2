package Modul;

import Animation.Animation;
import Animation.DataLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb extends BomberGame{

    private int size;
    private long timeLine;
    private Animation bomB;
    private int newX;
    private int newY;
    BufferedImage imageBomb,imageBombang;

    public Bomb(float x, float y, long timeLine,Manager manager) {
        super(x, y, 45, 45, 0, manager);
        bomB = DataLoader.getInstance().getAnimation("bomb");
        this.timeLine = timeLine;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public void attack() {

    }

    @Override
    public void draw(Graphics2D g2d) {
        newX = ((int)(getPosX()/45))*45;
        newY = ((int)(getPosY()/45))*45;
        bomB.updateImage(System.nanoTime());
        bomB.draw((int) (newX + getWidth()/2), (int)(newY + getHeight()/2) , g2d);
        }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }
}

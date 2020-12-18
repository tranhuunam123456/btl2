package Modul;

import Animation.Animation;
import Animation.DataLoader;


import java.awt.*;


public class BomBang extends Bomb{

    private Animation bombangAnim;
    private int newX;
    private int newY;


    public BomBang(float x, float y,long timeLine ,Manager manager) {
        super(x, y,timeLine, manager);
        bombangAnim = DataLoader.getInstance().getAnimation("bombang");
    }

    @Override
    public void attack() {

    }

    @Override
    public void draw(Graphics2D g2d) {
        newX = ((int)(getPosX()/45))*45;
        newY = ((int)(getPosY()/45))*45;
        bombangAnim.updateImage(System.nanoTime());
        bombangAnim.draw((int) (newX + getWidth()/2), (int)(newY + getHeight()/2) , g2d);

                if (getManager().physicMap.phys_map[newY/45 - 1][newX/45] != 1){
                    bombangAnim.updateImage(System.nanoTime());
                    bombangAnim.draw((int) (newX + getWidth()/2), (int) ((newY + getHeight()/2) - getHeight()) , g2d);
                    if (getManager().physicMap.phys_map[newY/45 - 1][newX/45] == 2){
                        getManager().physicMap.phys_map[newY/45 - 1][newX/45] = 0;
                    }
                    if (getManager().physicMap.phys_map[newY/45 - 1][newX/45] == 3){
                        getManager().physicMap.phys_map[newY/45 - 1][newX/45] = 4;
                    }
                    if (getManager().physicMap.phys_map[newY/45 - 1][newX/45] == 5){
                        getManager().physicMap.phys_map[newY/45 - 1][newX/45] = 6;
                    }
                }
                if (getManager().physicMap.phys_map[newY/45 + 1][newX/45] != 1){
                    bombangAnim.updateImage(System.nanoTime());
                    bombangAnim.draw((int) (newX + getWidth()/2), (int) ((newY + getHeight()/2)+ getHeight()) , g2d);
                    if (getManager().physicMap.phys_map[newY/45 + 1][newX/45] == 2){
                        getManager().physicMap.phys_map[newY/45 + 1][newX/45] = 0;
                    }
                    if (getManager().physicMap.phys_map[newY/45 + 1][newX/45] == 3){
                        getManager().physicMap.phys_map[newY/45 + 1][newX/45] = 4;
                    }
                    if (getManager().physicMap.phys_map[newY/45 + 1][newX/45] == 5){
                        getManager().physicMap.phys_map[newY/45 + 1][newX/45] = 6;
                    }
                }
                if (getManager().physicMap.phys_map[newY/45][newX/45 - 1] != 1){
                    bombangAnim.updateImage(System.nanoTime());
                    bombangAnim.draw((int) ((newX + getWidth()/2) - getWidth()), (int) (newY + getHeight()/2) , g2d);
                    if (getManager().physicMap.phys_map[newY/45][newX/45 - 1] == 2){
                        getManager().physicMap.phys_map[newY/45][newX/45 - 1] = 0;
                    }
                    if (getManager().physicMap.phys_map[newY/45][newX/45 - 1] == 3){
                        getManager().physicMap.phys_map[newY/45][newX/45 - 1] = 4;
                    }
                    if (getManager().physicMap.phys_map[newY/45][newX/45 - 1] == 5){
                        getManager().physicMap.phys_map[newY/45][newX/45 - 1] = 6;
                    }
                }
                if (getManager().physicMap.phys_map[newY/45][newX/45 + 1] != 1){
                    bombangAnim.updateImage(System.nanoTime());
                    bombangAnim.draw((int) ((newX + getWidth()/2)+ getWidth()), (int) (newY + getHeight()/2), g2d);
                    if (getManager().physicMap.phys_map[newY/45][newX/45 + 1] == 2){
                        getManager().physicMap.phys_map[newY/45][newX/45 + 1] = 0;
                    }
                    if (getManager().physicMap.phys_map[newY/45][newX/45 + 1] == 3){
                        getManager().physicMap.phys_map[newY/45][newX/45 + 1] = 4;
                    }
                    if (getManager().physicMap.phys_map[newY/45][newX/45 + 1] == 5){
                        getManager().physicMap.phys_map[newY/45][newX/45 + 1] = 6;
                    }
                }

    }
}

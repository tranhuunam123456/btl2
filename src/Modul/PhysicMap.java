package Modul;

import Animation.DataLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhysicMap extends Entity{
    public int[][] phys_map;
    private int tileSize;

    BufferedImage image;

    {
        try {
            image = ImageIO.read(new File("src/Data/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage imageHeart;

    {
        try {
            imageHeart = ImageIO.read(new File("src/Data/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage imageBox;

    {
        try {
            imageBox = ImageIO.read(new File("src/Data/box.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    BufferedImage itemSpeed;

    {
        try {
            itemSpeed = ImageIO.read(new File("src/Data/item_shoe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage imgheart1 = imageHeart.getSubimage(0,0,45,45);

    BufferedImage img = image.getSubimage(48,0,48,48);
    BufferedImage img1 = image.getSubimage(192,38,64,80);
    BufferedImage img3 = image.getSubimage(96,48,48,48);
    public PhysicMap(float x , float y , Manager manager) {
        super(x ,y , manager);
        this.tileSize = 45;
        phys_map = DataLoader.getInstance().getPhysicalMap();
    }

    public int getTileSize() {
        return tileSize;
    }

    public void drawMenu(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("", Font.PLAIN, 40));
        g2d.drawString("Level 1" , 810 , 60);

        for (int i = 1; i <= getManager().bomber.getHeart();i++ ){
            g2d.drawImage(imgheart1 , 755 + i*45 , 80,null );
        }


    }
    public void draw(Graphics2D g2){

        for(int i = 0;i< phys_map.length;i++)
            for(int j = 0;j<phys_map[0].length;j++){
                if(phys_map[i][j]==1) {
//                    g2.drawImage(img,(int) getPosX() + j*tileSize,
//                            (int)getPosY()+ i*tileSize , tileSize, tileSize,null);
                    g2.drawImage(img,(int) getPosX() + j*tileSize,
                            (int)getPosY()+ i*tileSize , tileSize, tileSize,null);

                    g2.drawImage(img1,(int) getPosX() + 8*tileSize,
                            (int)getPosY()+ 2*tileSize , tileSize, tileSize,null);

                    g2.setColor(Color.green);
                    g2.fillRect((int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 2*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 2*tileSize , tileSize, tileSize,null);
                    g2.fillRect((int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 2*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 2*tileSize , tileSize, tileSize,null);

                    g2.setColor(Color.green);
                    g2.fillRect((int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 6*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 6*tileSize , tileSize, tileSize,null);
                    g2.fillRect((int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 6*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 6*tileSize , tileSize, tileSize,null);


                    g2.fillRect((int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 10*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 4*tileSize,
                            (int)getPosY()+ 10*tileSize , tileSize, tileSize,null);
                    g2.fillRect((int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 10*tileSize , tileSize, tileSize);
                    g2.drawImage(img3,(int) getPosX() + 12*tileSize,
                            (int)getPosY()+ 10*tileSize , tileSize, tileSize,null);
                }
                if(phys_map[i][j]==2 || phys_map[i][j] == 3 || phys_map[i][j] == 5) {
                    g2.drawImage(imageBox,(int) getPosX() + j*tileSize,
                            (int)getPosY()+ i*tileSize , tileSize, tileSize,null);
                }
                if(phys_map[i][j]==4) {
                    g2.drawImage(imgheart1,(int) getPosX() + j*tileSize,
                            (int)getPosY()+ i*tileSize , tileSize, tileSize,null);
                }
                if(phys_map[i][j]==6) {
                    g2.drawImage(itemSpeed,(int) getPosX() + j*tileSize,
                            (int)getPosY()+ i*tileSize , tileSize, tileSize,null);
                }

        }
    }
    public Rectangle haveCollisionWithTop(Rectangle rect){
        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = rect.y/tileSize;

        if(posX1 < 0) posX1 = 0;

        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        for(int y = posY; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){
                if(phys_map[y][x] == 1 || phys_map[y][x] == 2 || phys_map[y][x] == 3 || phys_map[y][x] == 5){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLand(Rectangle rect){
        int posX1 = rect.x/tileSize;
        posX1 -=2;
        int posX2 = (rect.x +rect.width)/tileSize;
        posX2+=2;

        int posY1 = (rect.y + rect.height)/tileSize;

        if (posX1 < 0) posX1 = 0;

        if (posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        for (int y = posY1 ; y < phys_map.length ; y++){
            for (int x = posX1;x < posX2;x++){
                if (phys_map[y][x] == 1 || phys_map[y][x] == 2 || phys_map[y][x] == 3 || phys_map[y][x] == 5){
                    Rectangle r = new Rectangle((int) getPosX() + x*tileSize, (int) getPosY() + y*tileSize , tileSize , tileSize);
                    if (rect.intersects(r)) {
                        return r;
                    }
                }
            }
        }
        return null;
    }
    public Rectangle haveCollisionWithLeftWall(Rectangle rect){
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;

        if(posX2 < 0) posX2 = 0;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;


        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] == 1 || phys_map[y][x] == 2 || phys_map[y][x] == 3 || phys_map[y][x] == 5){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }
    public Rectangle haveCollisionWithRightWall(Rectangle rect){
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= phys_map.length) posY2 = phys_map.length - 1;


        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(phys_map[y][x] == 1 || phys_map[y][x] == 2 || phys_map[y][x] == 3 || phys_map[y][x] == 5){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }
    @Override
    public void update() {

    }
}

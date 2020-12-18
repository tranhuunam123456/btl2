package Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGame {
    private String name;
    private BufferedImage image;

    public ImageGame(String name , BufferedImage image){
        this.name = name;
        this.image = image;
    }

    public ImageGame(ImageGame imageGame){
        image = new BufferedImage(imageGame.getImageWidth() , imageGame.getImageHeight(),
                imageGame.getImage().getType());
        Graphics g = image.getGraphics();
        g.drawImage( imageGame.getImage(),0,0,null);
    }

    public void drawImage(Graphics2D g2d , int x , int y){
        g2d.drawImage(image, x - image.getWidth()/2 , y - image.getHeight()/2, null);
    }

    ImageGame(){
        image = null;
        name = null;
    }
    public int getImageWidth(){
        return image.getWidth();
    }

    public int getImageHeight(){
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}

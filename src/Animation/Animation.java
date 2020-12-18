package Animation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private String name;
    private boolean isRepeated;

    private ArrayList<ImageGame> arrImage;
    private int currentImage;

    private ArrayList<Boolean> ignorImage;

    private ArrayList<Double> delayImage;
    private long beginTime;
    private boolean drawRectImage;


    public Animation(){
        delayImage = new ArrayList<Double>();
        beginTime = 0;

        currentImage = 0;

        ignorImage = new ArrayList<Boolean>();
        arrImage = new ArrayList<ImageGame>();

        drawRectImage = false;
        isRepeated = true;
    }

    public Animation(Animation animation){
        beginTime = animation.beginTime;
        currentImage = animation.currentImage;
        drawRectImage = animation.drawRectImage;
        isRepeated = animation.isRepeated;

        delayImage = new ArrayList<Double>();
        for (Double d : animation.delayImage){
            delayImage.add(d);
        }

        ignorImage = new ArrayList<Boolean>();
        for (boolean b : animation.ignorImage){
            ignorImage.add(b);
        }

        arrImage = new ArrayList<ImageGame>();
        for (ImageGame f : animation.arrImage){
            arrImage.add(f);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public ArrayList<ImageGame> getArrImage() {
        return arrImage;
    }

    public void setArrImage(ArrayList<ImageGame> arrImage) {
        this.arrImage = arrImage;
    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int currentImage) {
        if (currentImage >= 0 && currentImage < arrImage.size()){
            this.currentImage = currentImage;
        }
        else this.currentImage = 0;
    }

    public ArrayList<Boolean> getIgnorImage() {
        return ignorImage;
    }

    public void setIgnorImage(ArrayList<Boolean> ignorImage) {
        this.ignorImage = ignorImage;
    }

    public ArrayList<Double> getDelayImage() {
        return delayImage;
    }

    public void setDelayImage(ArrayList<Double> delayImage) {
        this.delayImage = delayImage;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawRectImage() {
        return drawRectImage;
    }

    public void setDrawRectImage(boolean drawRectImage) {
        this.drawRectImage = drawRectImage;
    }
    // -------------------
    public boolean getIgnorImage(int id) {
        return ignorImage.get(id);
    }

    public void setIgnorImage(int id){
        if (id >= 0 && id  < ignorImage.size()){
            ignorImage.set(id ,true);
        }
    }
    public void unIgnorImage(int id){
        if (id >= 0 && id  < ignorImage.size()){
            ignorImage.set(id ,true);
        }
    }

    public void reset(){
        currentImage = 0;
        beginTime = 0;

        for (int i =0 ; i< ignorImage.size() ; i++){
            ignorImage.set(i , false);
        }
    }

    public void add(ImageGame imageGame , double timeToNextImage){

        ignorImage.add(false);
        arrImage.add(imageGame);
        delayImage.add(new Double(timeToNextImage));
    }

    public BufferedImage getCurrentImages(){
        return arrImage.get(currentImage).getImage();
    }

    public void updateImage(long deltaTime){
        if (beginTime == 0) beginTime = deltaTime;
        else
            if (deltaTime - beginTime > delayImage.get(currentImage)){
                nextImage();
                beginTime = deltaTime;
            }
    }

    public void nextImage(){
        if (currentImage >= arrImage.size() - 1){
            if (isRepeated) currentImage = 0;
        }
        else currentImage++;
        if (ignorImage.get(currentImage)) nextImage();
    }

    public boolean isLastGame(){
        if (currentImage == arrImage.size() - 1){
            return true;
        }
        else return false;
    }

    public void flipAllImage(){
        for (int i = 0; i < arrImage.size(); i++){
            BufferedImage image = arrImage.get(i).getImage();

            AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth() , 0);

            AffineTransformOp op = new AffineTransformOp(tx , AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image  , null);
            arrImage.get(i).setImage(image);
        }
    }

    public void draw(int x , int y , Graphics2D g2d){
        BufferedImage image = getCurrentImages();

        g2d.drawImage(image , x - image.getWidth()/2,y - image.getHeight()/2 ,null );
        if(drawRectImage)
            g2d.drawRect(x -image.getWidth()/2 , x -image.getWidth()/2 ,image.getWidth(),image.getHeight() );
    }
}

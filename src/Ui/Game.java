package Ui;

import Animation.Animation;
import Animation.ImageGame;
import Animation.DataLoader;
import Modul.Bomber;
import Modul.Manager;
import Modul.PartcularGame;
import Modul.PhysicMap;
//import sound.GameSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements Runnable, KeyListener {
    public static boolean IS_RUNNING=true;
    private Thread thread;
    private boolean isRunning = false;
    PlayGame playGame;

    private BufferedImage bufferedImage;
    private Graphics2D buffg2d;

    public Manager manager;


    public Game() throws IOException {
        manager = new Manager();
        playGame = new PlayGame(manager);
        bufferedImage = new BufferedImage(Main.SCREEN_WIDTH,Main.SCREEN_WIDTH,BufferedImage.TYPE_INT_ARGB);
    }
    public void paintComponent(Graphics g){
        g.drawImage(bufferedImage , 0 , 0,this);

    }
    public void renderGame(){
        if (bufferedImage == null){
            bufferedImage = new BufferedImage(Main.SCREEN_WIDTH,Main.SCREEN_WIDTH,BufferedImage.TYPE_INT_ARGB);
        }
        if (bufferedImage != null){
            buffg2d = (Graphics2D) bufferedImage.getGraphics();

        }
        if (buffg2d != null){
            buffg2d.setColor(Color.BLACK);
            buffg2d.fillRect(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
            manager.render(buffg2d);
        }

    }

    public void updateGame(){
        manager.update();
    }

    public void startGame(){
        if (thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run() {
        long FPS = 60;
        long period = 1000*1000000/FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();
        while (isRunning){
            updateGame();
            if (manager.bomber.getState() == PartcularGame.ALIVE){
                renderGame();
                repaint();
            }

            long timeDelta = System.nanoTime() - beginTime;
            sleepTime = period - timeDelta;
            try {
                if (sleepTime > 0){
                    thread.sleep(sleepTime/1000000);
                }
                thread.sleep(period/2000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            beginTime = System.nanoTime();

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        playGame.processKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playGame.processReleasedKey(e.getKeyCode());
    }
}

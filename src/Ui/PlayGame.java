package Ui;

import Modul.Bomber;
import Modul.Boss;
import Modul.Manager;

import java.awt.event.KeyEvent;

public class PlayGame {
    private Manager manager;
    public PlayGame(Manager manager){
         this.manager = manager;
    }
    private Main main;

    public void processKey(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                manager.bomber.setDirection(manager.bomber.UP_DIR);
                manager.bomber.move();
                manager.bomber.setSpeedX(0);
                break;
            case KeyEvent.VK_DOWN:
                manager.bomber.setDirection(manager.bomber.DOWN_DIR);
                manager.bomber.move();
                manager.bomber.setSpeedX(0);
                break;
            case KeyEvent.VK_LEFT:
                manager.bomber.setDirection(manager.bomber.LEFT_DIR);
                manager.bomber.move();
                manager.bomber.setSpeedY(0);
                break;
            case KeyEvent.VK_RIGHT:
                manager.bomber.setDirection(manager.bomber.RIGHT_DIR);
                manager.bomber.move();
                manager.bomber.setSpeedY(0);
                break;
            case KeyEvent.VK_SPACE:
                try {
                    manager.putBomb();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_A:
                break;

        }
    }

    public void processReleasedKey(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                manager.bomber.setDirection(Bomber.UP_DIR);
                manager.bomber.setSpeedY(0);
                manager.bomber.setSpeedX(0);
                break;
            case KeyEvent.VK_DOWN:
                manager.bomber.setDirection(Bomber.DOWN_DIR);
                manager.bomber.setSpeedY(0);
                manager.bomber.setSpeedX(0);

                break;
            case KeyEvent.VK_LEFT:
                manager.bomber.setDirection(Bomber.LEFT_DIR);
                manager.bomber.setSpeedX(0);
                manager.bomber.setSpeedY(0);

                break;
            case KeyEvent.VK_RIGHT:
                manager.bomber.setDirection(Bomber.RIGHT_DIR);
                manager.bomber.setSpeedX(0);
                manager.bomber.setSpeedY(0);

                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }
}

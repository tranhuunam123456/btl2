package Ui;

import Animation.DataLoader;
import Modul.PartcularGame;
//import sound.GameSound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 622;

    public static Game game;
    private Container mContainer;

    public Main(){
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();

        this.setBounds((dimension.width-SCREEN_WIDTH)/ 2 , (dimension.height - SCREEN_HEIGHT)/2 , SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            DataLoader.getInstance().LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {
            try {
                game = new Game();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //GameSound.getIstance().getAudio(GameSound.PLAYGAME).loop();
        add(game);
        this.addKeyListener(game);
        if (game.manager.bomber.getState() == PartcularGame.DEAD ){
            this.removeKeyListener(game);
        }
    }

    public void startGame(){
        game.startGame();
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

    public static void main(String[] args) {
        Main gameFrame = new Main();
        gameFrame.setVisible(true);
        gameFrame.startGame();
        playSound("src/sound/sound.wav");

    }

}

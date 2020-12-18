package Modul;

import Ui.Main;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private Thread thread;

    public Bomber bomber ;
    public PhysicMap physicMap;
    public Boss boss1,boss2,boss3,boss4,boss5,boss6;
    public ArrayList<Boss> boss = new ArrayList<>();
    public ArrayList<Bomb> bomb = new ArrayList<>();
    public ArrayList<BomBang> bombang = new ArrayList<>();
    long preTime;
    private Random random = new Random();


    public Manager() {
        bomber = new Bomber(65,65,this);
        boss1 = new Boss(387,158,this,PartcularGame.ENEMY);
        boss2 = new Boss(380,520,this,PartcularGame.MONSTERA);
        boss3 = new Boss(65,500,this,PartcularGame.MONSTERA);
        boss4 = new Boss(333,380,this,PartcularGame.ENEMY);

        boss5 = new Boss(700,65,this,PartcularGame.MONSTER);
        boss6 = new Boss(700,515,this,PartcularGame.MONSTER);
        boss.add(boss1);
        boss.add(boss2);
        boss.add(boss3);
        boss.add(boss4);
        boss.add(boss5);
        boss.add(boss6);
        physicMap = new PhysicMap(0,0,this);
    }

    public void update(){
        for (int i = 0;i<boss.size();i++){
            boss.get(i).update();
        }
        bomber.update();

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

    public void render(Graphics2D g2d){
        physicMap.draw(g2d);
        physicMap.drawMenu(g2d);

        if (bomb.size() > 0){
            bomb.get(0).draw(g2d);

            for (int i = 0;i < boss.size();i++){
                if (boss.get(i).isImpactBossVsBomb(bomb.get(0))){

                    if (boss.get(i).getDirboss() == 1){
                        boss.get(i).setDirboss(2);
                    }else {
                        if (boss.get(i).getDirboss() == 2){
                            boss.get(i).setDirboss(1);
                        }
                    }
                    if (boss.get(i).getDirboss() == 3){
                        boss.get(i).setDirboss(4);
                    }else {
                        if (boss.get(i).getDirboss() == 4){
                            boss.get(i).setDirboss(3);
                        }
                    }
                }
            }
            long time = System.currentTimeMillis() - bomb.get(0).getTimeLine();
            if (time > 2000){
                bombang.get(0).draw(g2d);

                for (int i = 0 ;i < boss.size();i++){
                    if (boss.get(i).isImpactBossVsBombang(bombang.get(0))){
                        boss.remove(i);
                       // GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
                    }
                }
            }

            if (time > 2500){
                if (bomber.isImpactBomberVsBombang(bombang.get(0))){
                    //GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
                    playSound("src/sound/megamanhurt.wav");
                    bomber.setHeart(bomber.getHeart() - 1);



                    if (bomber.getHeart() == 0){
                        bomber.setState(PartcularGame.DEAD);
                    }
                    else if (bomber.getHeart() > 0){
                        bomber.setPosX(65);
                        bomber.setPosY(65);
                        bomber.setDirection(PartcularGame.DOWN_DIR);
                    }
                }
                bomb.remove(0);
                bombang.remove(0);
            }
        }
        bomber.draw(g2d);

        for (int i = 0;i<boss.size();i++){
            boss.get(i).draw(g2d);
        }
        for (int y = 0 ; y < physicMap.phys_map.length ; y++){
            for (int x = 0;x < physicMap.phys_map[0].length;x++){
                if (bomber.isImpactBomberVsItem() == 1){
                    if (bomber.getHeart() < 3){
                        bomber.setHeart(bomber.getHeart() + 1);
                    }
                    if (physicMap.phys_map[y][x] == 4){
                        physicMap.phys_map[y][x] = 0;
                    }

                }
                if (bomber.isImpactBomberVsItem() == 2){
                   bomber.setSpeed(5);
                   long timeContact = System.currentTimeMillis();
                    if (physicMap.phys_map[y][x] == 6){
                        physicMap.phys_map[y][x] = 0;
                    }
                }
            }
        }
        if (bomber.getHeart() == 0){
            drawDialog(g2d, bomber.getType());
        }
        if (boss.size() == 0){
            drawDialog(g2d, 3);
        }
        if (bomber.getState() == PartcularGame.DEAD){
            //GameSound.getIstance().getAudio(GameSound.PLAYGAME).stop();
        }
    }
    public void drawDialog(Graphics2D g2d, int type) {
        g2d.setFont(new Font("Arial", Font.BOLD, 70));
        g2d.setColor(Color.RED);
        if(type==1){
            g2d.drawString("You Lose !!!", 350, 250);

        }else{
            if(type==2){
                g2d.drawString("Round ", 350, 250);
            }else{
                g2d.drawString("You Win !!!", 350, 250);
            }
        }
    }
    public void putBomb() throws InterruptedException {
            preTime = System.currentTimeMillis();
            Bomb newBomb = new Bomb((int)bomber.getPosX(),(int)bomber.getPosY(),preTime, this);
            BomBang newbombang = new BomBang((int)bomber.getPosX(),(int)bomber.getPosY() ,preTime, this);

            bomb.add(newBomb);
            bombang.add(newbombang);


            playSound("src/sound/111.wav");
    }

}

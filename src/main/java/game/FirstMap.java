package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class FirstMap implements game.Map{

    Image img = new Image(getClass().getResourceAsStream("res/mapBig.png"), 1000, 1000, false, false);
    double maxWidth;
    double maxHeight;
    int hboxsize = 60;
    int waves = 3;
    int currentWave = 0;

    public FirstMap(double width, double heigth) {
        this.maxWidth = width;
        this.maxHeight = heigth; 
    }

    public Image getMap() {
        return img;
    }

    public double getStartX() {
        return 200;
    }

    @Override
    public double getStartY() {
        return maxHeight - 200;
    }

    public boolean checkLegalPositionUp(double x, double y) {
        if (x <= maxWidth/2 && y > ((24960-9*x)/64) - hboxsize){
            // System.out.println("1 True!!");
            return true;
        }
        if (x >= maxWidth/2 && x < maxWidth/2+50 && y > (-3*x+2220)-hboxsize){
            // System.out.println("2 True!!");
            return true;
        }
        if (x >= maxWidth/2+50 && x < maxWidth && y > ((81450+10*x)/589) - hboxsize){
            // System.out.println("3 True!!");
            return true;
        }


        return false;
        
    }

    @Override
    public boolean checkLegalPositionLeft(double x, double y) {
        if (x <= maxWidth/2 && x > 0 && y > ((24960-9*x)/64) - hboxsize){
            // System.out.println("21 True!!");
            return true;
        }
        if (x >= maxWidth/2 && x < maxWidth/2+50 && y > (-3*x+2220)-hboxsize){
            // System.out.println("22 True!!");
            return true;
        }
        if (x >= maxWidth/2+50){
            // System.out.println("23 True!!");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLegalPositionRight(double x, double y) {
        if (x <= maxWidth/2+50){
            // System.out.println("31 True!!");
            return true;
        }
        if (x > maxWidth/2+50 && x < maxWidth-100 && y > ((81450+10*x)/589) - hboxsize){
            // System.out.println("32 True!!");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLegalPositionDown(double x, double y) {
        if (y < maxHeight-110){
            // System.out.println("41 True!!");
            return true;
        }
        return false;
    }

    public int getNoOfWaves() {
        return waves;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int i) {
        this.currentWave = i;
    }

    public List<Enemy> nextWave() {
        if (currentWave < waves){
            setCurrentWave(getCurrentWave()+1);
            List<Enemy> list = new ArrayList<>();
            if (currentWave == 1){
                list = wave1();
            }
            if (currentWave == 2){
                list = wave2();
            }
            if (currentWave == 3){
                list = wave3();
            }
            return list;
        }
        return null;
    }

    public List<Enemy> wave1() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Boar boarNode = new Boar(70.0, 500.0 + i*90, 400.0);
            // anchorPane2.getChildren().add(boarNode.getRectangle());
            // anchorPane2.getChildren().add(boarNode.getRectangleOutline());
            // anchorPane2.getChildren().add(boarNode.getImageView());
            enemies.add(boarNode);
        }
        return enemies;
    }

    public List<Enemy> wave2() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Boar boarNode = new Boar(70.0, 500.0 + i*90, 400.0);
            enemies.add(boarNode);
        }
        return enemies;
    }

    public List<Enemy> wave3() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Boar boarNode = new Boar(70.0, 500.0 + i*90, 400.0);
            enemies.add(boarNode);
        }
        return enemies;
    }
    
}

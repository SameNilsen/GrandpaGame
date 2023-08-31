package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Salmon implements Fisk{

    private int size = 64;
    private Image gjeddeImg = new Image(getClass().getResourceAsStream("res/Salmon.png"), size, size, false, false);
    private ImageView imageview = new ImageView();
    private int dir;
    private double timer = 0;
    private double startX;
    private double startY;
    private double speed = 60;
    private double timeLimit = 11;

    public Salmon(double x, double y, int dir) {
        imageview.setImage(gjeddeImg);
        setX(x);
        setY(y);
        this.dir = dir;
    }


    public double getX() {
        return imageview.getX();
    }
    
    public void setX(double x) {
        imageview.setX(x);
    }

    public double getY() {
        return imageview.getY();
    }
    
    public void setY(double y) {
        imageview.setY(y);
    }

    public ImageView getImageView() {
        return imageview;
    }

    @Override
    public int getDir() {
        return dir;
    }

    @Override
    public double getTimer() {
        return timer;
    }


    @Override
    public void setTimer(double t) {
        this.timer = t;
    }


    @Override
    public double getStartX() {
        return startX;
    }


    @Override
    public void setStartX(double x) {
        this.startX = x;
    }


    @Override
    public double getStartY() {
        return startY;
    }


    @Override
    public void setStartY(double y) {
        this.startY = y;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public double getTimeLimit() {
        return this.timeLimit;
    }
}

package game;

import javafx.scene.image.ImageView;

public interface Fisk {
    
    public double getX();

    public void setX(double x);

    public double getY();
    
    public void setY(double y);

    public ImageView getImageView();

    public int getDir();

    public double getTimer();

    public void setTimer(double t);

    public double getStartX();

    public void setStartX(double x);

    public double getStartY();

    public void setStartY(double y);

    public double getSpeed();

    public double getTimeLimit();
}

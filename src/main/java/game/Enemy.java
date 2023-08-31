package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public interface Enemy {
    
    public double getHealth();

    public void setHealth(double health);

    public void changeHealth(double change);

    public ImageView getImageView();

    public void setX(double x);

    public void setY(double y);

    public double getX();

    public double getY();

    public Rectangle getRectangle();
    
    public Rectangle getRectangleOutline();

    public double getDestX();

    public double getDestY();

    public void setDestX(double x);

    public void setDestY(double y);

    public boolean getRedirect();

    public void setRedirect(boolean bool);

    public int getRedirectTimer();

    public void setRedirectTimer(int tid);

    public void incrementRedirectTimer(int tid);

    public Image[] getRunninImages();
    
    public Image[] getAttackImages();

    public double getSpeed();
}

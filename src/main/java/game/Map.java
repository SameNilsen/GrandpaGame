package game;

import java.util.List;

import javafx.scene.image.Image;

public interface Map {

    public Image getMap();

    public double getStartX();

    public double getStartY();

    public boolean checkLegalPositionUp(double x, double y);

    public boolean checkLegalPositionLeft(double x, double y);

    public boolean checkLegalPositionRight(double x, double y);

    public boolean checkLegalPositionDown(double x, double y);

    public List<Enemy> nextWave();

    public int getNoOfWaves();

    public int getCurrentWave();

    public void setCurrentWave(int i);
    
}

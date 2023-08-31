package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class FirstPlatformMap implements game.PlatformMap{

    Image img = new Image(getClass().getResourceAsStream("res/FirstPlatformMap.png"), 2628, 657, false, false);
    double maxWidth;
    double maxHeight;
    int hboxsize = 60;
    List<PlatformObject> platformsList = new ArrayList<>();

    public FirstPlatformMap(double width, double heigth) {
        this.maxWidth = width;
        this.maxHeight = heigth;
        createPlatformlist(); 
    }

    public Image getMap() {
        return img;
    }

    private void createPlatformlist() {
        platformsList.add(new PlatformObject(0, 373, 450, 373));
        platformsList.add(new PlatformObject(482, 428, 615, 428));
        platformsList.add(new PlatformObject(636, 493, 831, 493));
        platformsList.add(new PlatformObject(841, 455, 902, 455));
        platformsList.add(new PlatformObject(914, 521, 1158, 521));
        platformsList.add(new PlatformObject(944, 390, 1025, 390));
        platformsList.add(new PlatformObject(1057, 325, 1181, 325));
        platformsList.add(new PlatformObject(1283, 158, 1405, 158));
        platformsList.add(new PlatformObject(1273, 233, 1376, 233));
        platformsList.add(new PlatformObject(1263, 296, 1344, 296));
        platformsList.add(new PlatformObject(1222, 382, 1343, 382));
        platformsList.add(new PlatformObject(1509, 362, 1692, 362));
        platformsList.add(new PlatformObject(1653, 484, 2043, 484));
        platformsList.add(new PlatformObject(1867, 373, 2073, 373));
        platformsList.add(new PlatformObject(2033, 260, 2298, 260));
        platformsList.add(new PlatformObject(2257, 475, 2626, 475));

    }

    public List<PlatformObject> getPlatformList() {
        return platformsList;
    }

    @Override
    public double getStartX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStartX'");
    }

    @Override
    public double getStartY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStartY'");
    }

    @Override
    public boolean checkLegalPositionUp(double x, double y) {
        return true;
    }

    @Override
    public boolean checkLegalPositionLeft(double x, double y) {
        return true;
    }

    @Override
    public boolean checkLegalPositionRight(double x, double y) {
        return true;
    }

    @Override
    public boolean checkLegalPositionDown(double x, double y) {
        return true;
    }

}
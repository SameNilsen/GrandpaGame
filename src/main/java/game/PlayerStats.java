package game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PlayerStats {

    Integer ducks;
    Image img = new Image(getClass().getResourceAsStream("res/mapBig.png"), 1000, 1000, false, false);
    Image img2 = new Image(getClass().getResourceAsStream("res/townMap.png"), 1000, 1000, false, false);
    int mapNumber = 0;
    List<Image> maps = new ArrayList<>();
    List<Map> mapList = new ArrayList<>();
    List<PlatformMap> platformMapList = new ArrayList<>();
    String[] mapType = new String[4];
    Stage stage;
    GameController firstController;
    MapController secondController;
    PlatformController thirdController;

    public PlayerStats(int ducks) {
        this.ducks = ducks;
        maps.add(img);
        maps.add(img2);
        mapList.add(new FirstMap(1279, 657));
        platformMapList.add(null);
        mapType[0] = "topdown";

        mapList.add(new TownMap(1279, 657));
        platformMapList.add(null);
        mapType[1] = "topdown";

        mapList.add(null);
        platformMapList.add(new FirstPlatformMap(2628, 657));
        mapType[2] = "platform";

        mapList.add(new bryggeMap(1279, 657));
        platformMapList.add(null);
        mapType[3] = "topdown";
    }

    public Integer getDucks() {
        return ducks;
    }

    public void setDucks(int i) {
        ducks = i;
    }

    public void increaseDucks(int i) {
        ducks += i;
    }

    public void setMapNumber(int i) {
        this.mapNumber = i;
    }

    public int getMapNumber() {
        return this.mapNumber;
    }

    public Map getMap(int number) {
        return mapList.get(number);
    }

    public PlatformMap getMapPlatform(int number) {
        return platformMapList.get(number);
    }

    public String getMapType() {
        System.out.println("NUMBER: " + getMapNumber());
        return mapType[getMapNumber()];
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setGameController(GameController controller) {
        this.firstController = controller;
    }

    public GameController getGameController() {
        return firstController;
    }

    public void setMapController(MapController controller) {
        this.secondController = controller;
    }

    public MapController GetMapController() {
        return secondController;
    }

    public void setPlatformController(PlatformController controller) {
        this.thirdController = controller;
    }

    public  PlatformController GetPlatformController() {
        return thirdController;
    }
    
}

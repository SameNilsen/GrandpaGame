package game;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class MapController implements Initializable{

    @FXML
    AnchorPane anchorPane2;

    @FXML
    HBox hbox;

    @FXML
    Button b1;

    @FXML
    Label mousePos;

    @FXML
    BorderPane outerRegion;

    Map map;

    Button pauseButton = new Button("Pause");

    int hboxsize = 60;

    private ImageView grampa = new ImageView();
    private int imageSizeGrampa = 64;
    private Image grampaFront1 = new Image(getClass().getResourceAsStream("res/grampaFront/GrampaFront12.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaFront2 = new Image(getClass().getResourceAsStream("res/grampaFront/GrampaFront22.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaFront3 = new Image(getClass().getResourceAsStream("res/grampaFront/GrampaFront32.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    Image grampaFrontList[] = {grampaFront1, grampaFront2, grampaFront3};
    private Image grampaLeft1 = new Image(getClass().getResourceAsStream("res/grampaLeft/GrampaLeft1.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaLeft2 = new Image(getClass().getResourceAsStream("res/grampaLeft/GrampaLeft2.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaLeft3 = new Image(getClass().getResourceAsStream("res/grampaLeft/GrampaLeft3.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    Image grampaLeftList[] = {grampaLeft1, grampaLeft2, grampaLeft1, grampaLeft3};
    private Image grampaRight1 = new Image(getClass().getResourceAsStream("res/grampaRight/GrampaRight1.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaRight2 = new Image(getClass().getResourceAsStream("res/grampaRight/GrampaRight2.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaRight3 = new Image(getClass().getResourceAsStream("res/grampaRight/GrampaRight3.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    Image grampaRightList[] = {grampaRight1, grampaRight2, grampaRight1, grampaRight3};
    private Image grampaBack1 = new Image(getClass().getResourceAsStream("res/grampaBack/GrampaBack1.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaBack2 = new Image(getClass().getResourceAsStream("res/grampaBack/GrampaBack2.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    private Image grampaBack3 = new Image(getClass().getResourceAsStream("res/grampaBack/GrampaBack3.png"), imageSizeGrampa, imageSizeGrampa, false, false);
    Image grampaBackList[] = {grampaBack1, grampaBack2, grampaBack1, grampaBack3};

    double grampaHitboxX = 0.0;
    double grampaHitboxY = 0.0;

    Rectangle healthBarGrampa = new Rectangle(70, 10, Color.RED);
    Rectangle healthBarGrampaOutline = new Rectangle(71, 11);
    Rectangle fishSelectorOutline = new Rectangle(121, 31);
    List<Image> fishInventory = new ArrayList<>();
    List<Class<? extends Fisk>> fishInventoryClasses = new ArrayList<>();
    Integer fishInventorySelectedNo = 0;
    Integer fishSelected = 0;
    boolean fishFlying = false;
    HashMap<Class<? extends Fisk>, Image> possibleFishesMap = new HashMap<Class<? extends Fisk>, Image>(); 
    HashMap<Image, Class<? extends Fisk>> reversePossibleFishesMap = new HashMap<Image, Class<? extends Fisk>>();
    HashMap<Image, Integer> fishAndNumberMap = new HashMap<Image, Integer>();
    Label fishSelectedCount = new Label();

    List<Image> obtainedFishes = new ArrayList<>();

    PlayerStats stats;
    boolean isPause = false;
    VBox vbox = new VBox();
    AnchorPane nextWaveRegion = new AnchorPane();
    Label nextWaveLabel = new Label();
    int nextWaveAnimationTimer = 0;
    int nextWaveAnimationTid = 10;

    String dir = "down";

    private ImageView boar = new ImageView();
    private Image boarLeft1 = new Image(getClass().getResourceAsStream("res/Boar.png"), 96, 96, false, false);
    List<Enemy> enemies = new ArrayList<Enemy>();
    List<Double> boarsHealth = new ArrayList<Double>();

    
    private Image gjeddeImg = new Image(getClass().getResourceAsStream("res/Gjedde.png"), 96, 96, false, false);
    private int iconSize = 40;
    private Image gjeddeImgIcon = new Image(getClass().getResourceAsStream("res/Gjedde.png"), iconSize, iconSize, false, false);
    private Image clownFishImgIcon = new Image(getClass().getResourceAsStream("res/ClownFish.png"), iconSize, iconSize, false, false);
    private Image salmonImgIcon = new Image(getClass().getResourceAsStream("res/Salmon.png"), iconSize, iconSize, false, false);
    private Image fatFishIcon = new Image(getClass().getResourceAsStream("res/FatFish.png"), iconSize, iconSize, false, false);
    private Image blackFillerIcon = new Image(getClass().getResourceAsStream("res/blackFillerFish.png"), iconSize, iconSize, false, false);
    private ImageView fishInventory1 = new ImageView(), fishInventory2 = new ImageView(), fishInventory3 = new ImageView();
    double fiskStartY;
    private Fisk currentFish;
    long starttime = 0;

    private Image goldDuckImage = new Image(getClass().getResourceAsStream("res/GoldDuck.png"), 60, 60, false, false);
    private ImageView goldDuckIcon = new ImageView();
    private int ducksOnThisMap = 0;
    Label goldDucksLabel = new Label();
    Label waveLabel = new Label("Wave: 0");
    int waveNumber = 0;

    Scale scale = new Scale();
    boolean zoomAndScale = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Dette er en ny scene!?");
        // Stage stageTheLabelBelongs = (Stage) anchorPane2.getScene().getWindow();
        // stageTheLabelBelongs.setMaximized(true);
        
        // Image img = new Image(getClass().getResourceAsStream("res/mapBig.png"), 1000, 1000, false, false);
        // img = stats.getMap(stats.getMapNumber());
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
        // // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        // Background bGround = new Background(bImg);
        // anchorPane2.setBackground(bGround);

        grampa.setImage(grampaFront1);
        anchorPane2.getChildren().add(grampa);
        grampa.setPreserveRatio(false);
        grampa.setSmooth(false);
        // grampa.setFitHeight(100);
        grampa.setX(anchorPane2.getWidth()/2);
        grampa.setY(anchorPane2.getHeight()/2);
        anchorPane2.getChildren().add(healthBarGrampa);
        anchorPane2.getChildren().add(healthBarGrampaOutline);
        healthBarGrampaOutline.setStyle("-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 1;");
        anchorPane2.getChildren().add(fishSelectorOutline);
        fishSelectorOutline.setStyle("-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 1;");
        alwaysOnTimer.start();

        anchorPane2.getTransforms().add(scale);

        // fishInventory.add(gjeddeImgIcon);
        // fishInventory.add(clownFishImgIcon);
        // fishInventory.add(salmonImgIcon);
        fishInventory.add(blackFillerIcon);
        fishInventory.add(blackFillerIcon);
        fishInventory.add(blackFillerIcon);
        obtainedFishes.add(blackFillerIcon);
        obtainedFishes.add(blackFillerIcon);
        obtainedFishes.add(blackFillerIcon);
        fishInventoryClasses.add(Gjedde.class);
        fishInventoryClasses.add(ClownFish.class);
        fishInventoryClasses.add(Salmon.class);
        // fishInventoryClasses.get(0).getCon
        // fishInventory1.setImage(gjeddeImgIcon);
        // fishInventory2.setImage(clownFishImgIcon);
        // fishInventory3.setImage(salmonImgIcon);
        anchorPane2.getChildren().add(fishInventory1);
        anchorPane2.getChildren().add(fishInventory2);
        anchorPane2.getChildren().add(fishInventory3);
        fishInventoryTimer.start();
        // fishInventoryTimer.start();

        possibleFishesMap.put(Gjedde.class, gjeddeImgIcon);
        possibleFishesMap.put(ClownFish.class, clownFishImgIcon);
        possibleFishesMap.put(Salmon.class, salmonImgIcon);
        possibleFishesMap.put(FatFish.class, fatFishIcon);
        reversePossibleFishesMap.put(gjeddeImgIcon, Gjedde.class);
        reversePossibleFishesMap.put(clownFishImgIcon, ClownFish.class);
        reversePossibleFishesMap.put(salmonImgIcon, Salmon.class);
        reversePossibleFishesMap.put(fatFishIcon, FatFish.class);
        fishAndNumberMap.put(gjeddeImgIcon, 0);
        fishAndNumberMap.put(clownFishImgIcon, 0);
        fishAndNumberMap.put(salmonImgIcon, 0);
        fishAndNumberMap.put(fatFishIcon, 0);
        fishSelectedCount.setTextFill(Color.ORANGE);
        fishSelectedCount.setFont(new Font("Comic Sans MS", 24));
        anchorPane2.getChildren().add(fishSelectedCount);
        // rotateFishSelector();
        rotateFishSelector2(1);

        createMenu();
        vbox.setVisible(false);
        nextWaveRegion.setVisible(false);
        anchorPane2.getChildren().add(nextWaveRegion);
        AnchorPane.setTopAnchor(nextWaveRegion, 100.0);
        AnchorPane.setLeftAnchor(nextWaveRegion, 500.0);
        nextWaveLabel.setTextFill(Color.ORANGE);
        nextWaveLabel.setFont(new Font("Comic Sans MS", 24));
        nextWaveRegion.getChildren().add(nextWaveLabel);

        // Button pauseButton = new Button("Pause");
        styleButton(pauseButton, hbox);
        // styleButton(b1, hbox);
        b1.setVisible(false);
        pauseButton.setOnAction(e -> {
            if (isPause == false){
                System.out.println("PAUSE");
                boarTimer.stop();
                isPause = true;
                vbox.setVisible(true);
            }
            else{
                System.out.println("START");
                boarTimer.start();
                isPause = false;
                vbox.setVisible(false);
            }
        });
        Region filleRegion0 = new Region();
        hbox.getChildren().add(filleRegion0);
        HBox.setHgrow(filleRegion0, Priority.ALWAYS);
        waveLabel.setTextFill(Color.ORANGE);
        waveLabel.setFont(new Font("Comic Sans MS", 24));
        hbox.getChildren().add(waveLabel);
        Region filleRegion = new Region();
        hbox.getChildren().add(filleRegion);
        HBox.setHgrow(filleRegion, Priority.ALWAYS);
        goldDuckIcon.setImage(goldDuckImage);
        hbox.getChildren().add(goldDuckIcon);
        goldDucksLabel.setTextFill(Color.ORANGE);
        goldDucksLabel.setFont(new Font("Comic Sans MS", 24));
        hbox.getChildren().add(goldDucksLabel);
        Region filleRegion2 = new Region();
        hbox.getChildren().add(filleRegion2);
        HBox.setHgrow(filleRegion2, Priority.ALWAYS);
        hbox.getChildren().add(pauseButton);
        Image hboxImg = new Image(getClass().getResourceAsStream("res/TopBgr9.png"), 1000, 1000, false, false);
        // hboxImg = stats.getMap(stats.getMapNumber());
        BackgroundImage bImg = new BackgroundImage(hboxImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        Background hboxbGround = new Background(bImg);
        hbox.setBackground(hboxbGround);

        // rotateFishSelector();

        boar.setImage(boarLeft1);
        anchorPane2.getChildren().add(boar);
    }

    public MapController() {  //  Egentlig ikke i bruk
        System.out.println("Contructor1");
    }

    public MapController(int i) {   //  Ikke i bruk
        System.out.println("Contructor " + i);
    }

    public void initData(PlayerStats data) {
        this.stats = data;
        System.out.println("Data: " + data);
        System.out.println(stats.getDucks());
        map = stats.getMap(stats.getMapNumber());
        System.out.println("MAP: " + map);
        // Stage stageTheLabelBelongs = (Stage) anchorPane2.getScene().getWindow();
        Stage stageTheLabelBelongs = stats.getStage();
        stageTheLabelBelongs.setMaximized(false);
        stageTheLabelBelongs.setMaximized(true);
        boar.setX(1157);
        boar.setY(90);
        // Image img = new Image(getClass().getResourceAsStream("res/mapBig.png"), 1000, 1000, false, false);
        Image img = map.getMap();
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        Background bGround = new Background(bImg);
        anchorPane2.setBackground(bGround);

        grampa.setX(map.getStartX());
        grampa.setY(map.getStartY());

        // grampa.setFitHeight(32);
        // grampa.setFitWidth(32);
    }

    public void createMenu() {
        // VBox vbox = new VBox();
        anchorPane2.getChildren().add(vbox);
        AnchorPane.setTopAnchor(vbox, 50.0);
        AnchorPane.setLeftAnchor(vbox, 500.0);
        Button resumeBtn = new Button("Resume");
        Button restartBtn = new Button("Restart");
        Button returnToMenuBtn = new Button("Return to menu");
        Button[] menuBtnList = {resumeBtn, restartBtn, returnToMenuBtn};
        for (Button button : menuBtnList) {
            button.setPrefWidth(150);
            styleButton(button, vbox);
            vbox.getChildren().add(button);
        }
        resumeBtn.setOnAction(e -> {
            pauseButton.fire();
        });
        restartBtn.setOnAction(e -> {
            stats.setDucks(0);
            restartMap();
            // stats.setDucks(0);
            // healthBarGrampa.setWidth(70);
            // for (Enemy enemy : enemies) {
            //     anchorPane2.getChildren().remove(enemy.getImageView());
            //     anchorPane2.getChildren().remove(enemy.getRectangle());
            //     anchorPane2.getChildren().remove(enemy.getRectangleOutline());
            // }
            // enemies.clear();
            // waveNumber = 0;
            // map.setCurrentWave(0);
            // nextWaveAnimationTid = 0;
            // nextWaveAnimationTimer = 10;
            // resetFishInventory();
        });
        returnToMenuBtn.setOnAction(e -> {
            restartBtn.fire();
            returnToMenu2();
            // Stage stageTheLabelBelongs = (Stage) returnToMenuBtn.getScene().getWindow();
            // Stage stag = stats.getStage();
            // FXMLLoader loader = new FXMLLoader();
            // loader.setController(stats.getGameController());  //  Kan eventuelt også gjøre det på gamlemåten og kalle initData med stats som argument. Da 
            // //  blir det en ny instans av GameController, men vet ikke hva som er for og imot enda.
            // loader.setLocation(getClass().getResource("/grandpaApp.fxml"));
            // // loader = new FXMLLoader(getClass().getResource("/grandpaApp.fxml"));
            // // loader.setController(stats.getGameController());
            // // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
            // try {
            //     // stageTheLabelBelongs.setMaximized(false);
            //     // stageTheLabelBelongs.setMaximized(true);
            //     // stageTheLabelBelongs.setScene(new Scene(loader.load()));
            //     stag.setScene(new Scene(loader.load()));
            //     stag.setMaximized(false);
            //     stag.setMaximized(true);
            // } catch (IOException e1) {
            //     // TODO Auto-generated catch block
            //     e1.printStackTrace();
            // }
            // MapController controller = loader.getController();
            // controller.initData(stats);
        });
    }

    public void styleButton(Button button, Region region) {
        button.setStyle(
            "-fx-background-color:"+ 
        "#000000,"+
        "linear-gradient(#7ebcea, #2f4b8f),"+
        "linear-gradient(#426ab7, #263e75),"+
        "linear-gradient(#395cab, #223768);"+
        "-fx-background-insets: 0,1,2,3;"+
        "-fx-background-radius: 3,2,2,2;"+
        "-fx-padding: 12 30 12 30;"+
        "-fx-text-fill: white;"+
        "-fx-font-size: 12px;");
    }

    public void stopAllTimers() {
        alwaysOnTimer.stop();
        fishInventoryTimer.stop();
        nextwaveTimer.stop();
        grampaImageAnimation.stop();
        boarTimer.stop();
        throwFishTimer.stop();
    }

    public void returnToMenu() {
        //  I denne metoden blir controller til den første scene/fxml angitt manuelt. Også i MainApp startklassen.
        System.out.println("Returning to menu");
        Stage stageTheLabelBelongs = (Stage) anchorPane2.getScene().getWindow();
        Stage stag = stats.getStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(stats.getGameController());  //  Kan eventuelt også gjøre det på gamlemåten og kalle initData med stats som argument. Da 
        //  blir det en ny instans av GameController, men vet ikke hva som er for og imot enda.
        loader.setLocation(getClass().getResource("/grandpaApp.fxml"));
        // loader = new FXMLLoader(getClass().getResource("/grandpaApp.fxml"));
        // loader.setController(stats.getGameController());
        // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
        try {
            // stageTheLabelBelongs.setMaximized(false);
            // stageTheLabelBelongs.setMaximized(true);
            // stageTheLabelBelongs.setScene(new Scene(loader.load()));
            stag.setScene(new Scene(loader.load()));
            stag.setMaximized(false);
            stag.setMaximized(true);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void returnToMenu2() {
        //  I denne metoden blir controller til den første scene/fxml angitt manuelt. Også i MainApp startklassen.
        System.out.println("Returning to menu");
        Stage stageTheLabelBelongs = (Stage) anchorPane2.getScene().getWindow();
        Stage stag = stats.getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/grandpaApp.fxml"));
        // loader.setController(stats.getGameController());
        // loader.setLocation(getClass().getResource("/grandpaApp.fxml"));
        try {
            stag.setScene(new Scene(loader.load()));
            GameController controller = loader.getController();
            controller.initData(stats);
            stag.setMaximized(false);  //  Maximized må komme etter scenen er loadet.
            stag.setMaximized(true);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void restartMap() {
        // stats.setDucks(0);
        healthBarGrampa.setWidth(70);
        for (Enemy enemy : enemies) {
            anchorPane2.getChildren().remove(enemy.getImageView());
            anchorPane2.getChildren().remove(enemy.getRectangle());
            anchorPane2.getChildren().remove(enemy.getRectangleOutline());
        }
        enemies.clear();
        waveNumber = 0;
        map.setCurrentWave(0);
        nextWaveAnimationTid = 0;
        nextWaveAnimationTimer = 10;
        resetFishInventory();
    }

    AnimationTimer alwaysOnTimer = new AnimationTimer()
    {   
        public void handle(long currentNanoTime)
        {   
            // System.out.println(grampa.getImage().getWidth());
            healthBarGrampa.setX(grampa.getX() + ((grampa.getImage().getWidth()-70)/2));
            healthBarGrampa.setY(grampa.getY() - 10);
            healthBarGrampaOutline.setX(grampa.getX() + ((grampa.getImage().getWidth()-70)/2) - 1);
            healthBarGrampaOutline.setY(grampa.getY() - 11);
            goldDucksLabel.setText(String.valueOf(ducksOnThisMap));
            waveLabel.setText("Wave: " + Integer.toString(map.getCurrentWave()));
            grampaHitboxX = grampa.getX() + grampa.getImage().getWidth()/2;
            grampaHitboxY = grampa.getY() + grampa.getImage().getHeight()/2;
            scale.setPivotX(grampaHitboxX);
            scale.setPivotY(grampaHitboxY);
            // anchorPane2.toBack();
        }  
    };

    AnimationTimer fishInventoryTimer = new AnimationTimer()
    {   
        public void handle(long currentNanoTime)
        {   
            fishSelectorOutline.setX(grampa.getX() + ((grampa.getImage().getWidth()-120)/2) - 1);
            fishSelectorOutline.setY(grampa.getY() - 50);
            fishInventory1.setX(grampa.getX() + ((grampa.getImage().getWidth()-120)/2));
            fishInventory1.setY(grampa.getY() - 50);
            fishInventory2.setX(grampa.getX() + ((grampa.getImage().getWidth()-120)/2) + iconSize);
            fishInventory2.setY(grampa.getY() - 50);
            fishInventory3.setX(grampa.getX() + ((grampa.getImage().getWidth()-120)/2) + iconSize*2);
            fishInventory3.setY(grampa.getY() - 50);
            fishSelectedCount.setLayoutX(grampa.getX() + grampa.getImage().getWidth()/2 - 10);
            fishSelectedCount.setLayoutY(grampa.getY() - 75);
        }  
    };

    AnimationTimer nextwaveTimer = new AnimationTimer()
    {   
        public void handle(long currentNanoTime)
        {   
            // System.out.println((timer/60)%60);
            if (map.getCurrentWave() < map.getNoOfWaves()){
                nextWaveAnimationTid++;
                nextWaveAnimationTimer = 10-((nextWaveAnimationTid/60)%60);
                if (nextWaveAnimationTimer < 0){
                    nextWaveRegion.setVisible(false);
                    nextWave();
                    nextWaveAnimationTid = 0;
                    nextWaveAnimationTimer = 10;
                    nextwaveTimer.stop();
                }
                else{
                    nextWaveLabel.setText("Get ready!!! Wave nr. " + Integer.toString(map.getCurrentWave()+1) + " starter om " + nextWaveAnimationTimer);
                }
            }
            else{
                nextWaveAnimationTid++;
                nextWaveAnimationTimer = 10-((nextWaveAnimationTid/60)%60);
                if (nextWaveAnimationTimer < 0){
                    nextwaveTimer.stop();  //  Må ha denne før returnToMenu() !! idk why...
                    restartMap();
                    stats.increaseDucks(ducksOnThisMap);
                    returnToMenu2();
                    nextWaveAnimationTid = 0;
                    nextWaveAnimationTimer = 10;
                }
                else{
                    nextWaveLabel.setText("Gratulerer!!! Nå blir du snart kasta tilbake til home screen :D");
                }
            }
        }
    };

    AnimationTimer grampaImageAnimation = new AnimationTimer()
    {    
        Integer timer = 0;
        int currentL = 0;
        int currentD = 0;
        int currentU = 0;
        int currentR = 0;
        public void handle(long currentNanoTime)
        {   
            if (dir.equals("up")){
                if (map.checkLegalPositionUp(grampa.getX(), grampa.getY())){
                    System.out.println("LEGAL");
                    grampa.setY(grampa.getY()-5);
                }
                // if (grampa.getX() <= anchorPane2.getWidth()/2 && grampa.getY() > ((24960-9*grampa.getX())/64) - 40){
                //     grampa.setY(grampa.getY()-5);
                // }
                // if (grampa.getX() >= anchorPane2.getWidth()/2 && grampa.getX() < anchorPane2.getWidth()/2+50 && grampa.getY() > (-3*grampa.getX()+2220)-40){
                //     grampa.setY(grampa.getY()-5);
                // }
                // if (grampa.getX() >= anchorPane2.getWidth()/2+50 && grampa.getX() < anchorPane2.getWidth() && grampa.getY() > ((81450+10*grampa.getX())/589) - 40){
                //     grampa.setY(grampa.getY()-5);
                // }
                timer++;
                if (timer > 10){
                    // System.out.println("SWITCH");
                    if (currentU == grampaBackList.length-1){ currentU = 0;}
                    else{ currentU++;}
                    grampa.setImage(grampaBackList[currentU]);
                    timer = 0;
                }
            }
            if (dir.equals("left")){
                if (map.checkLegalPositionLeft(grampa.getX(), grampa.getY())){
                    grampa.setX(grampa.getX()-5);
                }
                // if (grampa.getX() <= anchorPane2.getWidth()/2 && grampa.getX() > 0 && grampa.getY() > ((24960-9*grampa.getX())/64) - 40){
                //     grampa.setX(grampa.getX()-5);
                // }
                // if (grampa.getX() >= anchorPane2.getWidth()/2 && grampa.getX() < anchorPane2.getWidth()/2+50 && grampa.getY() > (-3*grampa.getX()+2220)-40){
                //     grampa.setX(grampa.getX()-5);
                // }
                // if (grampa.getX() >= anchorPane2.getWidth()/2+50){
                //     grampa.setX(grampa.getX()-5);
                // }
                timer++;
                if (timer > 10){
                    System.out.println("SWITCH");
                    if (currentL == grampaLeftList.length-1){ currentL = 0;}
                    else{ currentL++;}
                    grampa.setImage(grampaLeftList[currentL]);
                    timer = 0;
                }
            }
            if (dir.equals("right")){
                if (map.checkLegalPositionRight(grampa.getX(), grampa.getY())){
                    grampa.setX(grampa.getX()+5);
                }
                // if (grampa.getX() <= anchorPane2.getWidth()/2+50){
                //     grampa.setX(grampa.getX()+5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2+50 && grampa.getX() < anchorPane2.getWidth()-100 && grampa.getY() > ((81450+10*grampa.getX())/589) - 40){
                //     grampa.setX(grampa.getX()+5);
                // }
                timer++;
                if (timer > 10){
                    // System.out.println("SWITCH");
                    if (currentR == grampaRightList.length-1){ currentR = 0;}
                    else{ currentR++;}
                    grampa.setImage(grampaRightList[currentR]);
                    timer = 0;
                }
            }
            if (dir.equals("down")){
                // grampa.setImage(grampaFront);
                if (map.checkLegalPositionDown(grampa.getX(), grampa.getY())){
                    grampa.setY(grampa.getY()+5);
                }
                // if (grampa.getY() < anchorPane2.getHeight()-100){
                //     grampa.setY(grampa.getY()+5);
                // }
                timer++;
                if (timer > 10){
                    System.out.println("SWITCH");
                    if (currentD == grampaFrontList.length-1){ currentD = 0;}
                    else{ currentD++;}
                    grampa.setImage(grampaFrontList[currentD]);
                    timer = 0;
                }
            }
        }  
    };

    AnimationTimer boarTimer = new AnimationTimer()
    {    
        Random random = new Random();

        int timerLimit = 20;
        int minProximity = 200;
        int maxProximity = 250;
        int maxOffsetChange = 100;

        double timer = 0.0;
        double timerAttack = 0.0;
        int currentRun;
        int currentAttack;

        public void handle(long currentNanoTime)
        {   
            if (enemies.size() == 0 && map.getCurrentWave() > 0){
                boarTimer.stop();
                nextWaveRegion.setVisible(true);
                nextwaveTimer.start();
            }
            for (Enemy enemy : enemies) {
                double dest_x = grampaHitboxX;
                double dest_y = grampaHitboxY;
                // boar.setDestX(dest_x);
                // boar.setDestY(dest_y);

                for (Enemy otherEnemy : enemies) {
                    if (enemy != otherEnemy){
                        if (Math.abs(enemy.getX()-otherEnemy.getX()) < maxProximity && Math.abs(enemy.getY()-otherEnemy.getY()) < maxProximity){
                            if (enemy.getRedirect()){
                                enemy.incrementRedirectTimer(1);
                                if (enemy.getRedirectTimer() > timerLimit){
                                    enemy.setRedirect(false);
                                    enemy.setRedirectTimer(0);
                                }
                                break;
                            }
                            else{
                                enemy.setDestX(dest_x);
                                enemy.setDestY(dest_y);
                            }
                        }
                        if (Math.abs(enemy.getX()-otherEnemy.getX()) < minProximity && Math.abs(enemy.getY()-otherEnemy.getY()) < minProximity){
                            double[] change = {random.nextDouble(maxOffsetChange-10, maxOffsetChange), random.nextDouble(-1*maxOffsetChange, -1*(maxOffsetChange-10))};
                            enemy.setDestX(dest_x + change[random.nextInt(change.length)]);
                            enemy.setDestY(dest_y + change[random.nextInt(change.length)]);
                            enemy.setRedirect(true);
                            break;
                        }
                        if (enemy.getRedirect()){
                            enemy.setRedirect(false);
                        }
                        enemy.setDestX(dest_x);
                        enemy.setDestY(dest_y);
                    }
                }

                if (enemies.size() == 1){
                    enemy.setDestX(dest_x);
                    enemy.setDestY(dest_y);
                }

                double dist_x = enemy.getDestX() - enemy.getX();
                double dist_y = enemy.getDestY() - enemy.getY();
                double speed = enemy.getSpeed();

                //  If boar is near other boar -> let dest_x/y be slightly different.
                //  Or the classic way: bump the boar sligtly away.


                enemies.indexOf(enemy);              //  For health. (No, use class instead!!!!!)

                double degrees = Math.toDegrees(Math.atan(dist_y/dist_x));
    
                if (dist_x > 0 && dist_y > 0){
                    enemy.setX(enemy.getX() + Math.cos(Math.toRadians(degrees))*speed);
                    enemy.setY(enemy.getY() + Math.sin(Math.toRadians(degrees))*speed);

                    timer++;
                    if (timer > 10){
                        // System.out.println("SWITCH");
                        if (currentRun == enemy.getRunninImages().length-1){ currentRun = 0;}
                        else{ currentRun++;}
                        enemy.getImageView().setImage(enemy.getRunninImages()[currentRun]);
                        enemy.getImageView().setScaleX(1);
                        timer = 0;
                    }
                }
                if (dist_x > 0 && dist_y < 0){
                    enemy.setX(enemy.getX() + Math.cos(Math.toRadians(degrees))*speed);
                    enemy.setY(enemy.getY() + Math.sin(Math.toRadians(degrees))*speed);

                    timer++;
                    if (timer > 10){
                        // System.out.println("SWITCH");
                        if (currentRun == enemy.getRunninImages().length-1){ currentRun = 0;}
                        else{ currentRun++;}
                        enemy.getImageView().setImage(enemy.getRunninImages()[currentRun]);
                        enemy.getImageView().setScaleX(1);
                        timer = 0;
                    }
                }
                if (dist_x < 0 && dist_y < 0){
                    enemy.setX(enemy.getX() + Math.cos(Math.toRadians(180 - degrees))*speed);
                    enemy.setY(enemy.getY() + Math.sin(Math.toRadians(-degrees))*speed);

                    timer++;
                    if (timer > 10){
                        // System.out.println("SWITCH");
                        if (currentRun == enemy.getRunninImages().length-1){ currentRun = 0;}
                        else{ currentRun++;}
                        enemy.getImageView().setImage(enemy.getRunninImages()[currentRun]);
                        enemy.getImageView().setScaleX(-1);
                        timer = 0;
                    }
                }
                if (dist_x < 0 && dist_y > 0){
                    enemy.setX(enemy.getX() + Math.cos(Math.toRadians(180 - degrees))*speed);
                    enemy.setY(enemy.getY() + Math.sin(Math.toRadians(-degrees))*speed);

                    timer++;
                    if (timer > 10){
                        // System.out.println("SWITCH");
                        if (currentRun == enemy.getRunninImages().length-1){ currentRun = 0;}
                        else{ currentRun++;}
                        enemy.getImageView().setImage(enemy.getRunninImages()[currentRun]);
                        enemy.getImageView().setScaleX(-1);
                        timer = 0;
                    }
                }
                if (Math.abs(enemy.getX()-grampaHitboxX) < 50 && Math.abs(enemy.getY()-grampaHitboxY) < 50){    //  For å få mer accurate: Lag grampa klasse, set centerx/y litt skrått til høyre slik at det blir i midten av bildet.
                    timerAttack++;
                    if (timerAttack > 20){
                        System.out.println("ATTACK");
                        if (currentAttack == enemy.getAttackImages().length-1){ currentAttack = 0;}
                        else{ currentAttack++;}
                        enemy.getImageView().setImage(enemy.getAttackImages()[currentAttack]);
                        healthBarGrampa.setWidth(healthBarGrampa.getWidth()-1);
                        if (healthBarGrampa.getWidth() < 0){
                            pauseButton.fire();
                            boarTimer.stop();
                        }
                        timerAttack = 0;
                    }
                }
                enemy.getRectangle().setX(enemy.getX() + ((enemy.getImageView().getImage().getWidth()-70)/2));
                enemy.getRectangle().setY(enemy.getY());
                enemy.getRectangleOutline().setX(enemy.getX() + ((enemy.getImageView().getImage().getWidth()-70)/2) - 1);
                enemy.getRectangleOutline().setY(enemy.getY() + 1);
            }
            // boar.setX(boar.getX() + Math.toDegrees(Math.sin(degrees))*1);
            // boar.setY(boar.getY() + Math.toDegrees(Math.cos(degrees))*1);
        }  
    };

    AnimationTimer throwFishTimer = new AnimationTimer()
    {    
        
        int timer = 0;
        boolean up = true;
        double speed = 50;
        int fortegn = 1;
        public void handle(long currentNanoTime)
        {   
            timer ++;
            currentFish.setTimer(currentFish.getTimer()+0.2);
            // currentFish.setTimer((currentNanoTime - starttime)*Math.pow(10, -9)*4);

            if (currentFish.getTimer() < currentFish.getTimeLimit()){
                double fishX = currentFish.getStartX() + (currentFish.getSpeed() * Math.cos(Math.toRadians(60)) * currentFish.getTimer() * currentFish.getDir());
                double fishY = currentFish.getStartY() + ((currentFish.getSpeed() * Math.sin(Math.toRadians(60)) * currentFish.getTimer()) - (0.5 * 9.81 * Math.pow(currentFish.getTimer(), 2)))*-1;
                currentFish.setX(fishX);
                currentFish.setY(fishY);
            }
            else{
                for (Enemy enemy : enemies) {
                    if (Math.abs(currentFish.getX()-enemy.getX()) < 40 && Math.abs(currentFish.getY()-enemy.getY()) < 40){
                        enemy.changeHealth(-50);
                        if (enemy.getHealth() < 0){
                            ducksOnThisMap+=33;
                            System.out.println(ducksOnThisMap);
                            anchorPane2.getChildren().remove(enemy.getImageView());
                            anchorPane2.getChildren().remove(enemy.getRectangle());
                            anchorPane2.getChildren().remove(enemy.getRectangleOutline());
                            enemies.remove(enemy);
                            break;
                        }
                    }
                }
                anchorPane2.getChildren().remove(currentFish.getImageView());
                fishFlying = false;
                throwFishTimer.stop();
            }


            // if (dir.equals("left")){
            //     System.out.println("venstre");
            //     fortegn = -1;
            // }
            // else if (dir.equals("right")){
            //     System.out.println("høyre");
            //     fortegn = 1;
            // }
            // if (timer > 5){
            //     System.out.println(timer);
            //     currentFish.setX(currentFish.getX()+(speed*currentFish.getDir()));
            //     if (currentFish.getY() > fiskStartY-100 && up){
            //         currentFish.setY(currentFish.getY()-speed);
            //     }
            //     else{
            //         up = false;
            //         currentFish.setY(currentFish.getY()+speed);
            //     }
            //     if (currentFish.getY() > fiskStartY){
            //         up = true;
            //         timer = 0;
            //         for (Boar boar : boars) {
            //             if (Math.abs(currentFish.getX()-boar.getX()) < 20 && Math.abs(currentFish.getY()-boar.getY()) < 20){
            //                 boar.changeHealth(-10);
            //                 if (boar.getHealth() < 0){
            //                     boars.remove(boar);
            //                 }
            //             }
            //         }
            //         throwFishTimer.stop();
            //     }
            // }
        }  
    };

    public void rotateFishSelector2(int antallRotasjoner) {
        Collections.rotate(obtainedFishes, antallRotasjoner);

        fishInventory1.setImage(obtainedFishes.get(0));
        fishInventory2.setImage(obtainedFishes.get(1));
        fishInventory3.setImage(obtainedFishes.get(2));

        fishInventory1.setOpacity(0.3);
        if (fishInventory2.getImage() == blackFillerIcon){
            fishSelectedCount.setVisible(false);
        }
        else{
            fishSelectedCount.setVisible(true);
            fishSelectedCount.setText(Integer.toString(fishAndNumberMap.get(fishInventory2.getImage())));
        }
        // fishInventory2.setFitWidth(48);
        // fishInventory2.setFitHeight(48);
        fishInventory3.setOpacity(0.3);
        
    }

    public void rotateFishSelector() {                         //  Lag helt på nytt...
        System.out.println(fishInventorySelectedNo);
                if (fishInventorySelectedNo == fishInventory.size()-2){
                    System.out.println("Første");
                    fishInventory1.setImage(fishInventory.get(fishInventorySelectedNo+1));
                    fishInventory2.setImage(fishInventory.get(0));
                    fishInventory3.setImage(fishInventory.get(fishInventorySelectedNo));
                    fishSelected = 0;
                    fishInventorySelectedNo++;
                }
                else if (fishInventorySelectedNo == fishInventory.size() - 1){
                    System.out.println("Andre");
                    fishInventory1.setImage(fishInventory.get(1));
                    fishInventory2.setImage(fishInventory.get(fishInventorySelectedNo));
                    fishInventory3.setImage(fishInventory.get(0));
                    fishSelected = fishInventorySelectedNo;
                    fishInventorySelectedNo = 0;
                }
                else{
                    System.out.println("Tredje");
                    fishInventory1.setImage(fishInventory.get(fishInventorySelectedNo));
                    fishInventory2.setImage(fishInventory.get(fishInventorySelectedNo+1));
                    fishInventory3.setImage(fishInventory.get(fishInventorySelectedNo+2));
                    fishSelected = fishInventorySelectedNo+1;
                    fishInventorySelectedNo++;
                }
                fishInventory1.setOpacity(0.3);
                if (fishInventory2.getImage() == blackFillerIcon){
                    fishSelectedCount.setVisible(false);
                }
                else{
                    fishSelectedCount.setVisible(true);
                    fishSelectedCount.setText(Integer.toString(fishAndNumberMap.get(fishInventory2.getImage())));
                }
                // fishInventory2.setFitWidth(48);
                // fishInventory2.setFitHeight(48);
                fishInventory3.setOpacity(0.3);
    }

    public void nextWave() {               //  Samle wavenumber herfra og firstmap kun som getter og setter fra firstmap/map
        System.out.println("NEXT WAVE");
        if (map.getCurrentWave() < map.getNoOfWaves()){
            enemies = map.nextWave();
            // map.setCurrentWave(map.getCurrentWave()+1);
            for (Enemy enemy : enemies) {
                anchorPane2.getChildren().add(enemy.getRectangle());
                anchorPane2.getChildren().add(enemy.getRectangleOutline());
                anchorPane2.getChildren().add(enemy.getImageView());
            }
            boarTimer.start();
        }
    }

    public void resetFishInventory() {
        obtainedFishes.clear();
        obtainedFishes.add(blackFillerIcon);
        obtainedFishes.add(blackFillerIcon);
        obtainedFishes.add(blackFillerIcon);

        for (Image image : fishAndNumberMap.keySet()) {
            fishAndNumberMap.put(image, 0);
        }

        rotateFishSelector2(1);
    }

    @FXML
    public void handleB1() {
        // System.out.println("Flytt b1!");
        // b1.setLayoutY(b1.getLayoutY() + 10);
        stats.increaseDucks(3);
        System.out.println(stats.getDucks());
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
            if (keyEvent.getCode() == KeyCode.W){
                dir = "up";
                grampaImageAnimation.start();
                // if (grampa.getY() > 10){
                //     grampa.setY(grampa.getY()-5);
                // }

                // if (grampa.getX() < anchorPane2.getWidth()/2 && grampa.getY() > ((24960-9*grampa.getX())/64) - 40){
                //     grampa.setY(grampa.getY()-5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2 && grampa.getX() < anchorPane2.getWidth()/2+50 && grampa.getY() > (-3*grampa.getX()+2220)-40){
                //     grampa.setY(grampa.getY()-5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2+50 && grampa.getX() < anchorPane2.getWidth() && grampa.getY() > ((81450+10*grampa.getX())/589) - 40){
                //     grampa.setY(grampa.getY()-5);
                // }
            }
            else if (keyEvent.getCode() == KeyCode.A){
                dir = "left";
                grampaImageAnimation.start();
                // if (grampa.getX() > 50){
                //     grampa.setX(grampa.getX()-5);
                // }

                // if (grampa.getX() < anchorPane2.getWidth()/2 && grampa.getX() > 0 && grampa.getY() > ((24960-9*grampa.getX())/64) - 40){
                //     grampa.setX(grampa.getX()-5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2 && grampa.getX() < anchorPane2.getWidth()/2+50 && grampa.getY() > (-3*grampa.getX()+2220)-40){
                //     grampa.setX(grampa.getX()-5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2+50){
                //     grampa.setX(grampa.getX()-5);
                // }
            }
            else if (keyEvent.getCode() == KeyCode.D){
                dir = "right";
                grampaImageAnimation.start();

                // if (grampa.getX() < anchorPane2.getWidth()/2+50){
                //     grampa.setX(grampa.getX()+5);
                // }
                // if (grampa.getX() > anchorPane2.getWidth()/2+50 && grampa.getX() < anchorPane2.getWidth()-100 && grampa.getY() > ((81450+10*grampa.getX())/589) - 40){
                //     grampa.setX(grampa.getX()+5);
                // }
            }
            else if (keyEvent.getCode() == KeyCode.S){
                dir = "down";
                grampaImageAnimation.start();

                // if (grampa.getY() < anchorPane2.getHeight()-100){
                //     grampa.setY(grampa.getY()+5);
                // }
            }
            else{
                System.out.println("Stop");
                grampaImageAnimation.stop();
            }

            if (keyEvent.getCode() == KeyCode.R){
                System.out.println("FISHATTEMPT");
                if (grampa.getX() < anchorPane2.getWidth()/2 && grampa.getY() - (((24960-9*grampa.getX())/64) - hboxsize) < 5){
                    System.out.println("FISH!!!!!!!!!!!!!!!!!!!");
                    Random rand = new Random();
                    Object[] possibleFishes = possibleFishesMap.keySet().toArray();
                    Object fish = possibleFishes[rand.nextInt(possibleFishes.length)];
                    Image icon = possibleFishesMap.get(fish);
                    System.out.println(fish);
                    // if (!fishInventory.contains(icon)){
                    //     if (fishInventory.contains(blackFillerIcon)){
                    //         System.out.println("Hey");
                    //         fishInventory.remove(blackFillerIcon);
                    //     }
                    //     System.out.println("ADD " + fish);
                    //     // possibleFishesMap.entrySet().toArray;
                    //     fishInventory.add(icon);
                    //     fishInventorySelectedNo = fishInventory.size()-1;
                    //     fishInventoryClasses.add(reversePossibleFishesMap.get(icon));
                    // }
                    // else{
                    //     System.out.println(fishInventory);
                    //     System.out.println(possibleFishesMap);
                    //     fishInventorySelectedNo = fishInventory.indexOf(icon);
                    // }
                    // fishAndNumberMap.put(icon, fishAndNumberMap.get(icon) + 1);
                    // rotateFishSelector();

                    if (!obtainedFishes.contains(icon)){
                        if (obtainedFishes.contains(blackFillerIcon)){
                            System.out.println("Hey");
                            obtainedFishes.remove(blackFillerIcon);
                        }
                        System.out.println("ADD " + fish);
                        // possibleFishesMap.entrySet().toArray;
                        obtainedFishes.add(icon);
                        fishInventorySelectedNo = fishInventory.size()-1;
                        fishInventoryClasses.add(reversePossibleFishesMap.get(icon));
                        // rotateFishSelector2(obtainedFishes.size()-2);
                        fishAndNumberMap.put(icon, fishAndNumberMap.get(icon) + 1);
                        rotateFishSelector2(2);
                    }
                    else{
                        int index = obtainedFishes.indexOf(icon);
                        fishAndNumberMap.put(icon, fishAndNumberMap.get(icon) + 1);
                        rotateFishSelector2(obtainedFishes.size() - index + 1);
                        fishInventorySelectedNo = fishInventory.indexOf(icon);
                    }
                }
                else{
                    System.out.println("PUSH!");
                    for (Enemy enemy : enemies) {
                        if (dir.equals("right")){
                            if (enemy.getX()-grampaHitboxX > 0 && enemy.getX()-grampaHitboxX < 100 && Math.abs(enemy.getY()-grampaHitboxY) < 50){ 
                                enemy.setX(enemy.getX() + 50);
                            }
                        }
                        if (dir.equals("left")){
                            if (enemy.getX()-grampaHitboxX < 0 && enemy.getX()-grampaHitboxX > -100 && Math.abs(enemy.getY()-grampaHitboxY) < 50){ 
                                enemy.setX(enemy.getX() - 50);
                            }
                        }
                        if (dir.equals("down")){
                            if (Math.abs(enemy.getX()-grampaHitboxX) < 50 && enemy.getY()-grampaHitboxY > 0 && enemy.getY()-grampaHitboxY < 100){ 
                                enemy.setY(enemy.getY() + 50);
                            }
                        }
                        if (dir.equals("up")){
                            if (Math.abs(enemy.getX()-grampaHitboxX) < 50 && enemy.getY()-grampaHitboxY < 0 && enemy.getY()-grampaHitboxY > -100){ 
                                enemy.setY(enemy.getY() - 50);
                            }
                        }
                    }
                }
            }

            if (keyEvent.getCode() == KeyCode.E){
                System.out.println("THROW FISH!!!!!");
                // currentFish = new ImageView();
                // fishInventoryClasses.get(fishSelected).getDeclaredConstructor(double.class, double.class, int.class).newInstance(1.0, 1.0, 1);
                if (fishFlying == false && fishInventory2.getImage() != blackFillerIcon && fishAndNumberMap.get(fishInventory2.getImage()) > 0){
                    if (dir.equals("left")){
                        System.out.println("venstre");
                        reversePossibleFishesMap.get(fishInventory2.getImage());
                        currentFish = reversePossibleFishesMap.get(fishInventory2.getImage()).getDeclaredConstructor(double.class, double.class, int.class).newInstance(grampa.getX(), grampa.getY(), -1);
                    }
                    else if (dir.equals("right")){
                        System.out.println("høyre");
                        currentFish = reversePossibleFishesMap.get(fishInventory2.getImage()).getDeclaredConstructor(double.class, double.class, int.class).newInstance(grampa.getX(), grampa.getY(), 1);
                    }
                    // currentFish.setImage(gjeddeImg);
                    anchorPane2.getChildren().add(currentFish.getImageView());
                    // currentFish.setX(grampa.getX());
                    // currentFish.setY(grampa.getY());
                    fishAndNumberMap.put(fishInventory2.getImage(), fishAndNumberMap.get(fishInventory2.getImage()) - 1);
                    fishSelectedCount.setText(Integer.toString(fishAndNumberMap.get(fishInventory2.getImage())));
                    if (fishAndNumberMap.get(fishInventory2.getImage()) <= 0){
                        rotateFishSelector2(1);
                    }
                    fiskStartY = currentFish.getY();
                    currentFish.setStartX(grampa.getX());
                    currentFish.setStartY(grampa.getY());
                    currentFish.setTimer(0.0);
                    starttime = System.nanoTime();
                    fishFlying = true;
                    throwFishTimer.start();
                }
            }

            if (keyEvent.getCode() == KeyCode.Q){
                rotateFishSelector2(1);
                // System.out.println(fishInventorySelectedNo);
                // if (fishInventorySelectedNo == fishInventory.size()-2){
                //     System.out.println("Første");
                //     fishInventory1.setImage(fishInventory.get(fishInventorySelectedNo+1));
                //     fishInventory2.setImage(fishInventory.get(0));
                //     fishInventory3.setImage(fishInventory.get(fishInventorySelectedNo));
                //     fishSelected = 0;
                //     fishInventorySelectedNo++;
                // }
                // else if (fishInventorySelectedNo == fishInventory.size() - 1){
                //     System.out.println("Andre");
                //     fishInventory1.setImage(fishInventory.get(1));
                //     fishInventory2.setImage(fishInventory.get(fishInventorySelectedNo));
                //     fishInventory3.setImage(fishInventory.get(0));
                //     fishSelected = fishInventorySelectedNo;
                //     fishInventorySelectedNo = 0;
                // }
                // else{
                //     System.out.println("Tredje");
                //     fishInventory1.setImage(fishInventory.get(fishInventorySelectedNo));
                //     fishInventory2.setImage(fishInventory.get(fishInventorySelectedNo+1));
                //     fishInventory3.setImage(fishInventory.get(fishInventorySelectedNo+2));
                //     fishSelected = fishInventorySelectedNo+1;
                //     fishInventorySelectedNo++;
                // }
                // fishInventory1.setOpacity(0.3);
                // // fishInventory2.setFitWidth(48);
                // // fishInventory2.setFitHeight(48);
                // fishInventory3.setOpacity(0.3);
            }

            if (keyEvent.getCode() == KeyCode.G){
                nextWave();
            }

            if (keyEvent.getCode() == KeyCode.V){
                for (int i = 0; i < 5; i++) {
                    Boar boarNode = new Boar(70.0, 500.0 + i*90, 400.0);
                    anchorPane2.getChildren().add(boarNode.getRectangle());
                    anchorPane2.getChildren().add(boarNode.getRectangleOutline());
                    anchorPane2.getChildren().add(boarNode.getImageView());
                    enemies.add(boarNode);
                }
            }

            if (keyEvent.getCode() == KeyCode.B){
                System.out.println("THE BOAR IS COMING!!!!");
                boarTimer.start();
            }

            if (keyEvent.getCode() == KeyCode.P){
                System.out.println(anchorPane2.getWidth() + ", " + anchorPane2.getHeight());
                System.out.println(grampa.getX() + ", " + grampa.getY());
                double dest_x = grampa.getX();
                double dest_y = grampa.getY();
                double dist_x = dest_x - boar.getX();
                double dist_y = dest_y - boar.getY();
                System.out.println(dist_x + ", " + dist_y);
                double degrees = 0;
                if (dist_x > 0 && dist_y > 0){
                    System.err.println("Under til høyre");
                    degrees = Math.toDegrees(Math.atan(dist_y/dist_x));

                }
                if (dist_x < 0 && dist_y < 0){
                    System.err.println("Over til venstre");
                    degrees = Math.toDegrees(Math.atan(dist_y/dist_x));
                }

                degrees = Math.toDegrees(Math.atan(dist_y/dist_x));
                System.out.println("DEGREES: " + degrees);
                System.out.println(Math.toDegrees(Math.atan(dist_x/dist_y)));
                
                healthBarGrampa.setWidth(healthBarGrampa.getWidth()-10);
                // for (Boar boar : boars) {
                //     boar.setY(100);
                //     System.out.println(boar.getX()+ ", " + boar.getY());
                // }
                // System.out.println(fishInventoryClasses.get(0));
                // System.out.println(fishInventoryClasses.get(0).getConstructors());
                // System.out.println(fishInventoryClasses.get(0).getDeclaredConstructor(double.class, double.class, int.class).newInstance(1.0, 1.0, 1));
                // System.out.println(fishInventoryClasses.get(0).getConstructor(double.class, double.class, int.class));
                // Fisk fisk = fishInventoryClasses.get(fishSelected).getDeclaredConstructor(double.class, double.class, int.class).newInstance(1.0, 1.0, 1);
                // anchorPane2.getChildren().add(fisk.getImageView());
                System.out.println(fishInventory);
            }

            if (keyEvent.getCode() == KeyCode.N){
                Line line1 = new Line(0, anchorPane2.getHeight()/2+40, anchorPane2.getWidth()/2, anchorPane2.getHeight()/2-50);
                anchorPane2.getChildren().addAll(line1);
                for (int i = 0; i < anchorPane2.getWidth()/2; i++) {
                    Circle cir1 = new Circle(i, ((24960-9*i)/64) - 40, 1);
                    anchorPane2.getChildren().add(cir1);
                }
                Line line2 = new Line(anchorPane2.getWidth()/2, anchorPane2.getHeight()/2-50, anchorPane2.getWidth()/2+50, 110);
                anchorPane2.getChildren().addAll(line2);
                for (double i = anchorPane2.getWidth()/2; i < anchorPane2.getWidth()/2+50; i++) {
                    Circle cir1 = new Circle(i, (-3*i+2220) - 40, 1);
                    anchorPane2.getChildren().add(cir1);
                }
                Line line3 = new Line(anchorPane2.getWidth()/2+50, 110, anchorPane2.getWidth(), 120);
                anchorPane2.getChildren().addAll(line3);
                for (double i = anchorPane2.getWidth()/2+50; i < anchorPane2.getWidth(); i++) {
                    Circle cir1 = new Circle(i, ((81450+10*i)/589) - 40, 1);
                    anchorPane2.getChildren().add(cir1);
                }
                grampa.setX(100);
                grampa.setY(anchorPane2.getHeight()/2 + 100);
                boar.setX(790);
                boar.setY(440);
            }

            if (keyEvent.getCode() == KeyCode.M){
                // double a = 425.0;
                double a = anchorPane2.getWidth()*0.225;
                // double b = 225.0;
                double b = anchorPane2.getHeight()*0.250;  //  0.375
                System.out.println(a + " , " + b);
                int x = (int) a;
                Double y1;
                Double y2;
                for (int i = -x; i <= x; i++) {
                    y1 = (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2));
                    y2 = (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2)) *-1;
                    // System.out.println("x= " + i + " , y= " + y + " , " + Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2)));
                    Circle cir1 = new Circle(anchorPane2.getWidth()/2 + i, anchorPane2.getHeight()/2 + 30 + y1, 1);
                    Circle cir2 = new Circle(anchorPane2.getWidth()/2 + i, anchorPane2.getHeight()/2 + 30 + y2, 1);
                    anchorPane2.getChildren().add(cir1);
                    anchorPane2.getChildren().add(cir2);
                }

                Line line1 = new Line(50, 10, 50, anchorPane2.getHeight()-30);
                anchorPane2.getChildren().addAll(line1);
                Line line2 = new Line(50, 10, anchorPane2.getWidth()-50, 10);
                anchorPane2.getChildren().addAll(line2);
                Line line3 = new Line(anchorPane2.getWidth()-50, 10, anchorPane2.getWidth()-50, anchorPane2.getHeight()-30);
                anchorPane2.getChildren().addAll(line3);
                Line line4 = new Line(50, anchorPane2.getHeight()-30, anchorPane2.getWidth()-50, anchorPane2.getHeight()-30);
                anchorPane2.getChildren().addAll(line4);
            }

            if (keyEvent.getCode() == KeyCode.O){
                // System.out.println(grampaHitboxX);
                // System.out.println(grampaHitboxY);
                // nextwaveTimer.start();
                System.out.println(fishAndNumberMap);
            }

            if (keyEvent.getCode() == KeyCode.Y){      //  Experimental stuff
                if (zoomAndScale == false){
                    zoomAndScale = true;
                }
                else if (zoomAndScale){
                    zoomAndScale = false;
                }
                System.out.println("ZoomAndScale: " + zoomAndScale);
            }

            if (keyEvent.getCode() == KeyCode.H){
                anchorPane2.toBack();
            }
    }

    @FXML
    private void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W){
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.A){
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.D){
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.S){
            grampaImageAnimation.stop();
        }
    }

    @FXML
    private void handleScroll(ScrollEvent se) {
        if (zoomAndScale){
            System.out.println(se.getDeltaY());
            anchorPane2.toBack();
            if (se.getDeltaY() > 0){
                if (scale.getX() < 2.0){
                    scale.setX(scale.getX() + 0.1);
                    scale.setY(scale.getY() + 0.1);
                }
            }
            else if (se.getDeltaY() < 0){
                // System.out.println(scale.getX() + ", " + scale.getY());
                if (scale.getX() > 1.0){
                    scale.setX(scale.getX() - 0.1);
                    scale.setY(scale.getY() - 0.1);
                }
            }
        }
        
    }

    @FXML
    private void moveScreen(MouseEvent e) {
        mousePos.setText("x=" + String.valueOf(Math.round(e.getX())) + " : y=" + String.valueOf(Math.round(e.getY())));
    }

    
}

package game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class PlatformController implements Initializable{

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

    PlayerStats stats;
    PlatformMap map;
    Scale scale = new Scale();
    boolean zoomAndScale = true;
    double scaleMoveFactor = 20.0;
    HashMap<Double, Double> tempScaleValuesMap = new HashMap<Double, Double>(); 

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
    String dir = "right";
    double grampaStartX = 0.0;
    double grampaStartY = 0.0;
    double grampaJumpTimer = 0.0;
    double jumpAngle = 90;
    boolean grampaJump = false;
    int jumpDirection = 1;
    boolean onPlatform = false;
    double grampaFreeFallingTimer = 0.0;
    double grampaStartFallingY = 0.0;
    double grampaStartFallingX = 0.0;
    boolean freeFalling = false;
    boolean up = true;
    double adjustFallingX = 0.0;
    double freeFallingXSpeed = 0;
    int freeFallingDir = 1;
    double jumpSpeed = 50;

    Circle cir = new Circle(0, 0, 1, Color.RED);

    List<Enemy> enemies = new ArrayList<Enemy>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Start of platform controller");

        anchorPane2.getTransforms().add(scale);

        grampa.setImage(grampaRight1);
        anchorPane2.getChildren().add(grampa);
        grampa.setPreserveRatio(false);
        grampa.setSmooth(false);
        grampa.setX(640);
        grampa.setY(340);

        tempScaleValuesMap.put(1.0, 55.0);
        tempScaleValuesMap.put(1.1, 55.0);
        tempScaleValuesMap.put(1.2, 30.0);
        tempScaleValuesMap.put(1.3, 21.5);
        tempScaleValuesMap.put(1.4, 17.5);
        tempScaleValuesMap.put(1.5, 15.0);
        tempScaleValuesMap.put(1.6, 13.3);
        tempScaleValuesMap.put(1.7, 12.1);
        tempScaleValuesMap.put(1.8, 11.25);
        tempScaleValuesMap.put(1.9, 10.55);
        tempScaleValuesMap.put(2.0, 10.0);

        alwaysOnTimer.start();

        Label borderLabel = new Label("");  //  For at bakgrunnsbildet skal strekkes helt ut...   
        anchorPane2.getChildren().add(borderLabel);
        borderLabel.setLayoutX(2628);

        // Circle cir = new Circle(0, 0, 5, Color.RED);
        anchorPane2.getChildren().add(cir);
    }

    public void initData(PlayerStats data) {
        this.stats = data;
        System.out.println("Data: " + data);
        System.out.println(stats.getDucks());
        map = stats.getMapPlatform(stats.getMapNumber());
        System.out.println("MAP: " + map);
        // Stage stageTheLabelBelongs = (Stage) anchorPane2.getScene().getWindow();
        Stage stageTheLabelBelongs = stats.getStage();
        stageTheLabelBelongs.setMaximized(false);
        stageTheLabelBelongs.setMaximized(true);
        // Image img = new Image(getClass().getResourceAsStream("res/mapBig.png"), 1000, 1000, false, false);
        Image img = map.getMap();
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(2628, 595, false, false, false, false));
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        Background bGround = new Background(bImg);
        anchorPane2.setBackground(bGround);
        // anchorPane2.setMaxWidth(2628);
        // anchorPane2.setPrefWidth(2628); //  221 103
        handleScroll(null);

        // grampa.setX(map.getStartX());
        // grampa.setY(map.getStartY());

        // grampa.setFitHeight(32);
        // grampa.setFitWidth(32);
    }

    AnimationTimer alwaysOnTimer = new AnimationTimer()
    {   
        public void handle(long currentNanoTime)
        {   
            // healthBarGrampa.setX(grampa.getX() + ((grampa.getImage().getWidth()-70)/2));
            // healthBarGrampa.setY(grampa.getY() - 10);
            // healthBarGrampaOutline.setX(grampa.getX() + ((grampa.getImage().getWidth()-70)/2) - 1);
            // healthBarGrampaOutline.setY(grampa.getY() - 11);
            // goldDucksLabel.setText(String.valueOf(ducksOnThisMap));
            // waveLabel.setText("Wave: " + Integer.toString(map.getCurrentWave()));
            // grampaHitboxX = grampa.getX() + grampa.getImage().getWidth()/2;
            // grampaHitboxY = grampa.getY() + grampa.getImage().getHeight()/2;
            // scale.setPivotX(grampa.getX()+100);
            // scale.setPivotY(grampa.getY()+100);
            // scale.set
            // System.out.println(scale.getX());
            cir.setCenterX(grampa.getX()+grampa.getImage().getWidth()/2);
            cir.setCenterY(grampa.getY()+grampa.getImage().getHeight());
        }  
    };

    AnimationTimer grampaImageAnimation = new AnimationTimer()
    {    
        Integer timer = 0;
        double scaleFactor;
        double moveFactor = 10;
        int currentL = 0;
        int currentD = 0;
        int currentU = 0;
        int currentR = 0;
        public void handle(long currentNanoTime)
        {   
            System.out.println(anchorPane2.getWidth()/2 +", " + grampa.getX());
            System.out.println(dir + ", " + (grampa.getX() > outerRegion.getWidth()/2 + 230) + ", " + (grampa.getX() < outerRegion.getWidth()/2 - 230));
            double grampaXonScreen = grampa.localToScreen(grampa.getBoundsInLocal()).getMinX();
            if ((grampaXonScreen > outerRegion.getWidth()/2 + 230 && dir.equals("right")) || (grampaXonScreen < outerRegion.getWidth()/2 - 230 && dir.equals("left"))){
                scaleFactor = tempScaleValuesMap.get(BigDecimal.valueOf(scale.getX()).setScale(1, RoundingMode.HALF_UP).doubleValue());
                System.out.println("HEI" + scaleFactor);
            }
            else{
                scaleFactor = 0;
            }
            moveFactor = scale.getX()*5;
            System.out.println(scaleFactor);
            anchorPane2.toBack();
            if (dir.equals("up")){
                if (map.checkLegalPositionUp(grampa.getX(), grampa.getY())){
                    grampa.setY(grampa.getY()-5);
                    // scale.setPivotY(scale.getPivotY()-50);
                }
                
                timer++;
                if (timer > 10){
                    if (currentU == grampaBackList.length-1){ currentU = 0;}
                    else{ currentU++;}
                    grampa.setImage(grampaBackList[currentU]);
                    timer = 0;
                }
            }
            if (dir.equals("left")){
                if (map.checkLegalPositionLeft(grampa.getX(), grampa.getY())){
                    if (freeFalling == true || (grampaJump == true)){   //  up == false
                        System.out.println("ADJUSTING");
                        // adjustFallingX += 5;
                        // jumpSpeed += 1;                                                                        // To go faster/further
                        grampaStartX -= 3;
                        grampaStartFallingX -= 3;
                        // grampaJumpTimer += 0.1;                                                                // To go faster down.
                        // jumpAngle -= 0.5;
                    }
                    else{
                        jumpAngle = 70;
                        grampa.setX(grampa.getX()-5);
                    }
                    // if (grampa.getX() > 410){
                    //     anchorPane2.toBack();
                    //     scale.setPivotX(scale.getPivotX()-scaleFactor);
                    // }
                    if (grampaXonScreen < outerRegion.getWidth()/2 - 230 && anchorPane2.getLayoutX() < 60){
                        anchorPane2.setLayoutX(anchorPane2.getLayoutX()+moveFactor);
                    }
                }
    
                timer++;
                if (timer > 10){
                    if (currentL == grampaLeftList.length-1){ currentL = 0;}
                    else{ currentL++;}
                    grampa.setImage(grampaLeftList[currentL]);
                    timer = 0;
                }
            }
            if (dir.equals("right")){
                if (map.checkLegalPositionRight(grampa.getX(), grampa.getY())){
                    if (freeFalling == true || (grampaJump == true)){
                        System.out.println("ADJUSTING");
                        // adjustFallingX += 5;
                        // jumpSpeed += 1;                                                                        // To go faster/further
                        grampaStartX += 3;
                        grampaStartFallingX += 3;
                        // grampaJumpTimer += 0.1;                                                                // To go faster down.
                        // jumpAngle -= 0.5;
                    }
                    else{
                        jumpAngle = 70;
                        grampa.setX(grampa.getX()+5);
                    }
                    // if (grampa.getX() < 2218){
                    //     System.out.println("FLYTTE" + scaleFactor);
                    //     scale.setPivotX(scale.getPivotX()+scaleFactor);
                    //     anchorPane2.toBack();
                    // }
                    if (grampaXonScreen > outerRegion.getWidth()/2 + 230 && anchorPane2.getLayoutX() > -1545){
                        anchorPane2.setLayoutX(anchorPane2.getLayoutX()-moveFactor);
                    }
                }
                
                timer++;
                if (timer > 10){
                    if (currentR == grampaRightList.length-1){ currentR = 0;}
                    else{ currentR++;}
                    grampa.setImage(grampaRightList[currentR]);
                    timer = 0;
                }
            }
            if (dir.equals("down")){
                if (map.checkLegalPositionDown(grampa.getX(), grampa.getY())){
                    grampa.setY(grampa.getY()+5);
                    // scale.setPivotY(scale.getPivotY()+50);
                    anchorPane2.toBack();
                }
                
                timer++;
                if (timer > 10){
                    if (currentD == grampaFrontList.length-1){ currentD = 0;}
                    else{ currentD++;}
                    grampa.setImage(grampaFrontList[currentD]);
                    timer = 0;
                }
            }

            if (grampaJump == false && freeFalling == false && checkIfOnAPlatform() == false){
                System.out.println("FALLING OFF!");
                grampaFreeFallingTimer = 0.0;
                grampaStartFallingY = grampa.getY();
                grampaStartFallingX = grampa.getX();
                freeFallingXSpeed = 15;
                adjustFallingX = 0;
                jumpSpeed = 50;
                if (dir.equals("right")){freeFallingDir = 1;}
                if (dir.equals("left")){freeFallingDir = -1;}
                freeFalling = true;
                onPlatform = false;
                grampaImageAnimation.stop();
                grampaFreeFallingAnimation.start();
            }
            // System.out.println(dir);

            // int safe = 0;
            // // if (onPlatform){
            //     double grampaFeetX = grampa.getX() + grampa.getImage().getWidth()/2;
            //     double grampaFeetY = grampa.getY() + grampa.getImage().getHeight();
            //     for (PlatformObject platform : map.getPlatformList()) {
            //         if (grampaFeetY > platform.getStartY()
            //              && grampaFeetY < platform.getStartY() + 10
            //              && grampaFeetX > platform.getStartX()
            //              && grampaFeetX < platform.getEndX()){
            //             safe += 1;
            //             break;
            //         }
            //     }
            //     if (safe < 1){
            //         System.out.println("FALLING OFF!");
            //         grampaFreeFallingTimer = 0.0;
            //         grampaStartFallingY = grampa.getY();
            //         freeFalling = true;
            //         onPlatform = false;
            //         grampaFreeFallingAnimation.start();
            //     }
            // }
        }  
    };

    AnimationTimer jumpAnimation = new AnimationTimer()
    {    
        Integer timer = 0;
        // double jumpSpeed = 50;
        // boolean up = true;
        
        public void handle(long currentNanoTime)
        {  
            grampaJumpTimer += 0.2;
            // map.getPlatformList();
            double grampaFeetX = grampa.getX() + grampa.getImage().getWidth()/2;
            double grampaFeetY = grampa.getY() + grampa.getImage().getHeight();
            if (up == false && checkIfOnAPlatform() == true){
                System.out.println("END PLATFORM");
                grampaJump = false;
                onPlatform = true;
                up = true;
                jumpSpeed = 50;
                jumpAngle = 90;
                jumpAnimation.stop();
            }
            // if (up == false){
            //     for (PlatformObject platform : map.getPlatformList()) {
            //         if (grampaFeetY > platform.getStartY()
            //              && grampaFeetY < platform.getStartY() + 20
            //              && grampaFeetX > platform.getStartX()
            //              && grampaFeetX < platform.getEndX()
            //              && up == false){
            //             System.out.println("END PLATFORM");
            //             grampaJump = false;
            //             onPlatform = true;
            //             up = true;
            //             jumpSpeed = 50;
            //             jumpAngle = 90;
            //             jumpAnimation.stop();
            //             break;
            //         }
            //     }
            // }
            if (onPlatform == false){
                double grampaX = grampaStartX + (jumpSpeed * Math.cos(Math.toRadians(jumpAngle)) * grampaJumpTimer * jumpDirection);
                double grampaY = grampaStartY + ((jumpSpeed * Math.sin(Math.toRadians(jumpAngle)) * grampaJumpTimer) - (0.5 * 9.81 * Math.pow(grampaJumpTimer, 2)))*-1;
                grampa.setX(grampaX);
                grampa.setY(grampaY);
                double grampaXonScreen = grampa.localToScreen(grampa.getBoundsInLocal()).getMinX();
                if (grampaXonScreen > outerRegion.getWidth()/2 + 230 && anchorPane2.getLayoutX() > -1545){
                    anchorPane2.setLayoutX(anchorPane2.getLayoutX()-(1.1*5));
                }
                if (grampaXonScreen < outerRegion.getWidth()/2 - 230 && anchorPane2.getLayoutX() < 60){
                    anchorPane2.setLayoutX(anchorPane2.getLayoutX()+(1.1*5));
                }
                if (grampaY <= grampaStartY - 112){   //  vinkel 60 -> 95   vinkel 80 -> 123    vinkel 70 -> 112
                    System.out.println("TOPPPUNKT!");
                    up = false;
                }
                if (grampa.getY() > anchorPane2.getHeight()){
                    grampa.setY(0);
                    grampaStartY = 0;
                    grampaStartX = grampa.getX();
                    grampaJumpTimer = 0;
                    // jumpSpeed = 0;
                    grampaJump = false;
                    freeFalling = true;
                    freeFallingXSpeed = 0;
                    adjustFallingX = 0;
                    jumpSpeed = 50;
                    grampaFreeFallingAnimation.start();
                    jumpAnimation.stop();
                }
            }
            else{
                System.out.println("END TIMER");
                // grampaJump = false;
                // jumpAnimation.stop();
            }
        }
    };

    AnimationTimer grampaFreeFallingAnimation = new AnimationTimer()
    {    
        Integer timer = 0;
        // boolean up = true;
        
        public void handle(long currentNanoTime)
        {  
            grampaFreeFallingTimer += 0.2;
            // if (checkIfOnAPlatform()){
            //     System.out.println("STOP FALLING");
            //     onPlatform = true;
            //     freeFalling = false;
            //     grampaJump = false;
            //     grampa.setY(platform.getStartY() - grampa.getImage().getHeight()+1);     
            //     up = true;       
            //     jumpSpeed = 50;
            //     jumpAngle = 90;
            //     grampaFreeFallingAnimation.stop();
            // }
            double grampaFeetX = grampa.getX() + grampa.getImage().getWidth()/2;
            double grampaFeetY = grampa.getY() + grampa.getImage().getHeight();
            for (PlatformObject platform : map.getPlatformList()) {
                if (grampaFeetY > platform.getStartY()
                     && grampaFeetY < platform.getStartY() + 20
                     && grampaFeetX > platform.getStartX()
                     && grampaFeetX < platform.getEndX()){
                        System.out.println("STOP FALLING");
                        onPlatform = true;
                        freeFalling = false;
                        grampaJump = false;
                        grampa.setY(platform.getStartY() - grampa.getImage().getHeight()+1);     
                        up = true;       
                        jumpSpeed = 50;
                        jumpAngle = 90;
                        grampaFreeFallingAnimation.stop();
                        break;
                }
            }
            if (freeFalling){
                System.out.println("FALLING..." + grampa.getX());
                if (freeFallingXSpeed == 0){
                    if (dir.equals("right")) grampa.setX(grampa.getX() + 2);
                    if (dir.equals("left")) grampa.setX(grampa.getX() - 2);
                }
                else{
                    double grampaX = grampaStartFallingX + (freeFallingXSpeed * Math.cos(Math.toRadians(0)) * grampaFreeFallingTimer * freeFallingDir);
                    grampa.setX(grampaX);
                }
                double grampaY = grampaStartFallingY + ((0 * Math.sin(Math.toRadians(0)) * grampaFreeFallingTimer) - (0.5 * 9.81 * Math.pow(grampaFreeFallingTimer, 2)))*-1;
                grampa.setY(grampaY);
            }
            if (grampa.getY() > anchorPane2.getHeight()){
                grampa.setY(0);
                grampaStartFallingY = 0;
                grampaStartFallingX = grampa.getX();
                grampaFreeFallingTimer = 0;
                freeFallingXSpeed = 0;
                adjustFallingX = 0;
                jumpSpeed = 50;
            }
        }
    };

    public boolean checkIfOnAPlatform() {
        boolean checkStatus = false;
        double grampaFeetX = grampa.getX() + grampa.getImage().getWidth()/2;
        double grampaFeetY = grampa.getY() + grampa.getImage().getHeight();
        for (PlatformObject platform : map.getPlatformList()) {
            if (grampaFeetY > platform.getStartY()
                 && grampaFeetY < platform.getStartY() + 20
                 && grampaFeetX > platform.getStartX()
                 && grampaFeetX < platform.getEndX()){
                    checkStatus = true;
                    break;
            }
        }
        return checkStatus;
    }

    AnimationTimer boarTimer = new AnimationTimer()
    {    
        Random random = new Random();

        // int timerLimit = 20;
        // int minProximity = 200;
        // int maxProximity = 250;
        // int maxOffsetChange = 100;

        // double timer = 0.0;
        // double timerAttack = 0.0;
        // int currentRun;
        // int currentAttack;

        public void handle(long currentNanoTime)
        {   
            for (Enemy enemy : enemies) {
                //  Get platform from boar/enemy class and make it go back and forth
            }
        }
    };

    @FXML
    public void handleB1() {
        // System.out.println("Flytt b1!");
        // b1.setLayoutY(b1.getLayoutY() + 10);
        stats.increaseDucks(3);
        System.out.println(stats.getDucks());
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        if (grampaJump == false || (grampaJump == true)){
            if (keyEvent.getCode() == KeyCode.W){
                dir = "up";
                // grampaImageAnimation.start();
                if (grampaJump == false && freeFalling == false){
                    System.out.println("W");
                    adjustFallingX = 20;
                    grampaStartX = grampa.getX();
                    grampaStartY = grampa.getY();
                    grampaJumpTimer = 0.0;
                    // jumpAngle = 90;
                    grampaJump = true;
                    onPlatform = false;
                    grampaImageAnimation.stop();
                    jumpAnimation.start();
                }
            }
            else if (keyEvent.getCode() == KeyCode.A){
                dir = "left";
                if (grampaJump == false) jumpDirection = -1;
                if (freeFalling == false && grampaJump == false){
                    System.out.println("SHOULDN NOT HAPPEN");
                    // grampaImageAnimation.start();
                }
                grampaImageAnimation.start();
                if (freeFalling == true || (grampaJump == true && up == false)){
                    // adjustFallingX -= 5;
                    // jumpSpeed -= 5;
                    // grampaStartFallingX -= 5;
                }
                if (freeFalling == true && grampaJump == false){
                    // jumpDirection = -1;
                }
            }
            else if (keyEvent.getCode() == KeyCode.D){
                dir = "right";
                if (grampaJump == false) jumpDirection = 1;
                if (freeFalling == false && grampaJump == false){
                    // grampaImageAnimation.start();
                }
                grampaImageAnimation.start();
                // if (freeFalling == true || (grampaJump == true && up == false)){
                //     System.out.println("ADJUSTING");
                //     // adjustFallingX += 5;
                //     // jumpSpeed += 5;
                //     grampaStartFallingX += 5;
                // }
                if (freeFalling == true && grampaJump == false){
                    // jumpDirection = 1;
                }
            }
            else if (keyEvent.getCode() == KeyCode.S){
                dir = "down";
                grampaImageAnimation.start();
            }
            else{
                System.out.println("Stop");
                grampaImageAnimation.stop();
            }

        }
        if (keyEvent.getCode() == KeyCode.N){
            System.out.println(grampa.getX());
            System.out.println(scale.getPivotX() + ", " + scale.getPivotY());
            scale.setPivotX(anchorPane2.getWidth()/2);
            scale.setPivotY(anchorPane2.getHeight()/2);
        }
        if (keyEvent.getCode() == KeyCode.M){
            System.out.println(grampa.getX() + ", " + grampa.getY());
            System.out.println(anchorPane2.getLayoutX());
            System.out.println(grampa.getY()+grampa.getImage().getHeight());
            // scale.setPivotX(grampa.getX());
            // scale.setPivotY(grampa.getY());
        }
        if (keyEvent.getCode() == KeyCode.P){
            System.out.println(scale.getX() + ",: " + scale.getY());
            DecimalFormat format = new DecimalFormat("0.#");
            System.out.println(BigDecimal.valueOf(scale.getX()).setScale(1, RoundingMode.HALF_UP));
            // System.out.println(scale.getPivotX() + ", " + scale.getPivotY());
        }
        if (keyEvent.getCode() == KeyCode.L){
            System.out.println("L");
            anchorPane2.toBack();
            // scale.setX(1.0);
            // scale.setY(1.0);
            anchorPane2.setLayoutX(anchorPane2.getLayoutX()+10);
            // outerRegion.setLayoutX(outerRegion.getLayoutX()+10);
        }
        if (keyEvent.getCode() == KeyCode.O){
            System.out.println(grampa.getBoundsInLocal());
            System.out.println(grampa.getBoundsInParent());
            System.out.println(grampa.getLayoutBounds());
            System.out.println(grampa.localToScreen(grampa.getBoundsInLocal()));
        }
        if (keyEvent.getCode() == KeyCode.B){
            System.out.println("Creating Boar!");
            for (int i = 1; i < 6; i++) {
                Boar boarNode = new Boar(70.0, map.getPlatformList().get(i).getStartX() + 10, map.getPlatformList().get(i).getStartY() - 50);
                anchorPane2.getChildren().add(boarNode.getRectangle());
                anchorPane2.getChildren().add(boarNode.getRectangleOutline());
                anchorPane2.getChildren().add(boarNode.getImageView());
                enemies.add(boarNode);
            }
        }
    }

    @FXML
    private void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W){
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.A){
            if (grampaJump == false)
            jumpAngle = 90;
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.D){
            if (grampaJump == false)
            jumpAngle = 90;
            grampaImageAnimation.stop();
        }
        if (keyEvent.getCode() == KeyCode.S){
            grampaImageAnimation.stop();
        }
    }

    @FXML
    private void handleScroll(ScrollEvent se) {
        if (se == null){
            scale.setX(1.1);
            scale.setY(1.1);
            // return;
        }
        if (zoomAndScale){
            scale.setPivotX(grampa.getX());  //  Eller kanskje localtoscreen opplegg...
            scale.setPivotY(500);
            // System.out.println(grampa.getX() + "!!!");
            // System.out.println(se.getDeltaY());
            // System.out.println(scale.getPivotX());
            anchorPane2.toBack();
            double scaleFactor = 0.1;
            if (se.getDeltaY() > 0){
                System.out.println(scale.getX() + ",a " + scale.getY());
                // System.out.println(scale.getPivotX() + ",a " + scale.getPivotY());  
                if (scale.getX() < 2.0){
                    scale.setX(scale.getX() + scaleFactor);
                    scale.setY(scale.getY() + scaleFactor);
                    // scaleMoveFactor -= scaleFactor*10;
                }
            }
            else if (se.getDeltaY() < 0){
                System.out.println(scale.getX() + ",b " + scale.getY());
                // System.out.println(scale.getPivotX() + ",b " + scale.getPivotY());  
                if (scale.getX() > 1.1){
                    scale.setX(scale.getX() - scaleFactor);
                    scale.setY(scale.getY() - scaleFactor);
                    // scaleMoveFactor += scaleFactor*10;
                }
            }
        }
        
    }

    @FXML
    private void moveScreen(MouseEvent e) {
        mousePos.setText("a:x=" + String.valueOf(Math.round(e.getX())) + " : y=" + String.valueOf(Math.round(e.getY())));
    }
    
}

package game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class GameController implements Initializable{

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button button;

    @FXML
    Label mousePos;

    // private ImageView grampa = new ImageView();
    // private Image grampaFront = new Image(getClass().getResourceAsStream("res/grampaFront.png"), 96, 96, false, false);

    private ImageView grampaIcon = new ImageView();
    private Image grampaHead = new Image(getClass().getResourceAsStream("res/grampaHead.png"), 16, 16, false, false);

    PlayerStats stats = new PlayerStats(346);  

    PlayerStats gameStats;

    List<int[]> mapList = new ArrayList<>();
    String[] mapType = new String[4];
    int currentMap = 0;

    Scale scale = new Scale();
    double mousePosX = 0.0;
    double mousePosY = 0.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {  // Denne blir loadet hver gang uansett. Derfor greit å ikke lagre denne controlleren, men bare lage en nye hver gang.
        System.out.println("Nu kjør vi grabbar B-)");

        Image img = new Image(getClass().getResourceAsStream("res/BiggerMap.png"), 1000, 1000, false, false);
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        Background bGround = new Background(bImg);
        anchorPane.setBackground(bGround);

        // grampa.setImage(grampaFront);
        // anchorPane.getChildren().add(grampa);
        // grampa.setPreserveRatio(false);
        // grampa.setSmooth(false);
        // // grampa.setFitHeight(100);

        grampaIcon.setImage(grampaHead);
        anchorPane.getChildren().add(grampaIcon);
        grampaIcon.setX(602);
        grampaIcon.setY(394);

        // this.stats = new PlayerStats(346);

        mapList.add(new int[] {580, 324});
        mapType[0] = "topdown";
        mapList.add(new int[] {602, 394});
        mapType[1] = "topdown";
        mapList.add(new int[] {555, 265});
        mapType[2] = "platform";
        mapList.add(new int[] {545, 308});
        mapType[3] = "topdown";
        System.out.println(mapList);

        anchorPane.getTransforms().add(scale);
    }

    public void initData(PlayerStats data) {
        this.gameStats = data;
        System.out.println("Så mange ducks starter vi med: " + gameStats.getDucks());
    }

    @FXML
    private void handleButton() throws IOException {
        System.out.println("Bytt");
        button.setLayoutX(button.getLayoutX()+10);
        // gameStats.increaseDucks(4);
        // MapController mc = new MapController(34);
        
        Stage stageTheLabelBelongs = (Stage) button.getScene().getWindow();
        gameStats.setStage(stageTheLabelBelongs);
        System.out.println("STAGEFROMSTART " + gameStats.getStage());

        if (gameStats.getMapType().equals("topdown")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test2.fxml"));
            // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
            stageTheLabelBelongs.setScene(new Scene(loader.load()));
            MapController controller = loader.getController();
            gameStats.setMapController(controller);
            controller.initData(gameStats);
        }
        else if (gameStats.getMapType().equals("platform")){
            System.out.println("HALLLOOO");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/platformfxml.fxml"));
            // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
            stageTheLabelBelongs.setScene(new Scene(loader.load()));
            PlatformController controller = loader.getController();
            gameStats.setPlatformController(controller);
            controller.initData(gameStats);
        }

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/test2.fxml"));
        // // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
        // stageTheLabelBelongs.setScene(new Scene(loader.load()));
        // MapController controller = loader.getController();
        // gameStats.setMapController(controller);
        // controller.initData(gameStats);

    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) throws IOException {
        
            if (keyEvent.getCode() == KeyCode.D){
                if (currentMap >= mapList.size()-1){
                    currentMap = 0;
                }
                else{
                    currentMap++;
                }
                grampaIcon.setX(mapList.get(currentMap)[0]);
                grampaIcon.setY(mapList.get(currentMap)[1]);
                System.out.println(currentMap);
                gameStats.setMapNumber(currentMap);
            }

            if (keyEvent.getCode() == KeyCode.ENTER){
                System.out.println("MMM" + currentMap);
                Stage stageTheLabelBelongs = (Stage) button.getScene().getWindow();
                stats.setStage(stageTheLabelBelongs);
                System.out.println("STAGEFROMSTART " + stats.getStage());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
                // stageTheLabelBelongs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/test.fxml"))));
                stageTheLabelBelongs.setScene(new Scene(loader.load()));
                MapController controller = loader.getController();
                controller.initData(stats);
            }

            if (keyEvent.getCode() == KeyCode.O){
                System.out.println(gameStats.getDucks());
            }

            if (keyEvent.getCode() == KeyCode.L){
                scale.setPivotX(scale.getPivotX()+1);
            }

            if (keyEvent.getCode() == KeyCode.J){
                scale.setPivotX(scale.getPivotX()-1);
            }
    }

    @FXML
    private void handleScroll(ScrollEvent se) {
        // System.out.println(se.getDeltaY());
        
        if (se.getDeltaY() > 0){
            if (scale.getX() < 3.0){
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

    @FXML
    private void moveScreen(MouseEvent e) {
        mousePos.setText("x=" + String.valueOf(Math.round(e.getX())) + " : y=" + String.valueOf(Math.round(e.getY())));
        mousePosX = e.getX();
        mousePosY = e.getY();
        scale.setPivotX(mousePosX);
        scale.setPivotY(mousePosY);
    }
    
}

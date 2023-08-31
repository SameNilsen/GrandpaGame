package game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {   //  Fjern ting som ikke er nødvendige (feks stats.setStage i MapController? Kanksje også bytte navn på controllerne)
        primaryStage.setTitle("Grampa");
        FXMLLoader firstloader = new FXMLLoader(getClass().getResource("/grandpaApp.fxml"));
        // firstloader.setController(new GameController());  //  Setter controller manuelt.
        primaryStage.setScene(new Scene(firstloader.load()));
        primaryStage.setWidth(650);
        primaryStage.setHeight(650);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("res/grampaHead.png"), 96, 96, false, false));
        GameController firstController = firstloader.getController();
        PlayerStats gameStats = new PlayerStats(3);
        gameStats.setGameController(firstController);
        firstController.initData(gameStats);
        // primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
}

module grandpa {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    opens game to javafx.fxml;
    exports game;
}
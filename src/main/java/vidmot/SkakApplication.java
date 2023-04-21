package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SkakApplication extends Application {

    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vidmot/upphaf-view.fxml"));
        stage.initStyle(StageStyle.UNDECORATED); //Fjarlægir default window bar
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheets/skyjad-still.css").toExternalForm());
        stage.setFullScreen(false);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMaxHeight(MAX_HEIGHT);
        stage.setMaxWidth(MAX_WIDTH);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SkakApplication extends Application {

    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.UPPHAFSSENA);
        stage.setTitle("Skák");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene getScene() {
        return scene;
    }
}

package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;

import java.io.IOException;

public class TimaController extends UpphafController {

    public Menu fxTheme;
    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;

    private int duration;
    public static String bot;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void fxAudveltHandler(ActionEvent actionEvent)  throws IOException {
        bot = ("audvelt");
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(UpphafController.selectedStylesheet);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void fxErfittHandler(ActionEvent actionEvent)  throws IOException {
        bot=("erfitt");
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(UpphafController.selectedStylesheet);
        stage.setScene(scene);
        stage.show();
    }


    public static String getBot() {
        return bot;
    }


    public void fx1minHandler(ActionEvent actionEvent) {
        duration = 60000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void fx3minHandler(ActionEvent actionEvent) {
        duration = 180000;
        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void fx5minHandler(ActionEvent actionEvent) {
        duration = 300000;
        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        duration = 600000;
        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(618, 393, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void initialize(){
        ImageView homeIcon = new ImageView(new Image(getClass().getResource("images/home_icon.png").toExternalForm()));
        homeIcon.setFitWidth(30);
        homeIcon.setFitHeight(25);
        homeIcon.setPreserveRatio(true);
        fxHomeButton.setGraphic(homeIcon);

        ImageView playIcon = new ImageView(new Image(getClass().getResource("images/play.png").toExternalForm()));
        playIcon.setFitHeight(30);
        playIcon.setFitWidth(25);
        playIcon.setPreserveRatio(true);
        fxHljodtakki.setGraphic(playIcon);

        if(isBot == 1){
            bot = getBot();
        }
        else {
            bot = null;
        }
    }
}



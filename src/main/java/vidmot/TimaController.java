package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;

import java.io.IOException;
import java.util.Objects;

public class TimaController extends UpphafController {

    @FXML
    private Button fxHomeButton;
    @FXML
    private AnchorPane fxtitleBar;
    @FXML
    private ToggleButton fxHljodtakkiToggle;
    @FXML
    private ToggleButton fxFullscreenButton;

    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;
    public static int timiEftirShared;
    public static int timiEftir2Shared;
    private MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();
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
        scene.getStylesheets().add(selectedStylesheet);
        addDraggableNode(fxtitleBar);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void fxErfittHandler(ActionEvent actionEvent)  throws IOException {
        bot=("erfitt");
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(selectedStylesheet);
        addDraggableNode(fxtitleBar);
        stage.setScene(scene);
        stage.show();
    }


    public static String getBot() {
        return bot;
    }


    public void fx1minHandler(ActionEvent actionEvent) {
        timiEftirShared=60;
        timiEftir2Shared=60;
        duration = 60000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void fx3minHandler(ActionEvent actionEvent) {
        timiEftirShared=180;
        timiEftir2Shared=180;
        duration = 180000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void fx5minHandler(ActionEvent actionEvent) {
        timiEftirShared=300;
        timiEftir2Shared=300;
        duration = 300000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void fx10minHandler(ActionEvent actionEvent) throws IOException {
        timiEftirShared=600;
        timiEftir2Shared=600;
        duration = 600000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void initialize(){
        addDraggableNode(fxtitleBar);
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);
        if(isBot == 1){
            bot = getBot();
            System.out.println("Bot: " + bot);
        }
        else {
            bot = null;
        }
        if (mediaPlayer.isMute()){
            fxHljodtakkiToggle.setSelected(true);
        }
        if(!Objects.equals(BreiddScenu, "700")){
            fxFullscreenButton.setSelected(true);
        }
    }
}



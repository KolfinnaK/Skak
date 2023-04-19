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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    public static int timiEftirShared;
    public static int timiEftir2Shared;
    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;
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
        addDraggableNode(root);
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
        addDraggableNode(root);
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
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void fx3minHandler(ActionEvent actionEvent) {
        timiEftirShared=180;
        timiEftir2Shared=180;
        duration = 180000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration, bot)));
        }

    }

    public void fx5minHandler(ActionEvent actionEvent) {
        timiEftirShared=300;
        timiEftir2Shared=300;
        duration = 300000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void fx10minHandler(ActionEvent actionEvent) throws IOException {
        timiEftirShared=600;
        timiEftir2Shared=600;
        duration = 600000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMaxHeight(MAX_HEIGHT);
            stage.setMaxWidth(MAX_WIDTH);
            stage.show();
        }


    }

    public void initialize(){
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);

        if(isBot == 1){
            bot = getBot();
            System.out.println("Bot: " + bot);
        }
        else {
            bot = null;
        }
    }
}



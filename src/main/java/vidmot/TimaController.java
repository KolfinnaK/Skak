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

/****************************************************************************************************************************
 * Lýsing: Controller klasi fyrir tímasenuna og einnig erfiðleikasenuna sem inniheldur handlera fyrir takkana í senunni.
 *
 *
 *
 *
 *
 *****************************************************************************************************************************/


public class TimaController extends UpphafController {

    public Menu fxTheme;
    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;
    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;
    private int duration;
    public static String bot;
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Handler fyrir auðvelt takkann í erfiðleikasenunni sem setur bottann sem audvelt og birtir svo næstu senu
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Handler fyrir erfitt takkann í erfiðleikasenunni sem setur bottann sem erfitt og birtir svo næstu senu
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Handler fyrir 1 mín takkann sem geymir hvaða tími var valinn og sendir það inn í skáksenuna
     * @param actionEvent
     */
    public void fx1minHandler(ActionEvent actionEvent) {
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

    /**
     * Handler fyrir 3 mín takkann sem geymir hvaða tími var valinn og sendir það inn í skáksenuna
     * @param actionEvent
     */
    public void fx3minHandler(ActionEvent actionEvent) {
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

    /**
     * Handler fyrir 5 mín takkann sem geymir hvaða tími var valinn og sendir það inn í skáksenuna
     * @param actionEvent
     */
    public void fx5minHandler(ActionEvent actionEvent) {
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

    /**
     * Handler fyrir 10 mín takkann sem geymir hvaða tími var valinn og sendir það inn í skáksenuna
     * @param actionEvent
     * @throws IOException
     */
    public void fx10minHandler(ActionEvent actionEvent) throws IOException {
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

    /**
     * initialize aðferð sem lætur homebutton hafa handler og setur bottann sem bottann sem var valinn í fyrri senu ef
     * valið var að spila við tölvu, annars er hann settur sem null.
     */
    public void initialize(){
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);
        if(isBot == 1){
            bot = getBot();
        }
        else {
            bot = null;
        }
    }
}



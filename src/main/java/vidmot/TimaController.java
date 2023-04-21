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

    private MediaPlayer mediaPlayer = TonlistarStjori.getMediaPlayer();
    private int duration;
    public static String bot;
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Handler fyrir auðvelt bot takka. Tekur mann yfir í tímasenu
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
        dragaSkjaHandler(fxtitleBar);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handler fyrir erfitt bot takka. Tekur mann yfir í tímasenu
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
        dragaSkjaHandler(fxtitleBar);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * getter fyrir bot,
     * @return String sem segir til um hvort valið var auðvelt eða erfitt bot.
     */
    public static String getBot() {
        return bot;
    }

    //handlerar fyrir tíma takkana sem stilla tíma á leiknum og búa til leik senuna

    /**
     * Handler fyrir 1mín takka, hefur leik með 1 mínútu á mann.
     * Tekur einnig inn hvort spilað er á móti tölvu eða ekki og erfiðleikastig ef valið er að spila á móti tölvu
     * og býr til skákleik og færir mann yfir í skáksenu
     * @param actionEvent
     */
    public void fx1minHandler(ActionEvent actionEvent) {
        duration = 60000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }

    }


    /**
     * Handler fyrir 3mín takka, hefur leik með 3 mínútur á mann.
     * Tekur einnig inn hvort spilað er á móti tölvu eða ekki og erfiðleikastig ef valið er að spila á móti tölvu
     * og býr til skákleik og færir mann yfir í skáksenu
     * @param actionEvent
     */
    public void fx3minHandler(ActionEvent actionEvent) {
        duration = 180000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Handler fyrir 5mín takka, hefur leik með 5 mínútur á mann.
     * Tekur einnig inn hvort spilað er á móti tölvu eða ekki og erfiðleikastig ef valið er að spila á móti tölvu
     * og býr til skákleik og færir mann yfir í skáksenu
     * @param actionEvent
     */
    public void fx5minHandler(ActionEvent actionEvent) {
        duration = 300000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     *  Handler fyrir 10mín takka, hefur leik með 10 mínútur á mann.
     *  Tekur einnig inn hvort spilað er á móti tölvu eða ekki og erfiðleikastig ef valið er að spila á móti tölvu
     *  og býr til skákleik og færir mann yfir í skáksenu
     * @param actionEvent
     * @throws IOException
     */
    public void fx10minHandler(ActionEvent actionEvent) throws IOException {
        duration = 600000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new SkakBordSenaController(new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot));
            scene.getStylesheets().add(selectedStylesheet);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initialize fall fyrir TimaController, skoðar ýmis stillingaratriði úr fyrri senum og setur senuna upp eftir því
     */
    public void initialize(){
        dragaSkjaHandler(fxtitleBar);
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



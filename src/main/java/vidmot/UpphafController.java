package vidmot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import presenter.MediatorConstructionFlags;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpphafController  {

    @FXML
    private Button fxHomeButton;
    @FXML
    private ToggleButton fxHljodtakkiToggle;
    @FXML
    private ToggleButton fxFullscreenButton;
    @FXML
    private AnchorPane fxtitleBar;

    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;
    private double xOffset = 0.0, yOffset = 0.0;
    private MediaPlayer mediaPlayer = TonlistarStjori.getMediaPlayer();
    public static String selectedStylesheet =  UpphafController.class.getResource("stylesheets/skyjad-still.css").toExternalForm();
    private MediatorConstructionFlags constructionFlag;
    public static int isBot;
    private Stage stage;
    public static String BreiddScenu = "700";
    private Scene scene;
    private Parent root;

    public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
        this.constructionFlag = constructionFlag;
    }

    public MediatorConstructionFlags getConstructionFlag() {
        return constructionFlag;
    }

    //handler sem fyrir minimize takkann
    public void fxMinimizeButtonHandler(ActionEvent event){
        Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setIconified(true);
    }
    //handler fyrir fullscreen takkan
    public void fxFullscreenButtonHandler(ActionEvent event){
        Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (scene.getWidth() == MAX_WIDTH && scene.getHeight() == MAX_HEIGHT){
            scene.setWidth(MIN_WIDTH);
            scene.setHeight(MIN_HEIGHT);
            fxFullscreenButton.setSelected(false);
            BreiddScenu = "700";
        } else {
            scene.setWidth(MAX_WIDTH);
            scene.setHeight(MAX_HEIGHT);
            fxFullscreenButton.setSelected(true);
            BreiddScenu = "1050";
        }
    }

    //handler sem lokar forritinu
    public void fxCloseButtonHandler(ActionEvent event){
        Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.close();
    }

    //handler sem opnar erfidleikavalmyndina
    public void fxTolvaHandler(ActionEvent event) throws IOException {
        isBot = 1;
        root = FXMLLoader.load(getClass().getResource("/vidmot/erfidleika-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(selectedStylesheet);
        dragaSkjaHandler(fxtitleBar);
        stage.setScene(scene);
        stage.show();
        setConstructionFlag(MediatorConstructionFlags.TIMED_AI); //það kemur villa útaf þessum línum
    }

    //handler sem opnar leikmannsvalmyndina
    public void fxLeikmadurHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(selectedStylesheet);
        dragaSkjaHandler(fxtitleBar);
        stage.setScene(scene);
        stage.show();
        setConstructionFlag(MediatorConstructionFlags.TIMED_LOCAL);
    }
    //handler sem leifir notanda að draga skjáinn
    void dragaSkjaHandler(final Node node) {
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    xOffset = me.getSceneX();
                    yOffset = me.getSceneY();
                }
            }
        });
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    node.getScene().getWindow().setX(me.getScreenX() - xOffset);
                    node.getScene().getWindow().setY(me.getScreenY() - yOffset);
                }
            }
        });
    }
    //Hljóðtakka handlerinn
    public void fxHljodtakkiHandler(ActionEvent actionEvent) {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    //heima takka handlerinn
    public void fxHomeButtonHandler(ActionEvent actionEvent) {
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ertu viss?");
            alert.setHeaderText(null);
            alert.setContentText("Vilt þú fara til baka á upphafsskjá?");

            ButtonType yesButton = new ButtonType("Já", ButtonBar.ButtonData.OK_DONE);
            ButtonType noButton = new ButtonType("Nei", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/vidmot/upphaf-view.fxml"));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(selectedStylesheet);
                    dragaSkjaHandler(fxtitleBar);
                    stage.setScene(scene);
                    stage.show();
                    isBot = 0;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //handlerar fyrir þema takkana
    public void fxClassicHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/klassiskt-still.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }
    public void fxCloudHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/skyjad-still.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }
    public void fxTropicalHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/hitabeltis-still.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void initialize() {
        dragaSkjaHandler(fxtitleBar);
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);
        mediaPlayer.setAutoPlay(true);
        if (mediaPlayer.isMute()){
            fxHljodtakkiToggle.setSelected(true);
        }
        if(!Objects.equals(BreiddScenu, "700")){
            fxFullscreenButton.setSelected(true);
        }
    }
}

package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import presenter.MediatorConstructionFlags;

import java.io.IOException;
import java.util.Optional;

public class UpphafController {

    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;

    private MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();
    public static String selectedStylesheet = "";
    private MediatorConstructionFlags constructionFlag;
    public static int isBot;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int thema;


    public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
        this.constructionFlag = constructionFlag;
    }

    public MediatorConstructionFlags getConstructionFlag() {
        return constructionFlag;
    }

    public void fxTolvaHandler(ActionEvent event) throws IOException {
        isBot = 1;
        root = FXMLLoader.load(getClass().getResource("/vidmot/erfidleika-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(selectedStylesheet);
        stage.setScene(scene);
        stage.show();
        setConstructionFlag(MediatorConstructionFlags.TIMED_AI); //það kemur villa útaf þessum línum
    }

    public void fxLeikmadurHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(selectedStylesheet);
        stage.setScene(scene);
        stage.show();
        setConstructionFlag(MediatorConstructionFlags.TIMED_LOCAL);
    }

    public void fxHljodtakkiHandler(ActionEvent actionEvent) {

        mediaPlayer.setMute(!mediaPlayer.isMute());

        ImageView image;
        if (mediaPlayer.isMute()) {
            image = new ImageView(getClass().getResource("images/mute.png").toExternalForm());
            image.setFitHeight(30);
            image.setFitWidth(25);
            image.setPreserveRatio(true);
        } else {
            image = new ImageView(getClass().getResource("images/play.png").toExternalForm());
            image.setFitHeight(30);
            image.setFitWidth(25);
            image.setPreserveRatio(true);
        }
        fxHljodtakki.setGraphic(image);
    }

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
                fxHomeButton.getScene().getStylesheets().clear();
                try {
                    root = FXMLLoader.load(getClass().getResource("/vidmot/upphaf-view.fxml"));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(selectedStylesheet);
                    stage.setScene(scene);
                    stage.show();
                    isBot = 0;
                } catch (IOException e) {
                    e.printStackTrace();

                if (result.isPresent() && result.get() == yesButton) {
                    fxHomeButton.getScene().getStylesheets().clear();

                }
            }
        }
    }}

    public void fxClassicHandler(ActionEvent actionEvent) {
        thema = 1;
        String newStylesheet = getClass().getResource("stylesheets/classic-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;

        }
    }

    public void fxCottonCandyHandler(ActionEvent actionEvent) {
        thema = 2;
        String newStylesheet = getClass().getResource("stylesheets/cottoncandy-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void fxTropicalHandler(ActionEvent actionEvent) {
        thema = 3;
        String newStylesheet = getClass().getResource("stylesheets/tropical-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void initialize() {
        fxHomeButton.setOnAction(this::fxHomeButtonHandler);
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

        mediaPlayer.setAutoPlay(true);
    }
}

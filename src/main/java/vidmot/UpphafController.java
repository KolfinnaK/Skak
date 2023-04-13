package vidmot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.media.*;
import presenter.MediatorConstructionFlags;

import java.util.Optional;


/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class UpphafController{

    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;
    @FXML
    private Button fxTolva;
    @FXML
    private Button fxLeikmadur;

    private SkakController skakController;
    private TimaController timaController = (TimaController) ViewSwitcher.lookup(View.TIMAMORK);
    public boolean isLocalTime;
    private MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();
    private String selectedStylesheet = "";
    private MediatorConstructionFlags constructionFlag;


    public void fxTolvaHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.ERFIDLEIKASENA);
        setConstructionFlag(MediatorConstructionFlags.TIMED_AI); //það kemur villa útaf þessum línum

    }

    public void fxLeikmadurHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.TIMAMORK);
        setConstructionFlag(MediatorConstructionFlags.TIMED_LOCAL);
        //skakController.setLocalTime(isLocalTime);
    }

   public void fxHljodtakkiHandler(ActionEvent actionEvent){

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

    public void fxHomeButtonHandler(ActionEvent actionEvent){
        fxHomeButton.setOnAction(event -> {
            if (!event.isConsumed()) {
                event.consume();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ertu viss?");
                alert.setHeaderText(null);
                alert.setContentText("Vilt þú fara til baka á upphafsskjá og hreinsa þema?");

                ButtonType yesButton = new ButtonType("Já", ButtonBar.ButtonData.OK_DONE);
                ButtonType noButton = new ButtonType("Nei", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(yesButton, noButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == yesButton) {
                    fxHomeButton.getScene().getStylesheets().clear();
                    ViewSwitcher.switchTo(View.UPPHAFSSENA);
                    skakController.setBot("");
                }
            }
        });
    }


    public void fxClassicHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/classic-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void fxCottonCandyHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/cottoncandy-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void fxTropicalHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/tropical-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            fxHomeButton.getScene().getStylesheets().remove(selectedStylesheet);
            fxHomeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
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

        mediaPlayer.setAutoPlay(true);

    }

    public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
        this.constructionFlag = constructionFlag;
    }

    public MediatorConstructionFlags getConstructionFlag() {
        return constructionFlag;
    }

}

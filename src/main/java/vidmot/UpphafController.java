package vidmot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.media.*;


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
    private Menu fxTheme;
    @FXML
    private Button fxTolva;
    @FXML
    private Button fxLeikmadur;

    Media media = new Media(getClass().getResource("audio/music.wav").toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    private SkakController skakController;
    public boolean isLocalTime;

    public void fxTolvaHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.ERFIDLEIKASENA);
        skakController.setLocalTime(!isLocalTime);

    }

    public void fxLeikmadurHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.TIMAMORK);
        skakController.setLocalTime(isLocalTime);
    }

   public void fxHljodtakkiHandler(ActionEvent actionEvent){
       mediaPlayer.setMute(!mediaPlayer.isMute());

       ImageView image;
       if (mediaPlayer.isMute()) {
           image = new ImageView(getClass().getResource("images/play.png").toExternalForm());
           image.setFitWidth(fxHljodtakki.getWidth());
           image.setFitHeight(fxHljodtakki.getHeight());
           image.setPreserveRatio(true);
       } else {
           image = new ImageView(getClass().getResource("images/mute.png").toExternalForm());
           image.setFitWidth(fxHljodtakki.getWidth());
           image.setFitHeight(fxHljodtakki.getHeight());
           image.setPreserveRatio(true);
       }
       fxHljodtakki.setGraphic(image);
   }

    public void fxThemeHandler(ActionEvent actionEvent){

    }

    public void fxClassicHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add(getClass().getResource("stylesheets/classic-styles.css").toExternalForm());}

    public void fxCottonCandyHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add(getClass().getResource("stylesheets/cottoncandy-styles.css").toExternalForm());

    }

    public void fxTropicalHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add(getClass().getResource("stylesheets/tropical-styles.css").toExternalForm());
    }

    public void initialize(){
        ImageView homeIcon = new ImageView(new Image(getClass().getResource("images/home_icon.png").toExternalForm()));
        homeIcon.setFitWidth(53);
        homeIcon.setFitHeight(34);
        homeIcon.setPreserveRatio(true);
        fxHomeButton.setGraphic(homeIcon);

        ImageView muteIcon = new ImageView(new Image(getClass().getResource("images/mute.png").toExternalForm()));
        muteIcon.setFitHeight(34);
        muteIcon.setFitWidth(38);
        muteIcon.setPreserveRatio(true);
        fxHljodtakki.setGraphic(muteIcon);

        mediaPlayer.setAutoPlay(true);

    }

}

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
   }

    public void fxHomeButtonHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().clear();
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
        Image img = new Image(getClass().getResource("images/home_icon.png").toExternalForm()); //ekki rétt path
        ImageView view = new ImageView(img);

        //fxHomeButton.getStyleClass().add("mynd");
        mediaPlayer.play();

        //fxHomeButton.setGraphic(view);
        //mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);

    }

}

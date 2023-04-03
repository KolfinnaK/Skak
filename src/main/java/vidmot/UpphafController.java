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

    //Media media = new Media("audio/music.wav");
    //MediaPlayer mediaPlayer = new MediaPlayer(media);
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

   public void fxHljodtakkiHandler(ActionEvent actionEvent){ //veit ekki hvort þetta sé rétt
        /*if(mediaPlayer.isMute()){
            mediaPlayer.play();
        }

        mediaPlayer.setMute(true);

        else {
            mediaPlayer.setMute(true);
        }*/
    }

    public void fxThemeHandler(ActionEvent actionEvent){

    }

    public void fxClassicHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("stylesheets/classic-styles.css"); //ekki rétt path
    }

    public void fxCottonCandyHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("stylesheets/cottoncandy-styles.css"); //ekki rétt path
    }

    public void fxTropicalHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("stylesheets/tropical-styles.css"); //ekki rétt path
    }

    public void initialize(){
        //Image img = new Image("images/home_icon.png"); //ekki rétt path
        //ImageView view = new ImageView(img);
        //fxHomeButton.setGraphic(view);
        //mediaPlayer.play();
    }

}

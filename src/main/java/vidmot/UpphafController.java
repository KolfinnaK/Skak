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

    Media media = new Media("resourses/audio/music.wav");
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    private SkakApplication skakApplication;

    public void fxTolvaHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.ERFIDLEIKASENA);
    }

    public void fxLeikmadurHandler(ActionEvent actionEvent){
        ViewSwitcher.switchTo(View.TIMAMORK);
    }

   public void fxHljodtakkiHandler(ActionEvent actionEvent){ //veit ekki hvort þetta sé rétt
        if(mediaPlayer.isMute()){
            mediaPlayer.play();
        }
        mediaPlayer.setMute(true);
    }

    public void fxClassicHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("resources/stylesheets/classic-styles.css"); //ekki rétt path
    }

    public void fxCottonCandyHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("resources/stylesheets/cottoncandy-styles.css"); //ekki rétt path
    }

    public void fxTropicalHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().add("resources/stylesheets/tropical-styles.css"); //ekki rétt path
    }

    public void initialize(){
        Image img = new Image("main/resources/images/home_icon.png"); //ekki rétt path
        ImageView view = new ImageView(img);
        fxHomeButton.setGraphic(view);
        mediaPlayer.play();
    }

}

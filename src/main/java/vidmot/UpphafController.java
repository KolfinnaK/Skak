package vidmot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
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
    private MenuBar fxTheme;
    @FXML
    private Button fxTolva;
    @FXML
    private Button fxLeikmadur;

    Media media = new Media(UpphafController.class.getResource("music.wav").toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

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

    public void initialize(){
        fxHomeButton.setGraphic(new ImageView(new Image(UpphafController.class.getResourceAsStream("home_icon.png"))));
        mediaPlayer.play();
    }

}

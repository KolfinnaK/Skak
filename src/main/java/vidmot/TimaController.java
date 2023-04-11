package vidmot;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//<<<<<<< Updated upstream
//=======
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//>>>>>>> Stashed changes
import javafx.stage.Stage;
import presenter.TimerPresenter;

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
public class TimaController extends UpphafController{

    @FXML
    private Label timaLabel;
    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;

    private SkakController skakController;
    private int timi;
    private Timeline timeline;


    public void fx5minHandler(ActionEvent actionEvent) {
<<<<<<< HEAD
        this.timi=5; //þetta er ekki rétt
=======
        this.timi=5; //þarf þetta nokkuð?
>>>>>>> c479f462188755b5a2b1e5fa8a86e09950b20667
        timaLabel.setText(String.valueOf(this.timi));
        skakController.setDuration(5000);
        //timaLabel.setText(timi.toString));
        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx3minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
        skakController.setDuration(3000);
    }

    public void fx1minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
        skakController.setDuration(1000);
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
        skakController.setDuration(10000);
    }

    @FXML
    public void fxHomeButtonHandler3(ActionEvent actionEvent){ //virkar ekki að skipta yfir í upphafssenu
        fxHomeButton.getScene().getStylesheets().clear();
        skakController.setBot("");
        skakController.setDuration(0);

    }

}



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
        this.timi=5; //þetta er ekki rétt
        timaLabel.setText(String.valueOf(this.timi));
        //timaLabel.setText(timi.toString));
        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx3minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx1minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    @FXML
    public void fxHomeButtonHandler3(ActionEvent actionEvent){ //virkar ekki að skipta yfir í upphafssenu
        fxHomeButton.getScene().getStylesheets().clear();
        skakController.setBot("");
        skakController.setTimer(null);

    }

}



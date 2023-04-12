package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
//<<<<<<< Updated upstream
//=======
import javafx.scene.control.Label;
//>>>>>>> Stashed changes
import javafx.util.Duration;

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
public class TimaController extends UpphafController {
    @FXML
   private Label klukka;
    @FXML
    private Label klukka2;

    @FXML
    private SkakController skakController;
    public static int timiEftirShared;
    public static int timiEftir2Shared;

    private Timeline timeline1;
    private Timeline timeline2;
    private boolean erKlukka1adTeljaNidur=true;


    public void fx1minHandler(ActionEvent actionEvent) {
        timiEftirShared=60;
        timiEftir2Shared=60;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx3minHandler(ActionEvent actionEvent) {
        timiEftirShared=180;
        timiEftir2Shared=180;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx5minHandler(ActionEvent actionEvent) {
        timiEftirShared=300;
        timiEftir2Shared=300;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        timiEftirShared=600;
        timiEftir2Shared=600;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

   /* @FXML
    public void fxHomeButtonHandler(ActionEvent actionEvent){ //virkar ekki að skipta yfir í upphafssenu
        fxHomeButton.getScene().getStylesheets().clear();
        skakController.setBot("");
        //það þarf einhvern veginn að núllstilla tímann

    }*/
    public void initialize(){

    }

    public static int getTimiEftirShared() {
        return timiEftirShared;
    }

    public static int getTimiEftir2Shared() {
        return timiEftir2Shared;
    }

}



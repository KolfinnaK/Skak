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
    public int timiEftir;
    public int timiEftir2;
    private Timeline timeline1;
    private Timeline timeline2;
    private boolean erKlukka1adTeljaNidur=true;

    public void setTimiEftir(int timiEftir) {
        this.timiEftir = timiEftir;
    }

    private void setTimiEftir2(int timiEftir2) {
        this.timiEftir2 = timiEftir2;
    }

    public void fx1minHandler(ActionEvent actionEvent) {
        setTimiEftir(60);
        timiEftirShared=60;

        setTimiEftir2(60);
        timiEftir2Shared=60;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx3minHandler(ActionEvent actionEvent) {
        setTimiEftir(180);
        timiEftirShared=180;

        setTimiEftir2(180);
        timiEftir2Shared=180;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx5minHandler(ActionEvent actionEvent) {
        setTimiEftir(300);
        timiEftirShared=300;

        setTimiEftir2(300);
        timiEftir2Shared=300;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        setTimiEftir(600);
        timiEftirShared=600;

        setTimiEftir2(600);
        timiEftir2Shared=600;

        ViewSwitcher.switchTo(View.SKAKSENA);
    }
}



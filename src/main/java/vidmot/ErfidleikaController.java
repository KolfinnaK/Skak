package vidmot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class ErfidleikaController extends UpphafController {
    @FXML
    private Button fxAudvelt;
    @FXML
    private Button fxErfitt;
    @FXML
    private Button fxHomeButton;

    private String bot;
    private SkakController skakController;

    @FXML
    public void fxAudveltHandler() {
        bot = "audvelt";
        skakController.setBot(bot);
        ViewSwitcher.switchTo(View.TIMAMORK);
    }

    @FXML
    public void fxErfittHandler() {
        bot = "erfitt";
        skakController.setBot(bot);
        ViewSwitcher.switchTo(View.TIMAMORK);
    }

    @FXML
    public void fxHomeButtonHandler2(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().clear();
        skakController.setBot("");
        ViewSwitcher.switchTo(View.UPPHAFSSENA);
    }
}


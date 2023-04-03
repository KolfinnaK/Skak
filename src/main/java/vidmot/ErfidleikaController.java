package vidmot;

import javafx.application.Application;
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
    private String bot;

    @FXML
    public void fxAudveltHandler() {
        bot = "audvelt";
    }

    @FXML
    public void fxErfittHandler() {
        bot = "erfitt";
    }
}


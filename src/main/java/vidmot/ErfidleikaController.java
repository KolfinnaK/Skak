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
    //private TimaController timaController = (TimaController) ViewSwitcher.lookup(View.TIMAMORK);

    @FXML
    public void fxAudveltHandler() {
        setBot("audvelt");
        ViewSwitcher.switchTo(View.TIMAMORK);
    }

    @FXML
    public void fxErfittHandler() {
        setBot("erfitt");
        ViewSwitcher.switchTo(View.TIMAMORK);
    }

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

}


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
public class TimaController {

    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;

    private SkakController skakController;

    @FXML
    public void fxHomeButtonHandler(ActionEvent actionEvent){
        fxHomeButton.getScene().getStylesheets().clear();
        skakController.setBot("");
        skakController.setTimer(null);

    }

}

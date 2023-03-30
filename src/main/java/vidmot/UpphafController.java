package vidmot;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
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
    private Button homeButton;
    @FXML
    private Button hljodtakki;
    @FXML
    private MenuBar theme;
    @FXML
    private Button tolva;
    @FXML
    private Button leikmadur;

    Media media = new Media(UpphafController.class.getResource("upphaf.mp4").toExternalForm());

}

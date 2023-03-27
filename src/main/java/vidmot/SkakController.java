package vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SkakController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
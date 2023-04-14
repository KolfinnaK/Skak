package vidmot;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/* erum ekki að nota þetta

public class ErfidleikaController extends UpphafController {
    @FXML
    private Button fxAudvelt;
    @FXML
    private Button fxErfitt;
    @FXML
    private Button fxHomeButton;

    public String bot;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private SkakController skakController;
    //private TimaController timaController = (TimaController) ViewSwitcher.lookup(View.TIMAMORK);

    private BotHandler botHandler;

    public ErfidleikaController(BotHandler botHandler) {
        this.botHandler = botHandler;
    }

    @FXML
    public void fxAudveltHandler(ActionEvent actionEvent)  throws IOException {
        setBot("audvelt");
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void fxErfittHandler(ActionEvent actionEvent)  throws IOException {
        setBot("erfitt");
        root = FXMLLoader.load(getClass().getResource("/vidmot/timi-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }
    //
   // @Override
   // public void handle(ActionEvent event) {
     //   if (event.getSource() == fxAudvelt) {
          //  setBot("audvelt");
     //   } else if (event.getSource() == fxErfitt) {
          //  setBot("erfitt");
      //  }
       // botHandler.handleBot(this); // send the bot instance to the other class

}

*/

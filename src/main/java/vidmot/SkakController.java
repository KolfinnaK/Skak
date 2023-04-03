package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;

import java.util.ResourceBundle;
import java.util.Timer;

public class SkakController{

    private String bot;
    private boolean isLocalTime;
    private Timer timer;

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    public void setLocalTime(boolean localTime) {
        isLocalTime = localTime;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    public void initialize() {

    }
}

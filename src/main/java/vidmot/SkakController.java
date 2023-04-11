package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;
import presenter.TimerPresenter;

import java.util.ResourceBundle;
import java.util.Timer;

public class SkakController {

    private String bot;
    private boolean isLocalTime;
    private int duration;
    private MediatorConstructionFlags constructionFlag;

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    public void setLocalTime(boolean localTime) {
        isLocalTime = localTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
        this.constructionFlag = constructionFlag;
    }


    public void initialize() {
        new GameMediator(constructionFlag, duration, bot);
    }
}
